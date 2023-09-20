package com.example.microrabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;

@SpringBootApplication(exclude = RabbitAutoConfiguration.class)
public class MicroRabbitApplication {

  public static void main(String[] args) {
    SpringApplication.run(MicroRabbitApplication.class, args);
  }

}
