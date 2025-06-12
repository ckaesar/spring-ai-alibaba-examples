package com.alibaba.cloud.ai.toolcall.service;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.Random;
import java.util.function.Function;
import java.util.logging.Logger;

public class GetRandNumberService
        implements Function<GetRandNumberService.Request, GetRandNumberService.Response> {

    private static final Logger logger = Logger.getLogger(GetRandNumberService.class.getName());

    @Override
    public GetRandNumberService.Response apply(GetRandNumberService.Request request) {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < request.count; i++) {
            int randomNumber = random.nextInt(request.upperBound - request.lowerBound + 1) + request.lowerBound;
            sb.append(randomNumber).append(" ");
        }
        logger.info(String.format("Generated %d random numbers in the range [%d, %d]: %s",
                request.count, request.lowerBound, request.upperBound, sb.toString().trim()));

        return new Response(String.format("Generated %d random numbers in the range [%d, %d]: %s",
                request.count, request.lowerBound, request.upperBound, sb.toString().trim()));
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonClassDescription("Get several random numbers in a specified range.")
    public record Request(
            @JsonProperty(required = true, value = "lowerBound") @JsonPropertyDescription("lowerBound of the Number") Integer lowerBound,
            @JsonProperty(required = true, value = "upperBound") @JsonPropertyDescription("upperBound of the Number") Integer upperBound,
            @JsonProperty(required = true, value = "count") @JsonPropertyDescription("Number of "
                    + "random numbers to generate") Integer count) {
    }

    public record Response(String description) {
    }

}
