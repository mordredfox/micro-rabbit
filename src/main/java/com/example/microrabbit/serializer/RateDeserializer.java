package com.example.microrabbit.serializer;

import com.example.microrabbit.entity.RateMessage;
import com.example.microrabbit.entity.RatesList;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RateDeserializer extends StdDeserializer<RatesList> {

  public RateDeserializer() {
    this(null);
  }

  public RateDeserializer(Class<?> t) {
    super(t);
  }

  @Override
  public RatesList deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    List<RateMessage> rates = new ArrayList<>();
    RatesList ratesList = new RatesList();

    JsonNode node = p.getCodec().readTree(p);
    JsonNode ratesNode = node.get("wap_rates").get("data");

    for (int i = 0; i < ratesNode.size(); i++) {
      RateMessage rate = getRate(ratesNode.get(i));
      rates.add(rate);
    }

    ratesList.setRatesList(rates);
    return ratesList;
  }

  private RateMessage getRate(JsonNode node) {
    if(checkIsNull(node))
      return null;

    RateMessage rate = new RateMessage();
    rate.setId(node.get(3).asText());
    rate.setTime(node.get(0).asText());
    rate.setValue(node.get(4).asDouble());
    rate.setChangeValue(node.get(5).asDouble());

    return rate;
  }

  private boolean checkIsNull(JsonNode node) {
    return node == null || node.isNull();
  }
}
