package com.geektrust.backend.Repository;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.geektrust.backend.Entities.Fund;

public class FundDeserializer extends JsonDeserializer<Fund> {
    @Override
    public Fund deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        ObjectCodec codec = jp.getCodec();
        JsonNode node = codec.readTree(jp);

        String name = node.get("name").asText();

        Set<String> stocks = new HashSet<>();
        JsonNode stocksNode = node.get("stocks");
        if (stocksNode != null && stocksNode.isArray()) {
            for (JsonNode stockNode : stocksNode) {
                stocks.add(stockNode.asText());
            }
        }

        return new Fund.Builder()
        .withName(name)
        .withStocks(stocks)
        .build();

    }

}
