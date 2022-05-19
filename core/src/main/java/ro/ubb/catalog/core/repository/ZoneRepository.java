package ro.ubb.catalog.core.repository;

import ro.ubb.catalog.core.model.entities.Zone;

import java.util.List;

public interface ZoneRepository extends Repository<Zone, Long> {
    List<Zone> findZonesByTheme(String theme);
}
