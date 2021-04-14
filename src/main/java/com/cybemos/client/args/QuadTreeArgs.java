package com.cybemos.client.args;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import lombok.Data;

@Parameters(commandNames="quadtree")
@Data
public class QuadTreeArgs {

    @Parameter(names = "--source", description = "Image Source", required = true)
    private String source;

    @Parameter(names = "--dest", description = "destination file", required = true)
    private String destination;

    @Parameter(
            names = "--deepness",
            description = "Max deepness of quadtree. The more the value is high, the more the quality is high",
            required = true
    )
    private Integer deepness;

}
