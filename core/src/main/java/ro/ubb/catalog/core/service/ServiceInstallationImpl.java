package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.entities.Installed;
import ro.ubb.catalog.core.repository.ComputerRepository;
import ro.ubb.catalog.core.repository.GameRepository;
import ro.ubb.catalog.core.repository.InstalledRepository;

import java.util.List;

@Service
public class ServiceInstallationImpl implements ServiceInstallation{
    public static final Logger logger  = LoggerFactory.getLogger(ServiceInstallation.class);
    @Autowired
    private InstalledRepository installedRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ComputerRepository computerRepository;

    @Override
    public Installed addInstallation(Installed installed) {
        logger.trace("addInstallation - method entered; installed={}", installed);
        Installed addedInstalled = installedRepository.save(installed);
        logger.trace("addInstallation - method finished; installed={}", addedInstalled);
        return addedInstalled;
    }

    @Override
    public void removeInstallation(Long ID) {
        logger.trace("removeInstallation - method entered; id={}", ID);
        installedRepository.deleteById(ID);
        logger.trace("removeInstallation - method finished");
    }

    @Override
    public Installed updateInstallation(Installed installed) {
        logger.trace("updateInstallation - method entered; installed={}", installed);
        Installed update = installedRepository.findById(installed.getId()).orElseThrow();
        update.setComputerID(installed.getComputerID());
        update.setGameID(installed.getGameID());
        update.setPerformanceGrade(installed.getPerformanceGrade());
        logger.trace("updateInstallation - method finished; installed={}", update);
        return installed;
    }

    @Override
    public List<Installed> getAllInstallations() {
        logger.trace("getAllInstallations - method entered");
        return installedRepository.findAll();
    }

    @Override
    public Installed getInstalledById(Long id) {
        return installedRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Installed> getInstalledByGameIdSortedByPerformanceGradeDesc(Long gameid) {
        return installedRepository.findInstalledByGameIDOrderByPerformanceGradeDesc(gameid);
    }
}
