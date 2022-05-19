package ro.ubb.catalog.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.entities.Computer;
import ro.ubb.catalog.core.service.ServiceZone;
import ro.ubb.catalog.web.converter.ComputerConverter;
import ro.ubb.catalog.web.dto.ComputerDto;
import ro.ubb.catalog.web.dto.ComputersDto;
import ro.ubb.catalog.web.dto.ZonesDto;

@RestController
public class ComputerController {

    public static final Logger logger = LoggerFactory.getLogger(ComputerController.class);

    @Autowired
    private ServiceZone serviceComputer;

    @Autowired
    private ComputerConverter computerConverter;

    @RequestMapping(value = "/computers")
    ComputersDto getAllComputers() {
        logger.trace("getAllComputers - method entered");
        return new ComputersDto(computerConverter.convertModelsToDtos(serviceComputer.getAllComputers()));
    }

    @RequestMapping(value = "/computers", method = RequestMethod.POST)
    ComputerDto addComputer(@RequestBody ComputerDto computerDto) {
        logger.trace("addComputer - method entered; computerDto={}", computerDto);
        Computer computer = computerConverter.convertDtoToModel(computerDto);
        Computer result = serviceComputer.addComputer(computer);
        ComputerDto resultComputerDto = computerConverter.convertModelToDto(result);
        logger.trace("addComputer - method finished; computerResultDto={}", resultComputerDto);
        return resultComputerDto;
    }

    @RequestMapping(value = "/computers/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> removeComputer(@PathVariable Long id) {
        logger.trace("removeComputer - method entered; computer={}", serviceComputer.getComputerById(id));
        serviceComputer.removeComputer(id);
        logger.trace("removeComputer - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/computers/{id}", method = RequestMethod.PUT)
    ComputerDto updateComputer(@PathVariable Long id, @RequestBody ComputerDto computerDto) {
        logger.trace("updateComputer - method entered; computerDto={}", serviceComputer.getComputerById(id));
        computerDto.setId(id);
        ComputerDto result = computerConverter.convertModelToDto(serviceComputer.updateComputer(computerConverter.convertDtoToModel(computerDto)));
        logger.trace("updateComputer - method finished; resultComputerDto={}", result);
        return result;
    }

    @RequestMapping(value = "/computers/{id}")
    ComputerDto getComputerById(@PathVariable Long id) {
        return computerConverter.convertModelToDto(serviceComputer.getComputerById(id));
    }

    @RequestMapping(value = "/computers/filter/{id}")
    ComputersDto filterComputersByZoneID(@PathVariable Long id){
        logger.trace("filterComputersByZoneID - method entered; id={}", id);
        return new ComputersDto(computerConverter.convertModelsToDtos(serviceComputer.getComputersByZoneID(id)));
    }

}