package com.changgou.file;

import java.io.Serializable;

/**
 * 封装附件信息
 */
public class FastDFSFile implements Serializable {
    private String name;   // 附件名称
    private String ext;    // 附件的扩展名称
    private byte[] content;// 附件内容
    private String md5;    // 附件的备注信息
    private String author; // 文件作者

    public FastDFSFile(String name, String ext, byte[] content, String md5, String author) {
        this.name = name;
        this.ext = ext;
        this.content = content;
        this.md5 = md5;
        this.author = author;
    }

    public FastDFSFile(String name, String ext, byte[] content) {
        this.name = name;
        this.ext = ext;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
