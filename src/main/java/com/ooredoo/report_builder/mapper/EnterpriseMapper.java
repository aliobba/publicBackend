package com.ooredoo.report_builder.mapper;

import com.ooredoo.report_builder.dto.EnterpriseResponseDTO;
import com.ooredoo.report_builder.dto.RoleInfoDTO;
import com.ooredoo.report_builder.dto.UserInfoDTO;
import com.ooredoo.report_builder.entity.Enterprise;
import com.ooredoo.report_builder.user.Role;
import com.ooredoo.report_builder.user.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnterpriseMapper {

    public EnterpriseResponseDTO toDto(Enterprise enterprise) {
        if (enterprise == null) {
            return null;
        }

        UserInfoDTO managerDto = null;
        if (enterprise.getManager() != null) {
            managerDto = toUserInfoDto(enterprise.getManager());
        }

        List<UserInfoDTO> usersDto = null;
        if (enterprise.getUsersInEnterprise() != null) {
            usersDto = enterprise.getUsersInEnterprise().stream()
                    .map(this::toUserInfoDto)
                    .collect(Collectors.toList());
        }

        return new EnterpriseResponseDTO(
                enterprise.getId(),
                enterprise.getEnterpriseName(),
                enterprise.getLogoUrl(),
                enterprise.getPrimaryColor(),
                enterprise.getSecondaryColor(),
                managerDto,
                usersDto,
                enterprise.getCreation_Date(),
                enterprise.getUpdatedAt_Date()
        );
    }

    public UserInfoDTO toUserInfoDto(User user) {
        if (user == null) {
            return null;
        }

        RoleInfoDTO roleDto = null;
        if (user.getRole() != null) {
            roleDto = toRoleInfoDto(user.getRole());
        }

        return new UserInfoDTO(
                user.getId(),
                user.getFirst_Name(),
                user.getLast_Name(),
                user.getEmail(),
                user.getBirthdate(),
                user.isEnabled(),
                user.isAccountLocked(),
                user.getPos_Code(),
                user.getUserType(),
                roleDto,
                user.getCreated_Date(),
                user.getUpdated_Date()
        );
    }

    public RoleInfoDTO toRoleInfoDto(Role role) {
        if (role == null) {
            return null;
        }

        return new RoleInfoDTO(
                role.getId(),
                role.getName(),
                role.getCreated_Date(),
                role.getUpdated_Date()
        );
    }
}
