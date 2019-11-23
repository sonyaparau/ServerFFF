package com.mobile.freeforfun.business.utils;

import com.mobile.freeforfun.business.dto.UserDto;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class MailService {

    public static void sendMail(String userEmail, String subject, String message) throws MessagingException {
        String username = "freeforfunmobile@gmail.com";
        String password = "margasonya";
        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        //Establishing a session with required user details
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        MimeMessage msg = new MimeMessage(session);
        String to = userEmail;
        InternetAddress[] address = InternetAddress.parse(to, true);
        msg.setRecipients(Message.RecipientType.TO, address);
        msg.setFrom(new InternetAddress(username));
        msg.setSentDate(new Date());
        msg.setSubject(subject);
        msg.setText(message);
        msg.setHeader("XPriority", "1");
        Transport.send(msg);
    }
}
