package ro.ubb.catalog.core.repository;

import ro.ubb.catalog.core.model.entities.Game;
import ro.ubb.catalog.core.model.entities.Player;

import java.util.List;

public interface GameRepository extends Repository<Game, Long> {
    List<Game> findGamesByBestPlayerID(Long id);
}
