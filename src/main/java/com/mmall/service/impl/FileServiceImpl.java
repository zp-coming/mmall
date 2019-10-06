package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @author zp
 * @date 2019/3/10
 */
@Service("iFileService")
@Slf4j
public class FileServiceImpl implements IFileService {

    /**
     *  上传文件
     * @param file
     * @param path
     * @return
     */
    @Override
    public String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadName = UUID.randomUUID().toString() + "." + fileExtensionName;
        log.info("开始上传文件,上传的文件名是:{},上传的路径:{},新文件名:{}", fileName, path, uploadName);

        File fileDir = new File(path);
        // 创建目录
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        // 创建文件
        File targetFile = new File(path, uploadName);
        try {
            file.transferTo(targetFile);
            // 上传文件成功
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            targetFile.delete();

        } catch (IOException e) {
            log.error("上传文件异常", e);
            return null;
        }
        return targetFile.getName();
    }

}
