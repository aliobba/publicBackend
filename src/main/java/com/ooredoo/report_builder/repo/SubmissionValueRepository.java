package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.FormComponent;
import com.ooredoo.report_builder.entity.FormComponentAssignment;
import com.ooredoo.report_builder.entity.FormSubmission;
import com.ooredoo.report_builder.entity.SubmissionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionValueRepository extends JpaRepository<SubmissionValue, Integer> {

    // Find values by submission
    List<SubmissionValue> findBySubmissionId(Integer submissionId);

    List<SubmissionValue> findBySubmission(FormSubmission submission);

    List<SubmissionValue> findBySubmissionIdOrderByAssignment_OrderIndex(Integer submissionId);

    // CHANGED: Find values by assignment (not component)
    List<SubmissionValue> findByAssignmentId(Integer assignmentId);

    List<SubmissionValue> findByAssignment(FormComponentAssignment assignment);

    // Find values by component (through assignment)
    @Query("SELECT sv FROM SubmissionValue sv WHERE sv.assignment.component.id = :componentId")
    List<SubmissionValue> findByComponentId(@Param("componentId") Integer componentId);

    // IMPORTANT: Cleanup by assignment (not component)
    @Modifying
    @Query("DELETE FROM SubmissionValue sv WHERE sv.assignment.id = :assignmentId")
    void deleteByAssignmentId(@Param("assignmentId") Integer assignmentId);

    // CHANGED: Form-specific cleanup using assignment
    @Modifying
    @Query("DELETE FROM SubmissionValue sv WHERE sv.assignment.form.id = :formId AND sv.assignment.component.id = :componentId")
    void deleteByFormIdAndComponentId(@Param("formId") Integer formId, @Param("componentId") Integer componentId);

    // Delete by submission and assignment
    @Modifying
    void deleteBySubmissionIdAndAssignmentId(Integer submissionId, Integer assignmentId);

    // Delete all values for a submission
    void deleteBySubmissionId(Integer submissionId);

    // Find specific value by submission and assignment
    Optional<SubmissionValue> findBySubmissionIdAndAssignmentId(Integer submissionId, Integer assignmentId);

    // Count values
    long countBySubmissionId(Integer submissionId);

    long countByAssignmentId(Integer assignmentId);

    // Component usage statistics (through assignments)
    @Query("SELECT sv.assignment.component.id, COUNT(sv) FROM SubmissionValue sv GROUP BY sv.assignment.component.id")
    List<Object[]> getComponentUsageInSubmissions();

    @Query("SELECT sv.value, COUNT(sv) FROM SubmissionValue sv WHERE sv.assignment.id = :assignmentId GROUP BY sv.value ORDER BY COUNT(sv) DESC")
    List<Object[]> getValueFrequencyForAssignment(@Param("assignmentId") Integer assignmentId);

    // Form submission statistics
    @Query("SELECT sv.assignment.form.id, COUNT(sv) FROM SubmissionValue sv GROUP BY sv.assignment.form.id")
    List<Object[]> getFormSubmissionCounts();

    // Query 1: Fetch submission values with assignments and components
    @Query("SELECT DISTINCT sv FROM SubmissionValue sv " +
            "JOIN FETCH sv.assignment a " +
            "JOIN FETCH a.component c " +
            "WHERE sv.submission.id = :submissionId")
    List<SubmissionValue> findBySubmissionIdWithDetails(@Param("submissionId") Integer submissionId);

    // Query 2: Batch fetch properties for components
    @Query("SELECT DISTINCT c FROM FormComponent c " +
            "LEFT JOIN FETCH c.properties " +
            "WHERE c.id IN :componentIds")
    List<FormComponent> fetchPropertiesForComponents(@Param("componentIds") List<Integer> componentIds);

    // Query 3: Batch fetch options for components
    @Query("SELECT DISTINCT c FROM FormComponent c " +
            "LEFT JOIN FETCH c.options " +
            "WHERE c.id IN :componentIds")
    List<FormComponent> fetchOptionsForComponents(@Param("componentIds") List<Integer> componentIds);

}
