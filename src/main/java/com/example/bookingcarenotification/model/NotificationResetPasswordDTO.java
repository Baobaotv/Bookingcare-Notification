package com.example.bookingcarenotification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationResetPasswordDTO {
    private String email;

    private String url;

    private String fullName;
}
