package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.entities.Player;
import ro.ubb.catalog.core.service.ServicePlayer;
import ro.ubb.catalog.web.converter.PlayerConverter;
import ro.ubb.catalog.web.dto.PlayerDto;
import ro.ubb.catalog.web.dto.PlayersDto;

@RestController
public class PlayerController {
    public static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private ServicePlayer servicePlayer;

    @Autowired
    private PlayerConverter playerConverter;

    @RequestMapping(value = "/players")
    PlayersDto getAllPlayers() {
        logger.trace("getAllPlayers - method entered");
        return new PlayersDto(playerConverter.convertModelsToDtos(servicePlayer.getAllPlayers()));
    }

    @RequestMapping(value = "/players", method = RequestMethod.POST)
    PlayerDto addPlayer(@RequestBody PlayerDto playerDto) {
        logger.trace("addPlayer - method entered; playerDto={}", playerDto);
        Player player = playerConverter.convertDtoToModel(playerDto);
        Player result = servicePlayer.addPlayer(player);
        PlayerDto resultPlayerDto = playerConverter.convertModelToDto(result);
        logger.trace("addPlayer - method finished; playerResultDto={}", resultPlayerDto);
        return resultPlayerDto;
    }

    @RequestMapping(value = "/players/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> removePlayer(@PathVariable Long id) {
        logger.trace("removePlayer - method entered; player={}", servicePlayer.getPlayerById(id));
        servicePlayer.removePlayer(id);
        logger.trace("removePlayer - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/players/{id}", method = RequestMethod.PUT)
    PlayerDto updatePlayer(@PathVariable Long id, @RequestBody PlayerDto playerDto) {
        logger.trace("updatePlayer - method entered; playerDto={}", servicePlayer.getPlayerById(id));
        playerDto.setId(id);
        PlayerDto result = playerConverter.convertModelToDto(servicePlayer.updatePlayer(playerConverter.convertDtoToModel(playerDto)));
        logger.trace("updatePlayer - method finished; resultPlayerDto={}", result);
        return result;
    }

    @RequestMapping(value = "/players/{id}")
    PlayerDto getPlayerById(@PathVariable Long id) {
        return playerConverter.convertModelToDto(servicePlayer.getPlayerById(id));
    }

    @RequestMapping(value = "/players/filter/{name}")
    PlayersDto filterPlayersByName(@PathVariable String name) {
        logger.trace("filterPlayersByName - methon entered; name={}", name);
        return new PlayersDto(this.playerConverter.convertModelsToDtos(servicePlayer.getPlayersByName(name)));
    }
}
