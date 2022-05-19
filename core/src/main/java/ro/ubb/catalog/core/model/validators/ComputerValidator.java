package ro.ubb.catalog.core.model.validators;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.exceptions.ValidatorException;
import ro.ubb.catalog.core.model.entities.Computer;

@Component
public class ComputerValidator implements Validator<Computer> {
    @Override
    public void validate(Computer entity) throws ValidatorException {
        if (entity.getZoneID() <= 0)
            throw new ValidatorException("Zone id must be greater than 0");

        if (entity.getOperatingSystem().isEmpty())
            throw new ValidatorException("Operating system must be defined");

        if (entity.getPurchaseDate().isEmpty())
            throw new ValidatorException("Purchase date must be defined");
    }
}