package cl.copeuch.challenge.repository;

import cl.copeuch.challenge.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {
}
