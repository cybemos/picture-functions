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
public class BlurArgs {

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

    @Parameter(
            names = "--blurlevel",
            description = "Level of blur. The more the value is high, the more the image is blurred",
            required = false,
            validateValueWith = StrictlyPositiveValidator.class
    )
    private Integer blurLevel = 3;

}
