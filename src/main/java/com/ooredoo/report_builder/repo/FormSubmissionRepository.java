package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.FormSubmission;
import com.ooredoo.report_builder.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface FormSubmissionRepository extends JpaRepository<FormSubmission, Integer> {

    // List<ElementOption> findByAssignmentIdOrderByDisplayOrderAsc(Integer assignmentId);
    // List<ElementOption> findByAssignmentOrderByDisplayOrderAsc(FormComponentAssignment assignment);

    @Query("SELECT MAX(eo.displayOrder) FROM ElementOption eo WHERE eo.component.id = :componentId")
    Optional<Integer> findMaxDisplayOrderByComponentId(@Param("componentId") Integer componentId);

    // Bulk operations
    // void deleteByAssignmentId(Integer assignmentId);

   /* @Modifying
    @Query("DELETE FROM ElementOption eo WHERE eo.component.id IN :componentIds")
    void deleteByComponentIds(@Param("componentIds") List<Integer> componentIds);
*/
    // Option validation
    //boolean existsByAssignmentIdAndValue(Integer assignmentId, String value);
    // boolean existsByAssignmentIdAndLabel(Integer assignmentId, String label);

    // long countByAssignmentId(Integer assignmentId);

    // Find options by value
    //List<ElementOption> findByAssignmentIdAndValueIn(Integer assignmentId, List<String> values);

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

    long countByFormId(Integer formId);

    List<FormSubmission> findByFormIdOrderBySubmittedDateDesc(Integer formId);

    List<FormSubmission> findBySubmittedByOrderBySubmittedDateDesc(User user);
}
