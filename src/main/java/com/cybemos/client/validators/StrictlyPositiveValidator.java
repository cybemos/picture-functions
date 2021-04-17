package com.cybemos.client.validators;

import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.ParameterException;

public class StrictlyPositiveValidator implements IValueValidator<Integer> {
    @Override
    public void validate(String name, Integer value) throws ParameterException {
        if (value < 1) {
            throw new ParameterException("Parameter " + name + " : " + value + " should be superior to 0");
        }
    }
}
