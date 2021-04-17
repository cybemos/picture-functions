package com.cybemos.client.args;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.converters.FileConverter;
import com.cybemos.client.validators.ExistingFilesValidator;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Parameters
@Data
public class DiffArgs {

    @Parameter(
            names = "--source",
            description = "Image Source",
            required = true,
            converter = FileConverter.class,
            validateValueWith = ExistingFilesValidator.class
    )
    private List<File> sources = new ArrayList<>();

    @Parameter(names = "--dest", description = "Destination file", required = true, converter = FileConverter.class)
    private File destination;

}
