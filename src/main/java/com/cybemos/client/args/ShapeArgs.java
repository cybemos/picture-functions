package com.cybemos.client.args;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import lombok.Data;

@Parameters
@Data
public class ShapeArgs {

    @Parameter(names = "--source", description = "Image Source", required = true)
    private String source;

    @Parameter(names = "--dest", description = "destination file", required = true)
    private String destination;

    @Parameter(names = "--unicolor", description = "if true, there will be be only one color on the image to make the shape")
    private boolean unicolor = false;

    @Parameter(
            names = "--blurlevel",
            description = "Blur level is used to detect shapes. 3 Should be a good value.",
            required = false
    )
    private Integer blurLevel = 3;

}
