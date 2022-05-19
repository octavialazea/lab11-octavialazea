package ro.ubb.catalog.core.repository;

import ro.ubb.catalog.core.model.entities.Player;

import java.util.List;

public interface PlayerRepository extends Repository<Player, Long> {
    List<Player> findPlayersByName(String name);
}
