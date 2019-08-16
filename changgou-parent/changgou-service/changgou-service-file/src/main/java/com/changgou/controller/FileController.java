package com.changgou.controller;

import com.changgou.file.FastDFSFile;
import com.changgou.util.FastDFSClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {
    /**
     * 文件上传方式一，通过封装到pojo
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    public String upload(MultipartFile file) throws Exception {
        // 获得文件名
        String name = file.getOriginalFilename();
        // 获得文件的内容
        byte[] content = file.getBytes();
        // 获得文件的拓展名
        String ext = FilenameUtils.getExtension(name);
        FastDFSFile fastDFSFile = new FastDFSFile(name,ext,content);
        // 调用工具类：所在的组、以及附件所在的目录
        String[] uploadResult = FastDFSClient.uploadFile(fastDFSFile);
        // 拼接该附件的url地址 uploadResult[0]：拿到的是组名 uploadResult[1]：拿到的是所在的目录
        String url = FastDFSClient.getServerUrl() + "/" + uploadResult[0] + "/" + uploadResult[1];
        System.out.println(uploadResult[0] + ".........." + uploadResult[1]);
        return url;
    }

    /**
     * 文件上传方式二，通过直接传递参数
     * @param file
     * @return
     */
    @PostMapping("/uploadB")
    public String uploadB(MultipartFile file) throws Exception {
        byte[] file_buff = file.getBytes();
        String filename = file.getOriginalFilename();
        String file_ext_name = FilenameUtils.getExtension(filename);
        // 调用工具类：所在的组 以及 附件所在的目录
        String[] uploadResult = FastDFSClient.uploadFileB(file_buff, file_ext_name, null);
        // 拼接该附件的url地址
        String url = FastDFSClient.getServerUrl() + "/" + uploadResult[0] + "/" + uploadResult[1];
        return url;
    }
}
