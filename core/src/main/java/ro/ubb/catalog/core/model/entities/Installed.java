package ro.ubb.catalog.core.model.entities;

import lombok.*;

import javax.persistence.Entity;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
public class Installed extends BaseEntity<Long>{
    private Long gameID;
    private Long computerID;
    private Integer performanceGrade;
}
