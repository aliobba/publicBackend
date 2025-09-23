package com.ooredoo.report_builder.mapper;


import com.ooredoo.report_builder.entity.UploadedFiles;
import com.ooredoo.report_builder.dto.UploadedFilesDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UploadedFilesMapper {



    @Mapping(target = "submissionValueId", expression = "java(uploadedFiles.getSubmissionValue() != null ? uploadedFiles.getSubmissionValue().getId(): null)")
    UploadedFilesDTO toUploadedFilesDTO(UploadedFiles uploadedFiles);

    @Mapping(target = "submissionValue", ignore = true) // Ignore the submissionValue field in automatic mapping
    UploadedFiles toUploadedFiles(UploadedFilesDTO uploadedFilesDTO);



}
