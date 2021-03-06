package com.mall.admin.controller;

import com.mall.admin.service.FileService;
import com.mall.common.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * author :y.hao
 * time :2019/4/11
 * function:
 */
@RestController
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping("/file/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        return fileService.uploadFile(file);
    }
}
