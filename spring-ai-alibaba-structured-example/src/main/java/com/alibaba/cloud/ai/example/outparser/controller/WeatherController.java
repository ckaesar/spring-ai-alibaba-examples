/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.cloud.ai.example.outparser.controller;

import com.alibaba.cloud.ai.example.outparser.entity.WeatherEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private static final Logger log = LoggerFactory.getLogger(WeatherController.class);

    private final ChatClient chatClient;
    private final ChatModel chatModel;
    private final BeanOutputConverter<List<WeatherEntity>> converter;
    private final String format;

    public WeatherController(ChatClient.Builder builder, ChatModel chatModel) {
        this.chatModel = chatModel;

        this.converter = new BeanOutputConverter<>(
                new ParameterizedTypeReference<List<WeatherEntity>>() {
                }
        );
        this.format = converter.getFormat();
        log.info("format: {}", format);
        this.chatClient = builder
                .build();
    }

    @GetMapping("/query")
    public String chatModel(@RequestParam(value = "query", defaultValue = "获取北京最近7天的天气信息") String query) {
        String template = query + "{format}";

        PromptTemplate promptTemplate = PromptTemplate.builder()
                .template(template)
                .variables(Map.of("format", format))
                .renderer(StTemplateRenderer.builder().build())
                .build();

        Prompt prompt = promptTemplate.create();

        String result = chatModel.call(prompt).getResult().getOutput().getText();
        log.info("result: {}", result);
        assert result != null;
        try {
            List<WeatherEntity> convert = converter.convert(result);
            log.info("反序列成功，convert: {}", convert);
        } catch (Exception e) {
            log.error("反序列化失败");
        }
        return result;
    }
}
