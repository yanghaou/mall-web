package com.mall.admin.service;

import com.mall.admin.util.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * author :y.hao
 * time :2019/4/11
 * function:
 */
public interface FileService {
    Result uploadFile(MultipartFile file);
}
