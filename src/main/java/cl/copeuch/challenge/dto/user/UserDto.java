package cl.copeuch.challenge.dto.user;

import cl.copeuch.challenge.enums.UserRole;
import lombok.Builder;
import java.util.UUID;

@Builder
public record UserDto (
        UUID uuid,
        String username,
        UserRole role
) { }
