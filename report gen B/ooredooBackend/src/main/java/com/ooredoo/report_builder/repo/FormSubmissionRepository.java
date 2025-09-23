package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.FormSubmission;
import com.ooredoo.report_builder.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FormSubmissionRepository extends JpaRepository<FormSubmission, Integer> {

    List<FormSubmission> findByFormId(Integer formId);
    List<FormSubmission> findBySubmittedBy(User user);
}
