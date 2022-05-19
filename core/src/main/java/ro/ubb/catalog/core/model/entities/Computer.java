package ro.ubb.catalog.core.model.entities;

import lombok.*;

import javax.persistence.Entity;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
public class Computer extends BaseEntity<Long>{
    private Long zoneID;
    private String operatingSystem;
    private String purchaseDate;


}
