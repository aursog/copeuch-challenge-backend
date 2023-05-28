package cl.copeuch.challenge.services.task;

import cl.copeuch.challenge.dto.task.TaskDto;
import cl.copeuch.challenge.dto.task.TaskRequestDto;
import cl.copeuch.challenge.exceptions.NotFoundRecordException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface TaskService {

    Page<TaskDto> findAll(Pageable page);

    TaskDto findByUuid(UUID uuid) throws NotFoundRecordException;

    TaskDto save(TaskRequestDto cardRequestDto);

    TaskDto update(UUID uuid, TaskRequestDto cardRequestDto) throws NotFoundRecordException;

    TaskDto delete(UUID uuid) throws NotFoundRecordException;

}
