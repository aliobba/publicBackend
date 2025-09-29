package com.ooredoo.report_builder.mapper;


import com.ooredoo.report_builder.dto.UploadedFilesDTO;
import com.ooredoo.report_builder.entity.UploadedFiles;
import org.springframework.stereotype.Component;

@Component
public class UploadedFilesMapper {

    public UploadedFiles toUploadedFiles(UploadedFilesDTO dto) {
        if (dto == null) return null;

        UploadedFiles entity = new UploadedFiles();
        entity.setOriginalFileName(dto.getOriginalFileName());
        entity.setStoredFileName(dto.getStoredFileName());
        entity.setFileType(dto.getFileType());
        entity.setFileSize(dto.getFileSize());
        entity.setFilePath(dto.getFilePath());
        return entity;
    }

    public UploadedFilesDTO toUploadedFilesDTO(UploadedFiles entity) {
        if (entity == null) return null;

        UploadedFilesDTO dto = new UploadedFilesDTO();
        dto.setId(entity.getId());
        dto.setOriginalFileName(entity.getOriginalFileName());
        dto.setStoredFileName(entity.getStoredFileName());
        dto.setFileType(entity.getFileType());
        dto.setFileSize(entity.getFileSize());
        dto.setFilePath(entity.getFilePath());
        return dto;
    }
}
