package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.entities.Zone;
import ro.ubb.catalog.web.dto.ZoneDto;

@Component
public class ZoneConverter extends BaseConverter<Zone, ZoneDto>{
    @Override
    public Zone convertDtoToModel(ZoneDto dto) {
        var model = new Zone();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setTheme(dto.getTheme());
        model.setCapacity(dto.getCapacity());
        return model;
    }

    @Override
    public ZoneDto convertModelToDto(Zone zone) {
        ZoneDto dto = new ZoneDto(zone.getName(), zone.getTheme(), zone.getCapacity());
        dto.setId(zone.getId());
        return dto;
    }
}
