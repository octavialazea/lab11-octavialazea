package ro.ubb.catalog.web.dto;

import lombok.*;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
public class ComputerDto extends BaseDto{

    private Long zoneID;
    private String operatingSystem;
    private String purchaseDate;
}
