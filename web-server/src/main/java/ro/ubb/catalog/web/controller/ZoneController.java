package ro.ubb.catalog.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.entities.Zone;
import ro.ubb.catalog.core.service.ServiceZone;
import ro.ubb.catalog.web.converter.ZoneConverter;
import ro.ubb.catalog.web.dto.ZoneDto;
import ro.ubb.catalog.web.dto.ZonesDto;


@RestController
public class ZoneController {
    public static final Logger logger = LoggerFactory.getLogger(ZoneController.class);

    @Autowired
    private ServiceZone serviceZone;

    @Autowired
    private ZoneConverter zoneConverter;

    @RequestMapping(value = "/zones")
    ZonesDto getAllZones(){
        logger.trace("getAllZones - method entered");
        return new ZonesDto(zoneConverter.convertModelsToDtos(serviceZone.getAllZones()));
    }

    @RequestMapping(value = "/zones", method = RequestMethod.POST)
    ZoneDto addZone(@RequestBody ZoneDto zoneDto){
        logger.trace("adZone - method entered; zoneDto={}", zoneDto);
        Zone zone = zoneConverter.convertDtoToModel(zoneDto);
        Zone result = serviceZone.addZone(zone);
        ZoneDto resultZoneDto = zoneConverter.convertModelToDto(result);
        logger.trace("addZone - method finished; zoneResultDto={}", resultZoneDto);
        return resultZoneDto;
    }

    @RequestMapping(value = "/zones/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> removeZone(@PathVariable Long id){
        logger.trace("removePlayer - method entered; zone={}", serviceZone.getZoneById(id));
        serviceZone.removeZone(id);
        logger.trace("removeZone - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/zones/{id}", method = RequestMethod.PUT)
    ZoneDto updateZone(@PathVariable Long id, @RequestBody ZoneDto zoneDto){
        logger.trace("updateZone - method entered; zone={}", serviceZone.getZoneById(id));
        zoneDto.setId(id);
        ZoneDto result = zoneConverter.convertModelToDto(serviceZone.updateZone(zoneConverter.convertDtoToModel(zoneDto)));
        logger.trace("updateZone - method finished; resultPlayerDto={}", result);
        return result;
    }

    @RequestMapping(value = "/zones/{id}")
    ZoneDto getZoneById(@PathVariable Long id){
        return zoneConverter.convertModelToDto(serviceZone.getZoneById(id));
    }

    @RequestMapping(value = "/zones/filter/{theme}")
    ZonesDto filterZonesByTheme(@PathVariable String theme){
        logger.trace("filterZonesByTheme - method entered; theme={}", theme);
        return new ZonesDto(zoneConverter.convertModelsToDtos(serviceZone.getZonesByTheme(theme)));
    }
}
