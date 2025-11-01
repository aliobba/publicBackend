package com.ooredoo.report_builder.repo;


import com.ooredoo.report_builder.entity.ComponentProperty;
import com.ooredoo.report_builder.entity.FormComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComponentPropertyRepository extends JpaRepository<ComponentProperty, Integer> {

    List<ComponentProperty> findByComponent(FormComponent component);

    List<ComponentProperty> findByComponentId(Integer componentId);

    Optional<ComponentProperty> findByComponentIdAndPropertyName(Integer componentId, String propertyName);

    @Query("SELECT cp FROM ComponentProperty cp WHERE cp.component.id = :componentId ORDER BY cp.propertyName")
    List<ComponentProperty> findByComponentIdOrderByPropertyName(@Param("componentId") Integer componentId);

    // Bulk operations
    void deleteByComponentId(Integer componentId);

    @Modifying
    @Query("DELETE FROM ComponentProperty cp WHERE cp.component.id IN :componentIds")
    void deleteByComponentIds(@Param("componentIds") List<Integer> componentIds);

    // Property validation
    boolean existsByComponentIdAndPropertyName(Integer componentId, String propertyName);

    // Get properties as map
    @Query("SELECT cp.propertyName, cp.propertyValue FROM ComponentProperty cp WHERE cp.component.id = :componentId")
    List<Object[]> getPropertiesAsKeyValue(@Param("componentId") Integer componentId);

    // Property statistics
    @Query("SELECT cp.propertyName, COUNT(cp) FROM ComponentProperty cp GROUP BY cp.propertyName ORDER BY COUNT(cp) DESC")
    List<Object[]> getPropertyUsageStatistics();

    ;
}
