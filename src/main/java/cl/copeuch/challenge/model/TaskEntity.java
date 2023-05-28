package cl.copeuch.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "tbl_task")
@Entity
@DynamicUpdate
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue
    private UUID uuid;
    @Column(columnDefinition = "text")
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private UserEntity user;
    @Column(name = "is_valid")
    private Boolean isValid;
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
