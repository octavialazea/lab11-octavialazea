package ro.ubb.catalog.web.converter;


import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.entities.Computer;
import ro.ubb.catalog.web.dto.ComputerDto;

@Component
public class ComputerConverter extends BaseConverter<Computer, ComputerDto> {
    @Override
    public Computer convertDtoToModel(ComputerDto dto) {
        var model = new Computer();
        model.setId(dto.getId());
        model.setZoneID(dto.getZoneID());
        model.setOperatingSystem(dto.getOperatingSystem());
        model.setPurchaseDate(dto.getPurchaseDate());
        return model;
    }

    @Override
    public ComputerDto convertModelToDto(Computer computer) {
        ComputerDto dto = new ComputerDto(computer.getZoneID(), computer.getOperatingSystem(), computer.getPurchaseDate());
        dto.setId(computer.getId());
        return dto;
    }
}
