package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.entities.Computer;
import ro.ubb.catalog.core.model.entities.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.validators.ComputerValidator;
import ro.ubb.catalog.core.model.validators.ZoneValidator;
import ro.ubb.catalog.core.repository.ComputerRepository;
import ro.ubb.catalog.core.repository.ZoneRepository;

import java.util.List;

@Service
public class ServiceZoneImpl implements ServiceZone {

    public static final Logger logger  = LoggerFactory.getLogger(ServiceZone.class);
    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private ComputerRepository computerRepository;
    @Autowired
    private ZoneValidator zoneValidator;
    @Autowired
    private ComputerValidator computerValidator;



    @Override
    public Zone addZone(Zone zone) {
        logger.trace("addZone - method entered; zone={}", zone);
        zoneValidator.validate(zone);
        Zone addedZone = zoneRepository.save(zone);
        logger.trace("addZone - method finished; zone={}", addedZone);
        return addedZone;
    }

    @Override
    public void removeZone(Long ID) {
        logger.trace("removeZone - method entered; zone={}", zoneRepository.findById(ID));
        zoneRepository.deleteById(ID);
        logger.trace("removeZone - method finished");
    }

    @Override
    @Transactional
    public Zone updateZone(Zone zone) {
        logger.trace("updateZone - method entered; zone={}", zone);
        zoneValidator.validate(zone);
        Zone zoneUpdate = zoneRepository.findById(zone.getId()).orElseThrow();
        zoneUpdate.setName(zone.getName());
        zoneUpdate.setTheme(zone.getTheme());
        zoneUpdate.setCapacity(zone.getCapacity());
        logger.trace("updateZone - method finished; zone={}", zoneUpdate);
        return zone;
    }

    @Override
    public List<Zone> getAllZones() {
        logger.trace("getAllZones - method entered");
        return zoneRepository.findAll();
    }

    @Override
    public Computer addComputer(Computer computer) {
        logger.trace("addComputer - method entered; computer={}", computer);
        computerValidator.validate(computer);
        Computer addedComputer = computerRepository.save(computer);
        logger.trace("addComputer - method finished; computer={}", addedComputer);
        return addedComputer;
    }

    @Override
    public void removeComputer(Long ID) {
        logger.trace("removeComputer - method entered; computer={}", computerRepository.findById(ID));
        computerRepository.deleteById(ID);
        logger.trace("removeComputer - method finished");
    }

    @Override
    public Computer updateComputer(Computer computer) {
        logger.trace("updateComputer - method entered; computer={}", computer);
        computerValidator.validate(computer);
        Computer computerUpdate = computerRepository.findById(computer.getId()).orElseThrow();
        computerUpdate.setZoneID(computer.getZoneID());
        computerUpdate.setOperatingSystem(computer.getOperatingSystem());
        computerUpdate.setPurchaseDate(computer.getPurchaseDate());
        logger.trace("updateComputer - method finished; computer={}", computerUpdate);
        return computer;

    }

    @Override
    public List<Computer> getAllComputers() {
        logger.trace("getAllComputers- method entered");
        return computerRepository.findAll();
    }

    @Override
    public Zone getZoneById(Long ID) {
        logger.trace("getZoneById - method entered; id={}", ID);
        Zone zone =  zoneRepository.findById(ID).orElseThrow();
        logger.trace("getZoneById - method finished; zone={}", zone);
        return zone;
    }

    @Override
    public Computer getComputerById(Long ID) {
        logger.trace("getComputerById - method entered; id={}", ID);
        Computer computer =  computerRepository.findById(ID).orElseThrow();
        logger.trace("getComputerById - method finished; computer={}", computer);
        return computer;
    }

    @Override
    public List<Computer> getComputersByZoneID(Long ID) {
        return computerRepository.findComputersByZoneID(ID);
    }

    @Override
    public List<Zone> getZonesByTheme(String theme) {
        return zoneRepository.findZonesByTheme(theme);
    }
}
