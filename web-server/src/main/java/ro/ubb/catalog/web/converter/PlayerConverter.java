package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.entities.Player;
import ro.ubb.catalog.web.dto.PlayerDto;

@Component
public class PlayerConverter extends BaseConverter<Player, PlayerDto>{
    @Override
    public Player convertDtoToModel(PlayerDto dto) {
        var model = new Player();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setDateOfBirth(dto.getDateOfBirth());
        model.setEmail(dto.getEmail());
        return model;
    }

    @Override
    public PlayerDto convertModelToDto(Player player) {
        PlayerDto dto = new PlayerDto(player.getName(), player.getDateOfBirth(), player.getEmail());
        dto.setId(player.getId());
        return dto;
    }
}
