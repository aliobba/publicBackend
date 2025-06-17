package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.UploadedFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UploadedFileRepository extends JpaRepository<UploadedFiles, Integer> {


    List<UploadedFiles> findBySubmissionValueId(Integer submissionValueId);
}
