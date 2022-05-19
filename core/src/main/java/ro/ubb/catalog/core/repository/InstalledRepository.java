package ro.ubb.catalog.core.repository;

import ro.ubb.catalog.core.model.entities.Installed;

import java.util.List;

public interface InstalledRepository extends Repository<Installed, Long> {
    List<Installed> findInstalledByGameIDOrderByPerformanceGradeDesc(Long gameId);
}
