package com.ooredoo.report_builder.dto;

import com.ooredoo.report_builder.entity.SubmissionValue;

import java.time.LocalDateTime;

public class UploadedFilesDTO {

    private Integer id;
    private String originalFileName;
    private String storedFileName;
    private String fileType;
    private Integer fileSize;
    private String filePath;
    private Integer submissionValueId;


    // File metadata
    private LocalDateTime uploadedAt;
    private String downloadUrl;


    public UploadedFilesDTO(String originalFileName, String storedFileName, String fileType, Integer fileSize) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }


    public UploadedFilesDTO(Integer id, String originalFileName, String storedFileName, String fileType, Integer fileSize, String filePath, Integer submissionValueId) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.submissionValueId = submissionValueId;
    }

    public UploadedFilesDTO() {
    }

    public static UploadedFilesDTOBuilder builder() {
        return new UploadedFilesDTOBuilder();
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

    public Integer getSubmissionValueId() {
        return this.submissionValueId;
    }

    public void setSubmissionValueId(Integer submissionValueId) {
        this.submissionValueId = submissionValueId;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public void setSubmissionValue(SubmissionValue submissionValue) {
        this.submissionValueId = submissionValue.getId();
    }

    public static class UploadedFilesDTOBuilder {
        private Integer id;
        private String originalFileName;
        private String storedFileName;
        private String fileType;
        private Integer fileSize;
        private String filePath;
        private Integer submissionValueId;

        UploadedFilesDTOBuilder() {
        }

        public UploadedFilesDTOBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public UploadedFilesDTOBuilder originalFileName(String originalFileName) {
            this.originalFileName = originalFileName;
            return this;
        }

        public UploadedFilesDTOBuilder storedFileName(String storedFileName) {
            this.storedFileName = storedFileName;
            return this;
        }

        public UploadedFilesDTOBuilder fileType(String fileType) {
            this.fileType = fileType;
            return this;
        }

        public UploadedFilesDTOBuilder fileSize(Integer fileSize) {
            this.fileSize = fileSize;
            return this;
        }

        public UploadedFilesDTOBuilder filePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public UploadedFilesDTOBuilder submissionValueId(Integer submissionValueId) {
            this.submissionValueId = submissionValueId;
            return this;
        }

        public UploadedFilesDTO build() {
            return new UploadedFilesDTO(this.id, this.originalFileName, this.storedFileName, this.fileType, this.fileSize, this.filePath, this.submissionValueId);
        }

        public String toString() {
            return "UploadedFilesDTO.UploadedFilesDTOBuilder(id=" + this.id + ", originalFileName=" + this.originalFileName + ", storedFileName=" + this.storedFileName + ", fileType=" + this.fileType + ", fileSize=" + this.fileSize + ", filePath=" + this.filePath + ", submissionValue=" + this.submissionValueId + ")";
        }
    }
}
