package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.ElementOption;
import com.ooredoo.report_builder.entity.FormComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ElementOptionRepository extends JpaRepository<ElementOption, Integer> {

    List<ElementOption> findByComponentIdOrderByDisplayOrderAsc(Integer componentId);

    List<ElementOption> findByComponentOrderByDisplayOrderAsc(FormComponent component);

    @Query("SELECT MAX(eo.displayOrder) FROM ElementOption eo WHERE eo.component.id = :componentId")
    Optional<Integer> findMaxDisplayOrderByComponentId(@Param("componentId") Integer componentId);

    // Bulk operations
    void deleteByComponentId(Integer componentId);

    @Modifying
    @Query("DELETE FROM ElementOption eo WHERE eo.component.id IN :componentIds")
    void deleteByComponentIds(@Param("componentIds") List<Integer> componentIds);

    // Option validation
    boolean existsByComponentIdAndValue(Integer componentId, String value);

    boolean existsByComponentIdAndLabel(Integer componentId, String label);

    long countByComponentId(Integer componentId);

    // Find options by value
    List<ElementOption> findByComponentIdAndValueIn(Integer componentId, List<String> values);

    // Batch reorder
    @Modifying
    @Query("UPDATE ElementOption eo SET eo.displayOrder = :newOrder WHERE eo.id = :optionId")
    void updateDisplayOrder(@Param("optionId") Integer optionId, @Param("newOrder") Integer newOrder);

    // Option usage in submissions
    @Query("SELECT eo.value, COUNT(sv) FROM ElementOption eo " +
            "LEFT JOIN SubmissionValue sv ON sv.assignment.component = eo.component AND sv.value = eo.value " +
            "WHERE eo.component.id = :componentId " +
            "GROUP BY eo.value ORDER BY COUNT(sv) DESC")
    List<Object[]> getOptionUsageStatistics(@Param("componentId") Integer componentId);
}
