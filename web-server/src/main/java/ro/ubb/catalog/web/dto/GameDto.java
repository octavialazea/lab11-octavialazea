package ro.ubb.catalog.web.dto;

import lombok.*;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
public class GameDto extends BaseDto{
    private String name;
    private String company;
    private Integer price;
    private Double rating;
    private Long bestPlayerID;
}
