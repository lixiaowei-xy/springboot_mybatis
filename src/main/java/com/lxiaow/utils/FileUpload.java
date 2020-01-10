package com.lxiaow.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName FileUpload
 * @Description: TODO
 * @Author lxw
 * @Date 2019/12/5
 **/
@Component
public class FileUpload {

    private static final Log LOGGER = LogFactory.getLog(FileUpload.class);

    public String upload(MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return "上传失败，请选择文件";
        }
        //取得上传文件名字
        String fileName = file.getOriginalFilename();
        //路径
        String filePath = "D:\\workSoftware\\upload\\";
        File newFile = new File(filePath+fileName);
        LOGGER.info(filePath+fileName);
        //读取文件
        file.transferTo(newFile);
        return "上传成功！";
    }
}
