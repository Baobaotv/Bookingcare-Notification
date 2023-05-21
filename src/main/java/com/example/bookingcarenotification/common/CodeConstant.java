package com.example.bookingcarenotification.common;

public class CodeConstant {
    public static final String NOTIFICATION_TYPE_USER_BOOKING_SCHEDULE = "1";

    public static final String NOTIFICATION_TYPE_REMIND_USER_COMPLETE_PAYMENT = "4";

    public static final String NOTIFICATION_TYPE_CHANGE_SCHEDULE = "2";

    public static final String NOTIFICATION_TYPE_SEND_MEDICAL_RECORDS = "3";

    public static final String NOTIFICATION_TOPIC = "notification";

    public static final String NOTIFICATION_RESET_PASS_TOPIC = "notification_reset_pass";

    public static final String NOTIFICATION_CHANGE_TIME_TOPIC = "notification_change_time";

    public static final String NOTIFICATION_TYPE_CANCEL_MEDICAL = "notification_cancel_medical";

    public static final Integer MEDICAL_SCHEDULE_IS_NOT_COMPLETE = 1;

    public static final String MAIL_TEMPLATE_CHANGE_TIME = "mail_template_change_time.html";

    public static final String MAIL_TEMPLATE = "mail_template.html";

    public static final String MAIL_TEMPLATE_SEND_MEDICAL_RECORDS = "mail_template_send_medical_records.html";

    public static final String MAIL_TEMPLATE_RESET_PASS = "mail_template_reset_password.html";

    public static final String MAIL_TEMPLATE_USER_UPDATE_TIME = "mail_template_update_time.html";

    public static final String MAIL_TEMPLATE_USER_CANCEL_MEDICAL = "mail_template_cancel_medical.html";

    public static final String MAIL_TEMPLATE_REMIND_PAYMENT_MEDICAL = "mail_template_remind_payment_medical.html";

    public static final Integer payment_paid = 1;

    public static final Integer payment_unPaid = 0;

    public static final Integer payment_error = 2;

    public static final Integer MEDICAL_SCHEDULE_IS_COMPLETE = 2;

    public static final Integer MEDICAL_SCHEDULE_IS_CANCEL = 0;
}
