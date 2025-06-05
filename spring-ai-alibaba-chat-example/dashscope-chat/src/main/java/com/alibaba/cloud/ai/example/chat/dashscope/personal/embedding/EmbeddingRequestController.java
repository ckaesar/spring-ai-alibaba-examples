package com.alibaba.cloud.ai.example.chat.dashscope.personal.embedding;

public class EmbeddingRequestController {

    // 使用嵌入的内容进行对话
    EmbeddingResponse embeddingResponse = embeddingModel.call(
            new EmbeddingRequest(List.of("Hello World", "World is big and salvation is near"),
                    DashScopeEmbeddingOptions.builder()
                            .withModel("Different-Embedding-Model-Deployment-Name")
                            .build()));
}
