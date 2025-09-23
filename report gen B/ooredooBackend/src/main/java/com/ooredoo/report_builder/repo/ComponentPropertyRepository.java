package com.ooredoo.report_builder.repo;


import com.ooredoo.report_builder.entity.ComponentProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentPropertyRepository extends JpaRepository<ComponentProperty, Integer> {

    List<ComponentProperty> findByComponentId(Integer elementId);
}
