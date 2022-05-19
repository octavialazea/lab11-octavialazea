package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.entities.Game;
import ro.ubb.catalog.core.model.entities.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.validators.GameValidator;
import ro.ubb.catalog.core.model.validators.PlayerValidator;
import ro.ubb.catalog.core.repository.GameRepository;
import ro.ubb.catalog.core.repository.PlayerRepository;

import java.util.List;

@Service
public class ServicePlayerImpl implements ServicePlayer {
    public static final Logger logger  = LoggerFactory.getLogger(ServicePlayer.class);
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerValidator playerValidator;
    @Autowired
    private GameValidator gameValidator;


    @Override
    public Player addPlayer(Player player) {
        logger.trace("addPlayer - method entered; player={}", player);
        playerValidator.validate(player);
        Player addedPlayer =  playerRepository.save(player);
        logger.trace("addPlayer - method finished; player={}", addedPlayer);
        return addedPlayer;
    }

    @Override
    public void removePlayer(Long ID) {
        logger.trace("removePlayer - method entered; player={}", playerRepository.findById(ID));
        playerRepository.deleteById(ID);
        logger.trace("removePlayer - method finished");
    }

    @Override
    @Transactional
    public Player updatePlayer(Player player) {
       logger.trace("updatePlayer - method entered; player={}", player);
       playerValidator.validate(player);
       Player playerUpdate = playerRepository.findById(player.getId()).orElseThrow();
       playerUpdate.setName(player.getName());
       playerUpdate.setDateOfBirth(player.getDateOfBirth());
       playerUpdate.setEmail(player.getEmail());
       logger.trace("updatePlayer - method finished; player={}", playerUpdate);
       return player;
    }

    @Override
    public List<Player> getAllPlayers() {
        logger.trace("getAllPlayers - method entered");
        return playerRepository.findAll();
    }

    @Override
    public Game addGame(Game game) {
        logger.trace("addGame - method entered; game={}", game);
        gameValidator.validate(game);
        Game addedGame = gameRepository.save(game);
        logger.trace("addGame - method finished; game={}", addedGame);
        return addedGame;
    }

    @Override
    public void removeGame(Long ID) {
        logger.trace("removeGame - method entered; game={}", gameRepository.findById(ID));
        gameRepository.deleteById(ID);
        logger.trace("removeGame - method finished");
    }

    @Override
    public Game updateGame(Game game) {
        logger.trace("updateGame - method entered; game={}", game);
        gameValidator.validate(game);
        Game gameUpdate = gameRepository.findById(game.getId()).orElseThrow();
        gameUpdate.setName(game.getName());
        gameUpdate.setCompany(game.getCompany());
        gameUpdate.setPrice(game.getPrice());
        gameUpdate.setRating(game.getRating());
        gameUpdate.setBestPlayerID(game.getBestPlayerID());
        logger.trace("updateGame - method finished; game={}", gameUpdate);
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        logger.trace("getAllGames - method entered");
        return gameRepository.findAll();
    }

    @Override
    public Player getPlayerById(Long ID) {
        logger.trace("getPlayerById - method entered; id={}", ID);
        Player player =  playerRepository.findById(ID).orElseThrow();
        logger.trace("getPlayerById - method finished; player={}", player);
        return player;
    }

    @Override
    public Game getGameById(Long ID) {
        logger.trace("getGameById - method entered; id={}", ID);
        Game game =  gameRepository.findById(ID).orElseThrow();
        logger.trace("getGameById - method finished; player={}", game);
        return game;
    }

    @Override
    public List<Player> getPlayersByName(String name) {
        logger.trace("filterPlayerByName - method entered; name={}", name);
        List<Player> players = playerRepository.findPlayersByName(name);
        logger.trace("filterPlayersByName - method finished");
        return players;
    }

    @Override
    public List<Game> getGamesByBestPlayerID(Long ID) {
        return gameRepository.findGamesByBestPlayerID(ID);
    }
}
