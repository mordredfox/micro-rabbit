package com.example.microrabbit.services;

import com.example.microrabbit.entity.RatesList;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMessagingService {

  private final RabbitTemplate rabbitTemplate;

  @Autowired
  public RabbitMessagingService(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendMessage(RatesList currentMessage) {
    rabbitTemplate.convertAndSend("ConsumerServiceQueue", currentMessage);
  }
}
