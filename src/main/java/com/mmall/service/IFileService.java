package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author zp
 * @date 2019/3/10
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}
