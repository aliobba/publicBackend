package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.FormComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormComponentRepository extends JpaRepository<FormComponent, Integer> {

    List<FormComponent> findByFormIdOrderByOrderIndexAsc(Integer formId);
}
