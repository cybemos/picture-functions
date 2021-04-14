package com.cybemos.client.args;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import lombok.Data;

@Parameters(commandNames="reverse")
@Data
public class ReverseArgs {

    @Parameter(names = "--source", description = "Image Source", required = true)
    private String source;

    @Parameter(names = "--dest", description = "destination file", required = true)
    private String destination;

}
