package cl.copeuch.challenge.dto.user.mapper;

import cl.copeuch.challenge.dto.user.UserDto;
import cl.copeuch.challenge.enums.UserRole;
import cl.copeuch.challenge.model.UserEntity;
import cl.copeuch.challenge.services.user.impl.UserDetailsImpl;
import java.util.UUID;

public class UserMapper {

    public static UserDto toDto(UserEntity userEntity) {
        return userEntity != null ? UserDto.builder()
                .uuid(userEntity.getUuid())
                .username(userEntity.getUsername())
                .role(userEntity.getRole())
                .build() : null;
    }

    public static UserEntity toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        UUID uuid = userDto.uuid();
        String username = userDto.username();
        UserRole role = userDto.role();

        return new UserEntity(uuid, username, null, role);
    }

    public static UserDto userDetailsImplToUserDto(UserDetailsImpl userDetails) {
        if (userDetails == null) {
            return null;
        }

        var userDto = UserDto.builder();

        userDto.uuid(userDetails.getUuid());
        userDto.username(userDetails.getUsername());
        userDto.role(UserRole.getValue(userDetails.getAuthorities().get(0).getAuthority()));

        return userDto.build();
    }
}
