package com.example.microrabbit.entity;

import java.io.Serializable;
import java.util.List;

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
