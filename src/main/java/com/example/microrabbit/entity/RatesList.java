package com.example.microrabbit.entity;

import com.example.microrabbit.serializer.RateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

@JsonDeserialize(using = RateDeserializer.class)
public class RatesList implements Serializable {
  List<RateMessage> ratesList;

  @Override
  public String toString() {
    return "RatesList{" +
            "ratesList=" + ratesList +
            '}';
  }

  public List<RateMessage> getRatesList() {
    return ratesList;
  }

  public void setRatesList(List<RateMessage> ratesList) {
    this.ratesList = ratesList;
  }
}
