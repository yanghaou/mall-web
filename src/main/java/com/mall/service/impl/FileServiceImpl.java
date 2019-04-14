package com.mall.service.impl;

import com.mall.service.FileService;
import com.mall.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * author :y.hao
 * time :2019/4/11
 * function:
 */
@Service
public class FileServiceImpl implements FileService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    @Value("${image.path}")
    private String imagePath;

    public Result uploadFile(MultipartFile file){
        if (file.isEmpty()) {
            return new Result(-1,"文件不能为空！",null);
        }

        //获取上传图片的宽高,用来做图片大小限制
        /*BufferedImage bi = null;
        try {
            bi = ImageIO.read(file.getInputStream());
            int w = bi.getWidth();
            int h = bi.getHeight();

            LOGGER.info("width:"+w+", height:"+h);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(new File(imagePath).getAbsolutePath()+"/"+fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            //本地运行项目
            String url="/image/"+fileName;
            LOGGER.info(new StringBuilder().append("上传图片成功：").append(url).toString());
            return new Result(0,"success",url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(-1,"failed",null);
    }
}
