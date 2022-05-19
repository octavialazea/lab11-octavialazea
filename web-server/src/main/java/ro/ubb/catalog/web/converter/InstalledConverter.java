package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.entities.Installed;
import ro.ubb.catalog.web.dto.InstalledDto;

@Component
public class InstalledConverter extends BaseConverter<Installed, InstalledDto> {
    @Override
    public Installed convertDtoToModel(InstalledDto dto) {
        var model = new Installed();
        model.setId(dto.getId());
        model.setGameID(dto.getGameID());
        model.setComputerID(dto.getComputerID());
        model.setPerformanceGrade(dto.getPerformanceGrade());
        return model;
    }

    @Override
    public InstalledDto convertModelToDto(Installed installed) {
        InstalledDto dto = new InstalledDto(installed.getGameID(), installed.getComputerID(), installed.getPerformanceGrade());
        dto.setId(installed.getId());
        return dto;
    }
}
