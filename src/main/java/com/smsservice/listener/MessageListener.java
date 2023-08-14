package com.smsservice.listener;

import com.smsservice.entity.MessageEntity;
import com.smsservice.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MessageListener {

    private final WebClient.Builder webClientBuilder;

    private final SmsService smsService;
    private static final String QUEUE = "otp_code_sms_queue";
    public static final String OTP_SERVICE_URL = "http://localhost:8080/otp-service/v1/";

    @RabbitListener(queues = QUEUE)
    public void listener(MessageEntity message) {

        String code = webClientBuilder.build()
                .get()
                .uri(OTP_SERVICE_URL + message.getMessage())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        if (Objects.isNull(code))
            return;

        smsService.send(message.getTo(), code);

    }

}
