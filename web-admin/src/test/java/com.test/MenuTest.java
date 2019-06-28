package com.test;


import com.mall.App;
import com.mall.common.entity.Menu;
import com.mall.common.repository.MenuRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {App.class})
public class MenuTest {
    @Autowired
    private
    MenuRepository menuRepository;
    @Test
    public void testMenuListByUserId(){
        List<Menu> menus = menuRepository.findByUserId(1L);
        System.out.println("test hello 1");
    }
}
