package com.example.microrabbit.controller;

import com.example.microrabbit.entity.RatesList;
import com.example.microrabbit.services.CentralBankReceiverService;
import com.example.microrabbit.services.RabbitMessagingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitController {

  private final RabbitMessagingService rabbitMessagingService;
  private final CentralBankReceiverService receiverService;

  @Autowired
  public RabbitController(RabbitMessagingService rabbitMessagingService, CentralBankReceiverService receiverService) {
    this.rabbitMessagingService = rabbitMessagingService;
    this.receiverService = receiverService;
  }

  @Scheduled(cron = "5 23 * * 1-5")
  @GetMapping("/send")
  public void sendMessage() throws JsonProcessingException {
    RatesList ratesToSend = receiverService.receiveData();
    rabbitMessagingService.sendMessage(ratesToSend);
    System.out.println("Sending message with key: " + ratesToSend);
  }
}
