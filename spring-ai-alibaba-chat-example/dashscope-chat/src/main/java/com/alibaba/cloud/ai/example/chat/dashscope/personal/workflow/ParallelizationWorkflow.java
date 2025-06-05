package com.alibaba.cloud.ai.example.chat.dashscope.personal.workflow;

import org.springframework.ai.chat.client.ChatClient;

import java.util.List;

public class ParallelizationWorkflow {
    // todo 需要初始化
    private ChatClient chatClient = null;
    private String[] systemPrompts = null;

    public ParallelizationWorkflow(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

//    List<String> parallelResponse = new ParallelizationWorkflow(chatClient)
//            .parallel(
//                    "Analyze how market changes will impact this stakeholder group.",
//                    List.of(
//                            "Customers: ...",
//                            "Employees: ...",
//                            "Investors: ...",
//                            "Suppliers: ..."
//                    ),
//                    4
//            );
}
