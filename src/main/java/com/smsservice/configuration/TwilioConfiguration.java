package com.smsservice.configuration;

import com.twilio.Twilio;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Setter
@Configuration
@ConfigurationProperties(prefix = "twilio")
public class TwilioConfiguration {

    private String accountSid;

    private String authToken;

    @Bean
    public void twilio() {
        Twilio.init(accountSid, authToken);
    }
}
