package ro.ubb.catalog.core.model.validators;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.exceptions.ValidatorException;
import ro.ubb.catalog.core.model.entities.Zone;

@Component
public class ZoneValidator implements Validator<Zone> {
    @Override
    public void validate(Zone entity) throws ValidatorException {
        if (entity.getName().isEmpty())
            throw new ValidatorException("Zone name must be defined");

        if (entity.getTheme().isEmpty())
            throw new ValidatorException("Zone theme must be defined");

        if (entity.getCapacity() <= 0)
            throw new ValidatorException("Capacity of the zone must be greater than 0");
    }
}