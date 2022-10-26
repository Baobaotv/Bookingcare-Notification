package com.example.bookingcarenotification.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailTemplateDTO {

    private String mailTo;

    private String body;

    private String subject;
}
