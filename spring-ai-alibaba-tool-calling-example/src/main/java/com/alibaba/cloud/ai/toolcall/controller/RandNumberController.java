package com.alibaba.cloud.ai.toolcall.controller;

import com.alibaba.cloud.ai.toolcall.component.RandNumberTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生成随机数的工具使用，从结果上不使用工具调用也可以生成，只是通过这个例子来实战一下工具调用
 */
@RestController
@RequestMapping("/rand-number")
public class RandNumberController {

    private final ChatClient dashScopeChatClient;
    private final RandNumberTools randNumberTools;

    public RandNumberController(ChatClient chatClient, RandNumberTools randNumberTools) {
        this.dashScopeChatClient = chatClient;
        this.randNumberTools = randNumberTools;
    }

    /**
     * No Tool
     */
    @GetMapping("/chat")
    public String simpleChat(@RequestParam(value = "query", defaultValue = "请提供10个随机数，范围在0到100之间") String query) {
        return dashScopeChatClient.prompt(query).call().content();
    }

    /**
     * Methods as Tools
     */
    @GetMapping("/chat-tool-method")
    public String chatWithTimeFunction(@RequestParam(value = "query", defaultValue = "请提供10个随机数，范围在0到100之间") String query) {
        return dashScopeChatClient.prompt(query).advisors(new SimpleLoggerAdvisor()).tools(randNumberTools).call().content();
    }

}
