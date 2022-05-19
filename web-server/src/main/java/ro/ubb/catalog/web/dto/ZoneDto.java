package ro.ubb.catalog.web.dto;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
public class ZoneDto extends BaseDto{
    private String name;
    private String theme;
    private Integer capacity;
}
