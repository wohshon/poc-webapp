package com.demo.sgcustom.web;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadBean {
    private MultipartFile uploadFile;

    public MultipartFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(MultipartFile file) {
        this.uploadFile = file;
    }
}