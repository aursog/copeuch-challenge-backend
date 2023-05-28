package cl.copeuch.challenge.model;

import cl.copeuch.challenge.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Table(name = "tbl_user")
@Entity
@DynamicUpdate
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    private UUID uuid;
    @Column
    private String username;
    @Column
    private String passwd;
    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;

}
