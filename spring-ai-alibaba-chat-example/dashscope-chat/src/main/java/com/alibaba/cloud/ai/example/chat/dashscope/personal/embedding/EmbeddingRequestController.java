package com.alibaba.cloud.ai.example.chat.dashscope.personal.embedding;

import com.alibaba.cloud.ai.dashscope.embedding.DashScopeEmbeddingOptions;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;

import java.util.List;


public class EmbeddingRequestController {

    private static final EmbeddingModel embeddingModel = null;

    public static void main(String[] args) {
        // 使用嵌入的内容进行对话
        EmbeddingResponse embeddingResponse = embeddingModel.call(
                new EmbeddingRequest(List.of("Hello World", "World is big and salvation is near"),
                        DashScopeEmbeddingOptions.builder()
                                .withModel("Different-Embedding-Model-Deployment-Name")
                                .build()));

    }
}
