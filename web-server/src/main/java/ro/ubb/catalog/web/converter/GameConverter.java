package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.entities.Game;
import ro.ubb.catalog.web.dto.GameDto;

@Component
public class GameConverter extends BaseConverter<Game, GameDto> {
    @Override
    public Game convertDtoToModel(GameDto dto) {
        var model = new Game();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setCompany(dto.getCompany());
        model.setPrice(dto.getPrice());
        model.setRating(dto.getRating());
        model.setBestPlayerID(dto.getBestPlayerID());
        return model;
    }

    @Override
    public GameDto convertModelToDto(Game game) {
        GameDto dto = new GameDto(game.getName(), game.getCompany(), game.getPrice(), game.getRating(), game.getBestPlayerID());
        dto.setId(game.getId());
        return dto;
    }
}
