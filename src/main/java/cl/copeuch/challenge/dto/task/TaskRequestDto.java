package cl.copeuch.challenge.dto.task;

import com.sun.istack.NotNull;

public record TaskRequestDto(
        @NotNull
        String description,
        @NotNull
        Boolean isValid
) {
}
