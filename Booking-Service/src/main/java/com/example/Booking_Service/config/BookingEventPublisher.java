package com.example.Booking_Service.config;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishBookingCreated(BookingEvent event) {

        String routingKey = switch (event.getServiceType().toUpperCase()) {
            case "ELECTRICIAN" -> "service.electrician.requested";
            case "PLUMBER" -> "service.plumber.requested";
            case "CLEANING" -> "service.cleaning.requested";
            default -> throw new RuntimeException("Invalid Service Type");
        };

        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                routingKey,
                event
        );
    }
}