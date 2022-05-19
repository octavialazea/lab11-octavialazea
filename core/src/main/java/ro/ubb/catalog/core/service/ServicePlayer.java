package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.entities.Game;
import ro.ubb.catalog.core.model.entities.Player;

import java.util.List;

public interface ServicePlayer {

    Player addPlayer(Player player);
    void removePlayer(Long ID);
    Player updatePlayer(Player player);
    List<Player> getAllPlayers();
    Game addGame(Game game);
    void removeGame(Long ID);
    Game updateGame(Game game);
    List<Game> getAllGames();
    Player getPlayerById(Long ID);
    Game getGameById(Long ID);
    List<Player> getPlayersByName(String name);
    List<Game> getGamesByBestPlayerID(Long ID);
}
