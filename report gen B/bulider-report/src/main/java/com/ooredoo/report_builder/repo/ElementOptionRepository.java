package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.ElementOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElementOptionRepository extends JpaRepository<ElementOption, Integer> {

    List<ElementOption> findByComponentIdOrderByDisplayOrderAsc(Integer elementId);
}
