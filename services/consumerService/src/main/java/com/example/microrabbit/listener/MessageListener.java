package com.example.microrabbit.listener;

import com.example.microrabbit.entity.RatesList;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

  @RabbitListener(queues = "ConsumerServiceQueue")
  public void receiveMessage(RatesList message) {
    System.out.println("Received message from queue: " + message.toString());
  }
}
