package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.entities.Installed;

import java.util.List;

public interface ServiceInstallation {

    Installed addInstallation(Installed installed);
    void removeInstallation(Long ID);
    Installed updateInstallation(Installed installed);
    List<Installed> getAllInstallations();

    Installed getInstalledById(Long id);
    List<Installed> getInstalledByGameIdSortedByPerformanceGradeDesc(Long gameid);
}
