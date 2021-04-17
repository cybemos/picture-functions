package com.cybemos.client.args;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.converters.FileConverter;
import com.cybemos.client.validators.ExistingFileValidator;
import com.cybemos.client.validators.StrictlyPositiveValidator;
import lombok.Data;

import java.io.File;

@Parameters
@Data
public class ShapeArgs {

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

    @Parameter(names = "--unicolor", description = "If true, there will be be only one color on the image to make the shape")
    private boolean unicolor = false;

    @Parameter(
            names = "--blurlevel",
            description = "Blur level is used to detect shapes. 3 Should be a good value.",
            required = false,
            validateValueWith = StrictlyPositiveValidator.class
    )
    private Integer blurLevel = 3;

}
