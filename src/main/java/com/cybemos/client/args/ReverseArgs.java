package com.cybemos.client.args;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.converters.FileConverter;
import com.cybemos.client.validators.ExistingFileValidator;
import lombok.Data;

import java.io.File;

@Parameters
@Data
public class ReverseArgs {

    @Parameter(
            names = "--source",
            description = "Image Source",
            required = true,
            converter = FileConverter.class,
            validateValueWith = ExistingFileValidator.class
    )
    private File source;

    @Parameter(names = "--dest", description = "Destination file", required = true, converter = FileConverter.class)
    private File destination;

}
