package com.example.bookingcarenotification.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.ResourceUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class MailUtil {

    public static void sendMail(List<String> emailList, String body, JavaMailSender emailSender, Boolean isHtml) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false);
        String[] arrayEmail =   emailList.toArray(new String[emailList.size()]);
        helper.setTo(arrayEmail);
        helper.setSubject("Hệ thống chăm sóc sức khoẻ Booking Care");
        helper.setText(body, isHtml);
        emailSender.send(message);
    }

    public static void sendMail(String email, String body, JavaMailSender emailSender, Boolean isHtml) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
//        helper.setTo("baodev1810@gmail.com");
        helper.setTo(email);
        helper.setSubject("Hệ thống chăm sóc sức khoẻ Booking Care");
        helper.setText(body, isHtml);
        emailSender.send(message);
    }

    public static String readBodyFromMailTemplate(String typeNotification) throws IOException {
        String fileName = "";

        switch (typeNotification){
            case CodeConstant.NOTIFICATION_TYPE_USER_BOOKING_SCHEDULE: {
                fileName = CodeConstant.MAIL_TEMPLATE;
                break;
            }
            case CodeConstant.NOTIFICATION_TYPE_CHANGE_SCHEDULE: {
                fileName = CodeConstant.MAIL_TEMPLATE_CHANGE_TIME;
                break;
            }
            case CodeConstant.NOTIFICATION_TYPE_SEND_MEDICAL_RECORDS: {
                fileName = CodeConstant.MAIL_TEMPLATE_SEND_MEDICAL_RECORDS;
                break;
            }
            case CodeConstant.NOTIFICATION_RESET_PASS_TOPIC: {
                fileName = CodeConstant.MAIL_TEMPLATE_RESET_PASS;
                break;
            }
            case CodeConstant.NOTIFICATION_CHANGE_TIME_TOPIC: {
                fileName = CodeConstant.MAIL_TEMPLATE_USER_UPDATE_TIME;
                break;
            }
            case CodeConstant.NOTIFICATION_TYPE_CANCEL_MEDICAL : {
                fileName = CodeConstant.MAIL_TEMPLATE_USER_CANCEL_MEDICAL;
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + typeNotification);
        }

        File file = ResourceUtils.getFile("classpath:static/" + fileName);
        return new String(Files.readAllBytes(file.toPath()));
    }

//    private String readBodyFromMailTemplate() throws IOException {
//        Path path = resourceLoader.getResource("classpath:static/mail_template.html").getFile().toPath();
//        List<String> data = Files.readAllLines(path, StandardCharsets.UTF_8);
//        String body = data.stream().map(item -> item.replaceAll("\t", "")).collect(Collectors.joining());
//        return body;
//    }
}
