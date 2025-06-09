package com.alibaba.cloud.ai.example.chat.dashscope.personal.embedding;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.model.Model;
import org.springframework.util.Assert;

import java.util.List;

public interface EmbeddingModel extends Model<EmbeddingRequest, EmbeddingResponse> {

    @Override
    EmbeddingResponse call(EmbeddingRequest request);

    /**
     * Embeds the given document's content into a vector.
     * @param document the document to embed.
     * @return the embedded vector.
     */
    List<Double> embed(Document document);

    /**
     * Embeds the given text into a vector.
     * @param text the text to embed.
     * @return the embedded vector.
     */
    default List<Double> embed(String text) {
        Assert.notNull(text, "Text must not be null");
        return this.embed(List.of(text)).iterator().next();
    }

    /**
     * Embeds a batch of texts into vectors.
     * @param texts list of texts to embed.
     * @return list of list of embedded vectors.
     */
    default List<List<Double>> embed(List<String> texts) {
        Assert.notNull(texts, "Texts must not be null");
        return null;
//        return this.call(new EmbeddingRequest(texts, EmbeddingOptions.EMPTY))
//                .getResults()
//                .stream()
//                .map(Embedding::getOutput)
//                .toList();
    }

    /**
     * Embeds a batch of texts into vectors and returns the {@link EmbeddingResponse}.
     * @param texts list of texts to embed.
     * @return the embedding response.
     */
    default EmbeddingResponse embedForResponse(List<String> texts) {
        Assert.notNull(texts, "Texts must not be null");
        return null;
//        return this.call(new EmbeddingRequest(texts, EmbeddingOptions.EMPTY));
    }

    /**
     * @return the number of dimensions of the embedded vectors. It is generative
     * specific.
     */
    default int dimensions() {
        return embed("Test String").size();
    }

}