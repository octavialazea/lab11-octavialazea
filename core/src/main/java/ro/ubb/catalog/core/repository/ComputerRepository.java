package ro.ubb.catalog.core.repository;

import ro.ubb.catalog.core.model.entities.Computer;

import java.util.List;

public interface ComputerRepository extends Repository<Computer, Long>{
    List<Computer> findComputersByZoneID(Long id);
}
