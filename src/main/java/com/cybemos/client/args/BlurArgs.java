package com.cybemos.client.args;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import lombok.Data;

@Parameters
@Data
public class BlurArgs {

    @Parameter(names = "--source", description = "Image Source", required = true)
    private String source;

    @Parameter(names = "--dest", description = "destination file", required = true)
    private String destination;

    @Parameter(
            names = "--blurlevel",
            description = "Level of blur. The more the value is high, the more the image is blurred",
            required = false
    )
    private Integer blurLevel = 3;

}
