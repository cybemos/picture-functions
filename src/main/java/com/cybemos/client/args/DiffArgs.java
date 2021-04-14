package com.cybemos.client.args;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Parameters(commandNames="diff")
@Data
public class DiffArgs {

    @Parameter(names = "--source", description = "Image Source", required = true)
    private List<String> sources = new ArrayList<>();;

    @Parameter(names = "--dest", description = "destination file", required = true)
    private String destination;

}
