package com.ooredoo.report_builder.repo;


import com.ooredoo.report_builder.entity.FormComponentAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormComponentAssignmentRepository extends JpaRepository<FormComponentAssignment, Integer> {

    // Basic assignment queries
    List<FormComponentAssignment> findByFormIdAndIsActiveOrderByOrderIndex(Integer formId, Boolean isActive);

    List<FormComponentAssignment> findByComponentIdAndIsActive(Integer componentId, Boolean isActive);

    Optional<FormComponentAssignment> findByFormIdAndComponentIdAndIsActive(Integer formId, Integer componentId, Boolean isActive);

    // ADDED: Find active assignments with component details (for form submission)
    @Query("SELECT fca FROM FormComponentAssignment fca " +
            "LEFT JOIN FETCH fca.component c " +
            "LEFT JOIN FETCH c.properties " +
            "LEFT JOIN FETCH c.options " +
            "WHERE fca.form.id = :formId AND fca.isActive = true " +
            "ORDER BY fca.orderIndex ASC")
    List<FormComponentAssignment> findActiveAssignmentsWithComponentDetails(@Param("formId") Integer formId);

    // Order management
    @Query("SELECT MAX(fca.orderIndex) FROM FormComponentAssignment fca WHERE fca.form.id = :formId AND fca.isActive = true")
    Optional<Integer> findMaxOrderIndexByFormId(@Param("formId") Integer formId);

    // Usage counts
    long countByComponentIdAndIsActive(Integer componentId, Boolean isActive);

    long countByFormIdAndIsActive(Integer formId, Boolean isActive);

    // Assignment history
    List<FormComponentAssignment> findByFormIdOrderByAssignedAtDesc(Integer formId);

    List<FormComponentAssignment> findByComponentIdOrderByAssignedAtDesc(Integer componentId);

    // Active assignments for a form
    @Query("SELECT fca FROM FormComponentAssignment fca " +
            "WHERE fca.form.id = :formId AND fca.isActive = true " +
            "ORDER BY fca.orderIndex ASC")
    List<FormComponentAssignment> findActiveAssignmentsByForm(@Param("formId") Integer formId);

    // Find assignment by ID with component details
    @Query("SELECT fca FROM FormComponentAssignment fca " +
            "LEFT JOIN FETCH fca.component c " +
            "LEFT JOIN FETCH c.properties " +
            "LEFT JOIN FETCH c.options " +
            "WHERE fca.id = :assignmentId AND fca.isActive = true")
    Optional<FormComponentAssignment> findActiveAssignmentWithDetails(@Param("assignmentId") Integer assignmentId);

    // Bulk operations
    @Modifying
    @Query("UPDATE FormComponentAssignment fca SET fca.isActive = false, fca.unassignedAt = CURRENT_TIMESTAMP " +
            "WHERE fca.form.id = :formId AND fca.isActive = true")
    void deactivateAllAssignmentsForForm(@Param("formId") Integer formId);

    @Modifying
    @Query("UPDATE FormComponentAssignment fca SET fca.isActive = false, fca.unassignedAt = CURRENT_TIMESTAMP " +
            "WHERE fca.component.id = :componentId AND fca.isActive = true")
    void deactivateAllAssignmentsForComponent(@Param("componentId") Integer componentId);

    // Assignment validation
    @Query("SELECT COUNT(fca) > 0 FROM FormComponentAssignment fca " +
            "WHERE fca.form.id = :formId AND fca.component.id = :componentId AND fca.isActive = true")
    boolean existsActiveAssignment(@Param("formId") Integer formId, @Param("componentId") Integer componentId);

    // Find assignments for multiple component instances
    @Query("SELECT fca FROM FormComponentAssignment fca " +
            "WHERE fca.form.id = :formId AND fca.component.id = :componentId AND fca.isActive = true " +
            "ORDER BY fca.orderIndex ASC")
    List<FormComponentAssignment> findAllActiveAssignmentsByFormAndComponent(@Param("formId") Integer formId, @Param("componentId") Integer componentId);

}
