package ro.ubb.catalog.core.service;


import ro.ubb.catalog.core.model.entities.Computer;
import ro.ubb.catalog.core.model.entities.Zone;

import java.util.List;

public interface ServiceZone {

    Zone addZone(Zone zone);
    void removeZone(Long ID);
    Zone updateZone(Zone zone);
    List<Zone> getAllZones();
    Computer addComputer(Computer computer);
    void removeComputer(Long ID);
    Computer updateComputer(Computer computer);
    List<Computer> getAllComputers();
    Zone getZoneById(Long ID);
    Computer getComputerById(Long ID);
    List<Computer> getComputersByZoneID(Long ID);
    List<Zone> getZonesByTheme(String theme);
}
