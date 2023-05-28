package cl.copeuch.challenge.dto.task.mapper;

import cl.copeuch.challenge.dto.task.TaskDto;
import cl.copeuch.challenge.dto.user.mapper.UserMapper;
import cl.copeuch.challenge.model.TaskEntity;
import cl.copeuch.challenge.model.UserEntity;
import java.util.UUID;

public class TaskMapper {

    public static TaskDto toDto(TaskEntity entity) {
        return entity != null ? TaskDto.builder()
                .uuid(entity.getUuid())
                .description(entity.getDescription())
                .userDto(UserMapper.toDto(entity.getUser()))
                .isValid(entity.getIsValid())
                .build() : null;
    }

    public static TaskEntity toEntity(TaskDto dto) {
        if (dto == null) {
            return null;
        }

        UUID uuid = dto.uuid();
        String description = dto.description();
        UserEntity user = UserMapper.toEntity(dto.userDto());
        Boolean isValid = dto.isValid();

        return new TaskEntity(uuid, description, user, isValid, null, null);
    }
}
