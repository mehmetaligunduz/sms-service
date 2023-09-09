package com.smsservice.listener;

import com.smsservice.entity.MessageEntity;
import com.smsservice.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageListener {

    private final SmsService smsService;

    private static final String QUEUE = "otp_code_sms_queue";

    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @RabbitListener(queues = QUEUE)
    public void listener(MessageEntity message) {

        smsService.send(message.getTo(), message.getMessage());
        logger.info("SMS successfully sent - id: {}", message.getId());
    }

}
