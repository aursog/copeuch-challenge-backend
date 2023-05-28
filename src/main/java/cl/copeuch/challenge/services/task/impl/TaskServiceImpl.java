package cl.copeuch.challenge.services.task.impl;

import cl.copeuch.challenge.dto.task.TaskDto;
import cl.copeuch.challenge.dto.task.TaskRequestDto;
import cl.copeuch.challenge.dto.task.mapper.TaskMapper;
import cl.copeuch.challenge.dto.user.mapper.UserMapper;
import cl.copeuch.challenge.enums.UserRole;
import cl.copeuch.challenge.exceptions.NotFoundRecordException;
import cl.copeuch.challenge.repository.TaskRepository;
import cl.copeuch.challenge.services.task.TaskService;
import cl.copeuch.challenge.services.user.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Page<TaskDto> findAll(Pageable page) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new PageImpl<>(StreamSupport.stream(taskRepository.findAll(page).spliterator(), true)
                        .filter(entity -> user.getAuthorities().get(0).getAuthority().contains(UserRole.member.toString()) ? entity.getUser().getUsername().equals(user.getUsername()) : true)
                        .map(TaskMapper::toDto)
                        .collect(Collectors.toList()));
    }

    @Override
    public TaskDto findByUuid(UUID uuid) throws NotFoundRecordException {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return taskRepository.findById(uuid)
                .filter(entity -> user.getAuthorities().get(0).getAuthority().contains(UserRole.member.toString()) ? entity.getUser().getUsername().equals(user.getUsername()) : true)
                .map(TaskMapper::toDto)
                .orElseThrow(() -> new NotFoundRecordException("Card with uuid -> ${uuid} not found"));
    }

    @Override
    public TaskDto save(TaskRequestDto taskRequestDto) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        TaskDto taskDto = TaskDto.builder()
                .description(taskRequestDto.description())
                .userDto(UserMapper.userDetailsImplToUserDto(user))
                .isValid(taskRequestDto.isValid())
                .build();

        return TaskMapper.toDto(taskRepository.save(TaskMapper.toEntity(taskDto)));
    }

    @Override
    public TaskDto update(UUID uuid, TaskRequestDto taskRequestDto) throws NotFoundRecordException {
        TaskDto taskDto  = this.findByUuid(uuid);
        TaskDto updatedTask = new TaskDto(taskDto.uuid(), taskRequestDto.description(), taskDto.userDto(), taskRequestDto.isValid());

        return TaskMapper.toDto(taskRepository.save(TaskMapper.toEntity(updatedTask)));
    }

    @Override
    public TaskDto delete(UUID uuid) throws NotFoundRecordException {
        TaskDto taskDto = this.findByUuid(uuid);
        taskRepository.delete(TaskMapper.toEntity(taskDto));
        return taskDto;
    }
}
