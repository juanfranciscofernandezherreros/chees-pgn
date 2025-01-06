package com.example.chesspgn.dto;

public class FilePathRequest {
    private String filePath;

    public FilePathRequest() {
    }

    public FilePathRequest(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
