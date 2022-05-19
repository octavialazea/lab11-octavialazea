package ro.ubb.catalog.core.model.validators;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.exceptions.ValidatorException;
import ro.ubb.catalog.core.model.entities.Game;

@Component
public class GameValidator implements Validator<Game> {
    @Override
    public void validate(Game entity) throws ValidatorException {
        if (entity.getName().isEmpty())
            throw new ValidatorException("Game name must be defined");

        if (entity.getCompany().isEmpty())
            throw new ValidatorException("Game company must be defined");

        if (entity.getPrice() < 0)
            throw new ValidatorException("Price of the game must be greater than 0");

        if (entity.getRating() < 0 || entity.getRating() > 5)
            throw new ValidatorException("Rating must be between 0 and 5");
    }
}
