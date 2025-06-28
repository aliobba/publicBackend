package com.ooredoo.report_builder.mapper;

import com.ooredoo.report_builder.dto.request.CreateEnterpriseRequest;
import com.ooredoo.report_builder.dto.request.UpdateEnterpriseRequest;
import com.ooredoo.report_builder.dto.response.EnterpriseResponse;
import com.ooredoo.report_builder.dto.response.POSSummaryDTO;
import com.ooredoo.report_builder.dto.response.SectorSummaryDTO;
import com.ooredoo.report_builder.dto.response.UserSummaryDTO;
import com.ooredoo.report_builder.entity.Enterprise;
import com.ooredoo.report_builder.entity.POS;
import com.ooredoo.report_builder.entity.Sector;
import com.ooredoo.report_builder.user.User;
import org.mapstruct.*;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {})
public interface EnterpriseMapper {

    // Entity to Response mapping
    @Mapping(target = "enterpriseAdmin", expression = "java(userToUserSummaryDTO(enterprise.getEnterpriseAdmin()))")
    @Mapping(target = "users", expression = "java(enterprise.getUsers() != null ? enterprise.getUsers().stream().map(this::userToUserSummaryDTO).collect(java.util.stream.Collectors.toSet()) : null)")
    @Mapping(target = "pointsOfSale", expression = "java(enterprise.getPointsOfSale() != null ? enterprise.getPointsOfSale().stream().map(this::posToSummaryDTO).collect(java.util.stream.Collectors.toSet()) : null)")
    @Mapping(target = "sectors", expression = "java(enterprise.getSectors() != null ? enterprise.getSectors().stream().map(this::sectorToSummaryDTO).collect(java.util.stream.Collectors.toSet()) : null)")
    EnterpriseResponse toResponse(Enterprise enterprise);
    
    // CreateRequest to Entity mapping
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enterpriseAdmin", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "pointsOfSale", ignore = true)
    @Mapping(target = "sectors", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", ignore = true)
    Enterprise toEntity(CreateEnterpriseRequest request);
    
    // UpdateRequest to Entity mapping (partial update)
    @Mapping(target = "id", ignore = true) // ID comes from path variable
    @Mapping(target = "enterpriseAdmin", ignore = true) // Handled separately in service
    @Mapping(target = "users", ignore = true) // Handled separately in service
    @Mapping(target = "pointsOfSale", ignore = true) // Handled separately in service
    @Mapping(target = "sectors", ignore = true) // Handled separately in service
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(UpdateEnterpriseRequest request, @MappingTarget Enterprise enterprise);
    
    // Named mappers for summary DTOs
    @Named("userToUserSummaryDTO")
    default UserSummaryDTO userToUserSummaryDTO(User user) {
        if (user == null) return null;
        
        UserSummaryDTO dto = new UserSummaryDTO();
        dto.setId(user.getId_user());
        dto.setUsername(user.getEmail()); // Using email as username
        dto.setFullName(user.getFirstname() + " " + user.getLastname());
        return dto;
    }
    
    @Named("usersToUserSummaryDTOs")
    default Set<UserSummaryDTO> usersToUserSummaryDTOs(Set<User> users) {
        if (users == null) return null;
        return users.stream()
                .map(this::userToUserSummaryDTO)
                .collect(java.util.stream.Collectors.toSet());
    }
    
    @Named("posToSummaryDTO")
    default POSSummaryDTO posToSummaryDTO(POS pos) {
        if (pos == null) return null;
        
        POSSummaryDTO dto = new POSSummaryDTO();
        dto.setId(pos.getId());
        dto.setName(pos.getName());
        dto.setLocation(pos.getLocation());
        return dto;
    }
    
    @Named("posesToPOSSummaryDTOs")
    default Set<POSSummaryDTO> posesToPOSSummaryDTOs(Set<POS> poses) {
        if (poses == null) return null;
        return poses.stream()
                .map(this::posToSummaryDTO)
                .collect(java.util.stream.Collectors.toSet());
    }
    
    @Named("sectorToSummaryDTO")
    default SectorSummaryDTO sectorToSummaryDTO(Sector sector) {
        if (sector == null) return null;
        
        SectorSummaryDTO dto = new SectorSummaryDTO();
        dto.setId(sector.getId());
        dto.setName(sector.getName());
        return dto;
    }
    
    @Named("sectorsToSectorSummaryDTOs")
    default Set<SectorSummaryDTO> sectorsToSectorSummaryDTOs(Set<Sector> sectors) {
        if (sectors == null) return null;
        return sectors.stream()
                .map(this::sectorToSummaryDTO)
                .collect(java.util.stream.Collectors.toSet());
    }
}