package ro.ubb.catalog.web.dto;

import lombok.*;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
public class PlayerDto extends BaseDto{

    private String name;
    private String dateOfBirth;
    private String email;
}
