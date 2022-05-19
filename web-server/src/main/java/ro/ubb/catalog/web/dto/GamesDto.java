package ro.ubb.catalog.web.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GamesDto {
    private Iterable<GameDto> games;
}
