package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.SubmissionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionValueRepository extends JpaRepository<SubmissionValue, Integer> {

    List<SubmissionValue> findBySubmissionId(Integer submissionId);
}
