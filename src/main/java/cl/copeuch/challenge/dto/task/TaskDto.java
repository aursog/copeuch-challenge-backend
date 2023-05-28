package cl.copeuch.challenge.dto.task;

import cl.copeuch.challenge.dto.user.UserDto;
import lombok.Builder;
import java.util.UUID;

@Builder
public record TaskDto (
        UUID uuid,
        String description,
        UserDto userDto,
        Boolean isValid
) { }
