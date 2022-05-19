package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.entities.Game;
import ro.ubb.catalog.core.service.ServicePlayer;
import ro.ubb.catalog.web.converter.GameConverter;
import ro.ubb.catalog.web.dto.GameDto;
import ro.ubb.catalog.web.dto.GamesDto;
import ro.ubb.catalog.web.dto.PlayersDto;

@RestController
public class GameController {

    public static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private ServicePlayer serviceGame;

    @Autowired
    private GameConverter gameConverter;

    @RequestMapping(value = "/games")
    GamesDto getAllGames() {
        logger.trace("getAllGames - method entered");
        return new GamesDto(gameConverter.convertModelsToDtos(serviceGame.getAllGames()));
    }

    @RequestMapping(value = "/games/filter/{id}")
    GamesDto filterGamesByBestPlayerID(@PathVariable Long id) {
        logger.trace("filterGamesByBestPlayerID - methon entered; id={}", id);
        return new GamesDto(gameConverter.convertModelsToDtos(serviceGame.getGamesByBestPlayerID(id)));
    }

    @RequestMapping(value = "/games", method = RequestMethod.POST)
    GameDto addGame(@RequestBody GameDto gameDto) {
        logger.trace("addGame - method entered; gameDto={}", gameDto);
        Game game = gameConverter.convertDtoToModel(gameDto);
        Game result = serviceGame.addGame(game);
        GameDto resultGameDto = gameConverter.convertModelToDto(result);
        logger.trace("addGame - method finished; gameResultDto={}", resultGameDto);
        return resultGameDto;
    }

    @RequestMapping(value = "/games/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> removeGame(@PathVariable Long id) {
        logger.trace("removeGame - method entered; game={}", serviceGame.getGameById(id));
        serviceGame.removeGame(id);
        logger.trace("removeGame - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/games/{id}", method = RequestMethod.PUT)
    GameDto updateGame(@PathVariable Long id, @RequestBody GameDto gameDto) {
        logger.trace("updateGame - method entered; gameDto={}", serviceGame.getGameById(id));
        gameDto.setId(id);
        GameDto result = gameConverter.convertModelToDto(serviceGame.updateGame(gameConverter.convertDtoToModel(gameDto)));
        logger.trace("updateGame - method finished; resultGameDto={}", result);
        return result;
    }

    @RequestMapping(value = "/games/{id}")
    GameDto getGameById(@PathVariable Long id) {
        return gameConverter.convertModelToDto(serviceGame.getGameById(id));
    }
}