package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.entities.Installed;
import ro.ubb.catalog.core.model.entities.Player;
import ro.ubb.catalog.core.service.ServiceInstallation;
import ro.ubb.catalog.web.converter.InstalledConverter;
import ro.ubb.catalog.web.dto.InstallationsDto;
import ro.ubb.catalog.web.dto.InstalledDto;
import ro.ubb.catalog.web.dto.PlayerDto;
import ro.ubb.catalog.web.dto.PlayersDto;

@RestController
public class InstallationController {
    public static final Logger logger = LoggerFactory.getLogger(InstallationController.class);

    @Autowired
    private ServiceInstallation serviceInstallation;
    @Autowired
    private InstalledConverter installedConverter;

    @RequestMapping(value = "/installations")
    InstallationsDto getAllInstalled() {
        logger.trace("getAllInstalled - method entered");
        return new InstallationsDto(installedConverter.convertModelsToDtos(serviceInstallation.getAllInstallations()));
    }

    @RequestMapping(value = "/installations", method = RequestMethod.POST)
    InstalledDto addInstalled(@RequestBody InstalledDto installedDto) {
        logger.trace("addInstalled - method entered; installedDto={}", installedDto);
        Installed installed = installedConverter.convertDtoToModel(installedDto);
        Installed result = serviceInstallation.addInstallation(installed);
        InstalledDto resultInstalledDto = installedConverter.convertModelToDto(result);
        logger.trace("addInstalled - method finished; playerInstalledDto={}", resultInstalledDto);
        return resultInstalledDto;
    }

    @RequestMapping(value = "/installations/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> removeInstalled(@PathVariable Long id) {
        logger.trace("removeInstalled - method entered; player={}", serviceInstallation.getInstalledById(id));
        serviceInstallation.removeInstallation(id);
        logger.trace("removeInstalled - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/installations/{id}", method = RequestMethod.PUT)
    InstalledDto updateInstalled(@PathVariable Long id, @RequestBody InstalledDto installedDto) {
        logger.trace("updateInstalled - method entered; installedDto={}", serviceInstallation.getInstalledById(id));
        installedDto.setId(id);
        InstalledDto result = installedConverter.convertModelToDto(serviceInstallation.updateInstallation(installedConverter.convertDtoToModel(installedDto)));
        logger.trace("updateInstalled - method finished; resultInstalledDto={}", result);
        return result;
    }

    @RequestMapping(value = "/installations/{id}")
    InstalledDto getInstalledById(@PathVariable Long id) {
        return installedConverter.convertModelToDto(serviceInstallation.getInstalledById(id));
    }

    @RequestMapping(value = "/installations/{gameId}")
    InstallationsDto filterByGameIdOrderedByPerformanceGradeDesc(@PathVariable Long gameId){
        logger.trace("InstallationsDto filterByGameIdOrderedByPerformanceGradeDesc - method entered; gameid={}", gameId);
        return new InstallationsDto(installedConverter.convertModelsToDtos(serviceInstallation.getInstalledByGameIdSortedByPerformanceGradeDesc(gameId)));
    }


}
