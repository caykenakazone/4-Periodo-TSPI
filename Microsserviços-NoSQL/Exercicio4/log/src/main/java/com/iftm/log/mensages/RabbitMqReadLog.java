package com.iftm.log.mensages;

import com.iftm.log.models.dtos.LogDTO;
import com.iftm.log.services.LogService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqReadLog {

    @Autowired
    private LogService logService;

    @RabbitListener(queues = "${newsletter.rabbitmq.queue}")
    public void receiveLog(@Payload LogDTO logDTO) {
        if(logDTO != null)
            logService.save(logDTO);
    }
}
