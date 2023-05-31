package cl.copeuch.challenge.controller.task;

import cl.copeuch.challenge.dto.task.TaskDto;
import cl.copeuch.challenge.dto.task.TaskRequestDto;
import cl.copeuch.challenge.exceptions.NotFoundRecordException;
import cl.copeuch.challenge.services.task.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@CrossOrigin(maxAge = 3600, allowCredentials = "true", origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/task")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public Page<TaskDto> showByPage(
            @RequestParam(value = "page", required = false) Pageable pageable,
            @RequestParam(value = "sort", required = false) Sort sort
    ) {

        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.ASC, "createdAt"));

        if (pageable != null && sort != null) {
            pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        } else if (pageable != null && sort == null) {
            pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "createdAt"));
        }

        return taskService.findAll(pageRequest);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<TaskDto> findByUuid(
            @PathVariable final UUID uuid
    ) throws NotFoundRecordException {
        return ResponseEntity.ok().body(taskService.findByUuid(uuid));
    }

    @PostMapping
    public ResponseEntity<TaskDto> saveTask(
            @RequestBody final TaskRequestDto taskRequestDto
    ) {
        return ResponseEntity.ok().body(taskService.save(taskRequestDto));
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<TaskDto> updateTask(
        @PathVariable final UUID uuid,
        @RequestBody final TaskRequestDto taskRequestDto
    ) throws NotFoundRecordException {
        return ResponseEntity.ok().body(taskService.update(uuid, taskRequestDto));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<TaskDto> deleteTask(
        @PathVariable final UUID uuid
    ) throws NotFoundRecordException {
        return ResponseEntity.ok().body(taskService.delete(uuid));
    }
}
