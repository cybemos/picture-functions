package com.cybemos.client.validators;

import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.ParameterException;

import java.io.File;
import java.util.List;

public class ExistingFilesValidator implements IValueValidator<List<File>> {

    private final ExistingFileValidator validator;

    public ExistingFilesValidator() {
        this.validator = new ExistingFileValidator();
    }

    @Override
    public void validate(String name, List<File> files) throws ParameterException {
        for (File file : files) {
            validator.validate(name, file);
        }
    }
}
