package com.example.electrician_service.config;

import com.example.electrician_service.model.ElectricianJob;
import com.example.electrician_service.service.ElectricianService;
import com.example.electrician_service.config.RabbitConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ElectricianBookingListener {

    private final ElectricianService electricianService;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void handleBooking(BookingEvent event) {

        log.info("Received booking event: {}", event);

        ElectricianJob job =
                electricianService.assignElectrician(event.getBookingId());

        if (job != null) {

            log.info("Electrician assigned successfully. Publishing event...");

            rabbitTemplate.convertAndSend(
                    RabbitConfig.EXCHANGE,
                    RabbitConfig.ASSIGNED_KEY,
                    job
            );

        } else {
            log.warn("No available electrician found for booking: {}",
                    event.getBookingId());
        }
    }
}