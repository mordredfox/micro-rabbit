package com.example.microrabbit.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@ConditionalOnProperty(value = "spring.cloud.bus.enabled", havingValue = "true")
@Configuration
public class SpringRabbitConfig {

  @Value("${spring.cloud.bus.rabbitmq.host:localhost}")
  private String host;

  @Value("${spring.cloud.bus.rabbitmq.port:5672}")
  private Integer port;

  @Value("${spring.cloud.bus.rabbitmq.username:guest}")
  private String username;

  @Value("${spring.cloud.bus.rabbitmq.password:guest}")
  private String password;

  @Value("${rabbitmq.queue.name:ConsumerServiceQueue}")
  private String queue;

  @Value("${rabbitmq.exchange.name:ConsumerExchange}")
  private String exchange;

  @Bean
  @Primary
  public ConnectionFactory rabbitConnectionFactory() throws Exception {
    RabbitConnectionFactoryBean rabbitConnectionFactoryBean = new RabbitConnectionFactoryBean();
    rabbitConnectionFactoryBean.setHost(host);
    rabbitConnectionFactoryBean.setPort(port);
    rabbitConnectionFactoryBean.setUsername(username);
    rabbitConnectionFactoryBean.setPassword(password);
    rabbitConnectionFactoryBean.afterPropertiesSet();

    // noinspection ConstantConditions
    return new CachingConnectionFactory(rabbitConnectionFactoryBean.getObject());
  }

  @Bean
  public AmqpAdmin amqpAdmin() throws Exception {
    return new RabbitAdmin(rabbitConnectionFactory());
  }

  @Bean
  public RabbitTemplate rabbitTemplate() throws Exception {
    return new RabbitTemplate(rabbitConnectionFactory());
  }

  @Bean
  public Queue queue() {
    return new Queue(queue);
  }

  @Bean
  public DirectExchange exchange() {
    return new DirectExchange(exchange);
  }

  @Bean
  public Binding binding(Queue queue, DirectExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with("ConsumerServiceQueue");
  }
}