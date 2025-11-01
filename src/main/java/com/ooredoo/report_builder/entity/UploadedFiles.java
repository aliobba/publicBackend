package com.ooredoo.report_builder.entity;

import jakarta.persistence.*;

@Entity
public class UploadedFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String originalFileName;
    private String storedFileName;
    private String fileType;
    private Integer fileSize;
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_value_id")
    private SubmissionValue submissionValue;

    public UploadedFiles(Integer id, String originalFileName, String storedFileName, String fileType, Integer fileSize, String filePath, SubmissionValue submissionValue) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.submissionValue = submissionValue;
    }

    public UploadedFiles() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalFileName() {
        return this.originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getStoredFileName() {
        return this.storedFileName;
    }

    public void setStoredFileName(String storedFileName) {
        this.storedFileName = storedFileName;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public SubmissionValue getSubmissionValue() {
        return this.submissionValue;
    }

    public void setSubmissionValue(SubmissionValue submissionValue) {
        this.submissionValue = submissionValue;
    }
}
