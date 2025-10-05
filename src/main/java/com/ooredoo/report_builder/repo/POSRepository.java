package com.ooredoo.report_builder.repo;

import com.ooredoo.report_builder.entity.POS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface POSRepository extends JpaRepository<POS, Integer> {
    Optional<POS> findByName(String name);
    boolean existsByName(String name);
    List<POS> findBySectorId(Integer sectorId);
    boolean existsByHeadOfPOSId(Integer userId);
    List<POS> findByHeadOfPOSIsNull();
    Optional<POS> findByHeadOfPOSId(Integer headOfPOSId); // head of POS
    Optional<POS> findByCodePOS(String codePOS);
    //List<POS> findByUsers_Id(Integer userId);
    }