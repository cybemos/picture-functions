package com.cybemos.client;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import lombok.Data;

@Parameters(commandNames="blur")
@Data
public class BlurArgs {

    @Parameter(names = "--source", description = "Image Source", required = true)
    private String source;

    @Parameter(names = "--dest", description = "destination file", required = true)
    private String destination;

}
