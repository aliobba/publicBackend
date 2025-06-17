package com.ooredoo.report_builder.mapper;

import com.ooredoo.report_builder.dto.*;
import com.ooredoo.report_builder.dto.request.*;
import com.ooredoo.report_builder.dto.response.*;
import com.ooredoo.report_builder.entity.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EntityMapper {
    
    // Enterprise mappings
    EnterpriseDTO toDTO(Enterprise enterprise);
    Enterprise toEntity(EnterpriseDTO dto);
    Enterprise toEntity(CreateEnterpriseRequest request);
    EnterpriseResponse toResponse(Enterprise enterprise);
    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(UpdateEnterpriseRequest request, @MappingTarget Enterprise enterprise);
    
    // POS mappings
    POSDTO toDTO(POS pos);
    POS toEntity(POSDTO dto);
    POS toEntity(CreatePOSRequest request);
    POSResponse toResponse(POS pos);
    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(UpdatePOSRequest request, @MappingTarget POS pos);
    
    // Sector mappings
    SectorDTO toDTO(Sector sector);
    Sector toEntity(SectorDTO dto);
    Sector toEntity(CreateSectorRequest request);
    SectorResponse toResponse(Sector sector);
    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(UpdateSectorRequest request, @MappingTarget Sector sector);
    
    // Zone mappings
    ZoneDTO toDTO(Zone zone);
    Zone toEntity(ZoneDTO dto);
    Zone toEntity(CreateZoneRequest request);
    ZoneResponse toResponse(Zone zone);
    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(UpdateZoneRequest request, @MappingTarget Zone zone);
    
    // Region mappings
    RegionDTO toDTO(Region region);
    Region toEntity(RegionDTO dto);
    Region toEntity(CreateRegionRequest request);
    RegionResponse toResponse(Region region);
    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(UpdateRegionRequest request, @MappingTarget Region region);
    
    @AfterMapping
    default void linkEntities(@MappingTarget Enterprise enterprise) {
        if (enterprise.getPointsOfSale() != null) {
            enterprise.getPointsOfSale().forEach(pos -> pos.setEnterprise(enterprise));
        }
        if (enterprise.getSectors() != null) {
            enterprise.getSectors().forEach(sector -> sector.setEnterprise(enterprise));
        }
    }
    
    @AfterMapping
    default void linkEntities(@MappingTarget Sector sector) {
        if (sector.getZones() != null) {
            sector.getZones().forEach(zone -> zone.setSector(sector));
        }
    }
    
    @AfterMapping
    default void linkEntities(@MappingTarget Zone zone) {
        if (zone.getRegions() != null) {
            zone.getRegions().forEach(region -> region.setZone(zone));
        }
    }
    
    @AfterMapping
    default void linkEntities(@MappingTarget Region region) {
        if (region.getPointsOfSale() != null) {
            region.getPointsOfSale().forEach(pos -> pos.setRegion(region));
        }
    }
}