package ro.ubb.catalog.core.model.validators;

import ro.ubb.catalog.core.exceptions.ValidatorException;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}

