package com.iftm.logpool.mensages;

import com.iftm.logpool.models.dtos.LogDTO;
import com.iftm.logpool.services.LogService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqReadLog {

    @Autowired
    private LogService logService;

    @RabbitListener(queues = "${softwareHouse.rabbitmq.queue}")
    public void receiveLog(@Payload LogDTO logDTO) {
        if(logDTO != null)
            logService.save(logDTO);
    }
}
