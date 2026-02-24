package com.example.Booking_Service.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "urban.exchange";

    @Bean
    public TopicExchange urbanExchange() {
        return new TopicExchange(EXCHANGE, true, false);
    }
}