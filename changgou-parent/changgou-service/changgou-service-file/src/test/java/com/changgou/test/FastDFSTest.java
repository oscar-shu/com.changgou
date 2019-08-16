package com.changgou.test;

import com.changgou.util.FastDFSClient;
import org.apache.commons.io.IOUtils;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.util.Date;

/**
 * 文件其他操作的测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FastDFSTest {
    /**
     * 下载附件
     */
    @Test
    public void testDoloadFile() throws Exception {
        // 组名
        String group_name = "group1";
        // 组名下对应的文件的位置
        String remote_filename = "M00/00/00/wKjThF1RJeqAbjCdAArT6gqTmEQ826.jpg";
        // 文件下载的方法
        byte[] downloadFile = FastDFSClient.downloadFile(group_name,remote_filename);
        // 下载到本地的位置
        IOUtils.write(downloadFile,new FileOutputStream("f:/3.jpg"));
    }

    /**
     * 文件删除
     * @throws Exception
     */
    @Test
    public void testDeleteFile() throws Exception {
        // 组名
        String group_name = "group1";
        // 组名下对应的文件的位置
        String remote_filename = "M00/00/00/wKjThF1RLfGAbUOwAArT6gqTmEQ517.jpg";
        // 文件删除的方法
        FastDFSClient.deleteFile(group_name,remote_filename);
    }

    /**
     * 获取附件信息
     * rc16算法  +  crc32算法
     * 集群状态下：计算服务器的节点（redis01 redis02 redis03      s1 s2 s3）
     */
    @Test
    public void testFileInfo() {
        // 组名
        String group_name = "group1";
        // 组名下对应的文件的位置
        String remote_filename = "M00/00/00/wKjThF1RLnCAEtzCAArT6gqTmEQ801.jpg";
        // 文件信息获取的方法
        FileInfo fileInfo = FastDFSClient.getFileInfo(group_name, remote_filename);
        String ipAddr = fileInfo.getSourceIpAddr();
        long fileSize = fileInfo.getFileSize();
        Date date = fileInfo.getCreateTimestamp();
        System.out.println("附件所在的服务器地址：" + ipAddr);
        System.out.println("附件大小：" + fileSize);
        System.out.println("附件创建日期：" + date);
    }

    /**
     * 获取存储服务器信息
     */
    @Test
    public void testStorageServer() {
        // 组名
        String groupName = "group1";
        StorageServer storeStorage = FastDFSClient.getStoreStorage(groupName);
        String hostAddress = storeStorage.getInetSocketAddress().getAddress().getHostAddress();
        int index = storeStorage.getStorePathIndex();
        int port = storeStorage.getInetSocketAddress().getPort();
        System.out.println("存储服务器地址：" + hostAddress);
        System.out.println("存储服务器脚标：" + index);
        System.out.println("存储服务器端口：" + port);
    }

    /**
     * 获取多个存储服务器
     */
    @Test
    public void testStorageServers() {
        String groupName = "group1";
        String filename = "M00/00/00/wKjThF1RLnCAEtzCAArT6gqTmEQ801.jpg";
        // 获取多个存储服务器信息
        ServerInfo[] serverInfos = FastDFSClient.getServerInfos(groupName, filename);
        // 因为只有一个，所以直接0第一个就可以
        ServerInfo serverInfo = serverInfos[0];
        String ipAddr = serverInfo.getIpAddr();
        int port = serverInfo.getPort();
        System.out.println("服务器地址：" + ipAddr);
        System.out.println("服务器端口：" + port);
    }
}
