package com.changgou.util;

import com.changgou.file.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.Nullable;

import java.io.IOException;

/**
 * 操作文件上传的工具类
 */
public class FastDFSClient {
    /**
     * 初始化FastDFS配置
     */
    static {
        // 获得工程里面的配置文件
        String config_name = new ClassPathResource("fdfs_client.conf").getPath();
        try {
            // 初始化FastDFS的配置文件
            ClientGlobal.init(config_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传方式一，通过封装到pojo
     * @param fastDFSFile
     * @return
     */
    public static String[] uploadFile(FastDFSFile fastDFSFile) {
        try {
            // 获得文件内容
            byte[] file_buff = fastDFSFile.getContent();
            // 获得文件的拓展名
            String file_ext_name = fastDFSFile.getExt();
            // 获得文件的备注信息
            NameValuePair[] meta_list = new NameValuePair[1];
            meta_list[0] = new NameValuePair(fastDFSFile.getAuthor());
            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、通过跟踪服务器client获取服务器端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、创建存储服务器客户端
            StorageClient storageClient = new StorageClient(trackerServer,null);
            // 4、将附件上传
            String[] uploadResult = storageClient.upload_file(file_buff, file_ext_name, meta_list);
            return uploadResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件上传方式二，通过直接传递参数
     * @param file_buff  文件内容
     * @param file_ext_name  文件扩展名
     * @param des  文件描述
     * @return
     */
    public static String[] uploadFileB(byte[] file_buff, String file_ext_name, @Nullable String des) {
        try {
            NameValuePair[] meta_list = new NameValuePair[1];
            meta_list[0] = new NameValuePair(des);
            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、通过跟踪服务器client获取服务器端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、创建存储服务器客户端
            StorageClient storageClient = new StorageClient(trackerServer,null);
            // 4、将附件上传
            String[] uploadResult = storageClient.upload_file(file_buff, file_ext_name, meta_list);
            return uploadResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件下载
     * @param group_name  组名
     * @param remote_filename  服务器上的文件名称
     * @return
     */
    public static byte[] downloadFile(String group_name, String remote_filename) {
        try {
            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、通过跟踪服务器client获取服务器端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、创建存储服务器客户端
            StorageClient storageClient = new StorageClient();
            // 4、文件下载
            byte[] downloadFile = storageClient.download_file(group_name, remote_filename);
            return downloadFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件删除
     * @param group_name
     * @param remote_filename
     */
    public static void deleteFile(String group_name, String remote_filename) {
        try {
            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、通过跟踪服务器client获取服务器端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、创建存储服务器客户端
            StorageClient storageClient = new StorageClient(trackerServer,null);
            // 4、文件删除
            storageClient.delete_file(group_name,remote_filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取附件信息
     * @param group_name
     * @param remote_filename
     * @return
     */
    public static FileInfo getFileInfo(String group_name, String remote_filename) {
        try {
            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、通过跟踪服务器client获取服务器端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、创建存储服务器客户端
            StorageClient storageClient = new StorageClient();
            // 4、文件信息获取
            FileInfo fileInfo = storageClient.get_file_info(group_name,remote_filename);
            return fileInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取存储服务器信息
     * @param groupName  组名
     * @return
     */
    public static StorageServer getStoreStorage(String groupName) {
        try {
            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、通过跟踪服务器client获取服务器端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、获取存储服务器信息
            StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer, groupName);
            return storeStorage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取多个存储服务器
     * @param groupName
     * @param filename
     * @return
     */
    public static ServerInfo[] getServerInfos(String groupName, String filename) {
        try {
            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、通过跟踪服务器client获取服务器端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、获取多个存储服务器信息
            ServerInfo[] serverInfos = trackerClient.getFetchStorages(trackerServer, groupName, filename);
            return serverInfos;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取服务器地址
     * @return
     */
    public static String getServerUrl() {
        try {
            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、通过跟踪服务器client获取服务器端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、拼接服务器地址
            String hostAddress = trackerServer.getInetSocketAddress().getAddress().getHostAddress();
            int port = ClientGlobal.getG_tracker_http_port();
            String url = "http://" + hostAddress + ":" + port;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
