package ro.ubb.catalog.core.model.entities;

import lombok.*;
import javax.persistence.Entity;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
public class Player extends BaseEntity<Long> {
    private String name;
    private String dateOfBirth;
    private String email;

}
