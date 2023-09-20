package com.example.microrabbit.services;

import com.example.microrabbit.entity.RatesList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CentralBankReceiverService {

  @Value("${application.cbrf.url}")
  String ratesURI;

  public RatesList receiveData() throws JsonProcessingException {
    RestTemplate restTemplate = new RestTemplate();
    String response = restTemplate.getForObject(ratesURI, String.class);

    return new ObjectMapper().readValue(response, RatesList.class);
  }
}
