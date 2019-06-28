package com.mall.admin.config;

import com.mall.admin.common.BuzException;
import com.mall.common.entity.Menu;
import com.mall.common.entity.User;
import com.mall.common.repository.MenuRepository;
import com.mall.common.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        //todo 开发阶段不做权限验证。
        if (uri != null){
            return true;
        }
        //不需要验证的url
        if (uri.endsWith("/api/login") || uri.endsWith("/api/register")){
            return true;
        }

        String authHeader = request.getHeader(this.tokenHeader);
        if (StringUtils.isEmpty(authHeader)){
            throw new BuzException(1,"token为空,请登录");
        }
        String authToken = authHeader.substring(this.tokenHead.length());
        //验证token是否正确。和redis缓存的一致，且没过期才是正确的。
        String username = jwtTokenUtil.getUserNameFromToken(authToken);
        String cachedKey = "token_"+username;
        String cachedToken = (String) redisTemplate.opsForValue().get(cachedKey);
        if (jwtTokenUtil.isTokenExpired(authToken) || !authToken.equals(cachedToken)){
            throw new BuzException(1,"token已过期，请重新登录");
        }

        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new BuzException(1,"用户名不存在");
        }

        //如果是登出
        if (uri.endsWith("/api/logout")){
            redisTemplate.delete(cachedKey);
            return true;
        }

        //验证权限
        List<Menu> menus = menuRepository.findByUserId(user.getId());
        List<String> urls = menus.stream().map(Menu::getUri).collect(Collectors.toList());
        if (!urls.contains(uri)){
            throw new BuzException(1,"访问权限不足");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
