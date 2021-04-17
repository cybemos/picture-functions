package com.cybemos.client.validators;

import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.ParameterException;

import java.io.File;

public class ExistingFileValidator implements IValueValidator<File> {
    @Override
    public void validate(String name, File value) throws ParameterException {
        if (!value.exists()) {
            throw new ParameterException("Parameter " + name + " : " + value.getPath() + " doesn't exist");
        }
    }
}
