package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.FormComponent;
import com.ooredoo.report_builder.enums.ComponentType;
import com.ooredoo.report_builder.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormComponentRepository extends JpaRepository<FormComponent, Integer> {

    List<FormComponent> findByElementType(ComponentType elementType);

    List<FormComponent> findByCreatedBy(User createdBy);

    List<FormComponent> findByIsGlobalAndCreatedBy(Boolean isGlobal, User createdBy);

    // Global components
    List<FormComponent> findByIsGlobal(Boolean isGlobal);

    @Query("SELECT fc FROM FormComponent fc WHERE fc.isGlobal = true OR fc.createdBy.id = :userId")
    List<FormComponent> findAvailableComponentsForUser(@Param("userId") Integer userId);

    // Reusable components for a user
    @Query("SELECT DISTINCT fc FROM FormComponent fc " +
            "LEFT JOIN fc.formAssignments fa " +
            "LEFT JOIN fa.form f " +// ✅ Now 'forms' exists!
            "LEFT JOIN f.assignedUsers u " +
            "WHERE fc.isGlobal = true " +
            "OR fc.createdBy.id = :userId " +
            "OR u.id = :userId")
    List<FormComponent> findReusableComponentsForUser(@Param("userId") Integer userId);

    // Search components
    @Query("SELECT fc FROM FormComponent fc WHERE " +
            "(:elementType IS NULL OR fc.elementType = :elementType) AND " +
            "(:searchTerm IS NULL OR LOWER(fc.componentName) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<FormComponent> searchComponents(@Param("elementType") ComponentType elementType,
                                         @Param("searchTerm") String searchTerm);

    // Component usage statistics
    @Query("SELECT fc.id, COUNT(fca) as usageCount FROM FormComponent fc " +
            "LEFT JOIN FormComponentAssignment fca ON fc.id = fca.component.id AND fca.isActive = true " +
            "GROUP BY fc.id")
    List<Object[]> getComponentUsageStatistics();

    // Find generic component by type (for reuse)
    @Query("SELECT fc FROM FormComponent fc WHERE fc.elementType = :elementType AND fc.isGlobal = true")
    List<FormComponent> findGenericComponentsByType(@Param("elementType") ComponentType elementType);

    // Find or create generic component by type
    @Query("SELECT fc FROM FormComponent fc WHERE fc.elementType = :elementType AND fc.isGlobal = true")
    Optional<FormComponent> findFirstGenericComponentByType(@Param("elementType") ComponentType elementType);
}
