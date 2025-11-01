package com.ooredoo.report_builder.repo;


import com.ooredoo.report_builder.entity.Form;
import com.ooredoo.report_builder.enums.ComponentType;
import com.ooredoo.report_builder.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FormRepository extends JpaRepository<Form, Integer> {


    List<Form> findByCreator(User creator);

    //List<Form> findByCreatorOrderByCreationAtDesc(User creator);

    @Query("SELECT f FROM Form f JOIN f.assignedUsers u WHERE u.id = :userId")
    List<Form> findFormsByAssignedUserId(@Param("userId") Integer userId);

    @Query("SELECT f FROM Form f JOIN f.assignedEnterprises e WHERE e.id = :enterpriseId")
    List<Form> findFormsByAssignedEnterpriseId(@Param("enterpriseId") Integer enterpriseId);

    // Forms using a specific component
    @Query("SELECT DISTINCT f FROM Form f " +
            "JOIN FormComponentAssignment fca ON f.id = fca.form.id " +
            "WHERE fca.component.id = :componentId AND fca.isActive = true")
    List<Form> findFormsByComponentId(@Param("componentId") Integer componentId);

    // Forms with specific component types
    @Query("SELECT DISTINCT f FROM Form f " +
            "JOIN FormComponentAssignment fca ON f.id = fca.form.id " +
            "WHERE fca.component.elementType = :componentType AND fca.isActive = true")
    List<Form> findFormsByComponentType(@Param("componentType") ComponentType componentType);

    // Search forms
    @Query("SELECT f FROM Form f WHERE " +
            "(:searchTerm IS NULL OR LOWER(f.name_Form) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(f.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<Form> searchForms(@Param("searchTerm") String searchTerm);

    // Form statistics
    @Query("SELECT f.id, COUNT(fca) as componentCount FROM Form f " +
            "LEFT JOIN FormComponentAssignment fca ON f.id = fca.form.id AND fca.isActive = true " +
            "GROUP BY f.id")
    List<Object[]> getFormComponentCounts();

    // Recently created forms
    @Query("SELECT f FROM Form f ORDER BY f.creation_Date DESC")
    List<Form> findRecentForms(Pageable pageable);

    // Forms by date range
    @Query("SELECT f FROM Form f WHERE f.creation_Date BETWEEN :startDate AND :endDate")
    List<Form> findFormsCreatedBetween(@Param("startDate") LocalDateTime startDate,
                                       @Param("endDate") LocalDateTime endDate);
}
