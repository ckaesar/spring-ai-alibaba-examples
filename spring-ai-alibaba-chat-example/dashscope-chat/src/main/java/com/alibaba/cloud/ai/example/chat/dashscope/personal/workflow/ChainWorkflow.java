package com.alibaba.cloud.ai.example.chat.dashscope.personal.workflow;

import org.springframework.ai.chat.client.ChatClient;

public class ChainWorkflow {
    // todo 需要初始化
    private final ChatClient chatClient = null;
    private final String[] systemPrompts = null;

    // 通过一系列提示处理输入，其中每一步的输出成为链中下一个步骤的输入。
    public String chain(String userInput) {
        String response = userInput;
        for (String prompt : systemPrompts) {
            // 将系统提示与上一个响应结合
            String input = String.format("{%s}\n {%s}", prompt, response);
            // 通过大语言模型处理并捕获输出
            response = chatClient.prompt(input).call().content();
        }
        return response;
    }
}