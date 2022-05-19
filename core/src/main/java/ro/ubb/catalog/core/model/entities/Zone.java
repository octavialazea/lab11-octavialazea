package ro.ubb.catalog.core.model.entities;

import lombok.*;

import javax.persistence.Entity;
import java.util.Objects;
import java.util.Random;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
public class Zone extends BaseEntity<Long>{
    private String name;
    private String theme;
    private Integer capacity;

}
