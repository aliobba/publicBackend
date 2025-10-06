package com.ooredoo.report_builder.mapper;

import com.ooredoo.report_builder.dto.response.UserResponseDTO;
import com.ooredoo.report_builder.user.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-06T15:33:45+0200",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDTO toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO.UserResponseDTOBuilder userResponseDTO = UserResponseDTO.builder();

        userResponseDTO.accountLocked( user.isAccountLocked() );
        userResponseDTO.email( user.getEmail() );
        userResponseDTO.enabled( user.isEnabled() );
        userResponseDTO.firstname( user.getFirstname() );
        userResponseDTO.id( user.getId() );
        userResponseDTO.lastname( user.getLastname() );

        return userResponseDTO.build();
    }
}
