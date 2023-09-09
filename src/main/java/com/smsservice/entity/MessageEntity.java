package com.smsservice.entity;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {

    private String id;

    private String message;

    private String to;

    private Date date;

    public MessageEntity(String message, String to) {
        this.id = UUID.randomUUID().toString();
        this.message = message;
        this.to = to;
        this.date = new Date();

    }
}

