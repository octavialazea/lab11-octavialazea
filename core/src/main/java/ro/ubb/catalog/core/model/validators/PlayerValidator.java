package ro.ubb.catalog.core.model.validators;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.exceptions.ValidatorException;
import ro.ubb.catalog.core.model.entities.Player;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class PlayerValidator implements Validator<Player> {

    @Override
    public void validate(Player entity) throws ValidatorException {
        if (entity.getName().isEmpty() || entity.getDateOfBirth().isEmpty() || entity.getEmail().isEmpty())
            throw new ValidatorException("Empty string");
        if (entity.getName().contains("1234567890!@#$%^&*("))
            throw new ValidatorException("That's not your real name");
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date.parse(entity.getDateOfBirth());
        } catch (ParseException e) {
            throw new ValidatorException("Not a valid date format");
        }
    }
}
