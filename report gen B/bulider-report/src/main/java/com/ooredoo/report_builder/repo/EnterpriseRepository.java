package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Integer> {
    Optional<Enterprise> findByName(String name);
    List<Enterprise> findByEnterpriseAdminId(Integer adminId);
    boolean existsByName(String name);
    List<Enterprise> findBySectorIdsContaining(Integer sectorId);
    List<Enterprise> findByPointsOfSaleIdsContaining(Integer posId);
}