package org.calsync.Service;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailService {

    private static final String USERNAME;
    private static final String PASSWORD;

    private static final Properties props = new Properties();



    //Pulls username and password from email.properties file to use for sending out emails
    static {
        Properties configProps = new Properties();
        try (InputStream input = EmailService.class.getClassLoader().getResourceAsStream("email.properties")){
            if (input != null){
                    configProps.load(input);
            } else {
                System.err.println("email.properties not found");
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        USERNAME = configProps.getProperty("email.username", "");
        PASSWORD = configProps.getProperty("email.password", "");

        // Set up SMTP properties for Gmail
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


    }


    public static boolean sendEmail(String recipient, String subject, String text) {

        // Create a session with an authenticator using your credentials
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            // Create the email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(text);

            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully to " + recipient);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error sending email: " + e.getMessage());
            return false;
        }
    }



}

