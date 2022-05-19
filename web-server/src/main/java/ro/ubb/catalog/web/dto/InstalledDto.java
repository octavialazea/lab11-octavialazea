package ro.ubb.catalog.web.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
public class InstalledDto extends BaseDto{
    private Long gameID;
    private Long computerID;
    private Integer performanceGrade;
}
