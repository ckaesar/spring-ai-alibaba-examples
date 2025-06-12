package com.alibaba.cloud.ai.toolcall.component;

import com.alibaba.cloud.ai.toolcall.service.GetRandNumberService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

public class RandNumberTools {

    private final GetRandNumberService rand;

    public RandNumberTools(GetRandNumberService randNumberService) {
        this.rand = randNumberService;
    }

    @Tool(description = "Get several random numbers in a specified range.")
    public String getRandNumber(
            @ToolParam(description = "lowerBound of the Number") Integer lowerBound,
            @ToolParam(description = "upperBound of the Number") Integer upperBound,
            @ToolParam(description = "count") Integer count) {
        return rand.apply(new GetRandNumberService.Request(lowerBound, upperBound, count)).description();
    }

}
