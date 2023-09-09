package com.smsservice.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {

    @Value("${twilio.phone}")
    private String from;

    private static final String PHONE_PREFIX_TR = "+90";

    private static final String OTP_CODE_MESSAGE = "Your confirmation code is: ";


    @Override
    public void send(String to, String code) {
        to = PHONE_PREFIX_TR.concat(to);
        sendMessage(to, code);
    }

    private void sendMessage(String to, String message) {
        Message.creator(phoneNumber(to),
                        phoneNumber(from),
                        OTP_CODE_MESSAGE.concat(message))
                .create();
    }

    private PhoneNumber phoneNumber(String phone) {
        return new PhoneNumber(phone);
    }

}