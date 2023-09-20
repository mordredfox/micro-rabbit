package com.example.microrabbit.entity;

import java.io.Serializable;

public class RateMessage implements Serializable {

  String id;
  Double value;
  String time;
  Double changeValue;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public Double getChangeValue() {
    return changeValue;
  }

  public void setChangeValue(Double changeValue) {
    this.changeValue = changeValue;
  }

  @Override
  public String toString() {
    return "RateMessage{" +
            "id='" + id + '\'' +
            ", value=" + value +
            ", time=" + time +
            ", changeValue=" + changeValue +
            '}';
  }
}
