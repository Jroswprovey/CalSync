// package name : package com.calsync.email

package com.calsync.email;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailService {

    // Retrieve email credentials from environment variables
    private static final String USERNAME = System.getenv("EMAIL_USER");
    private static final String PASSWORD = System.getenv("EMAIL_PASS");

    public static void sendEmail(String to, String subject, String text) {
        // Set up SMTP properties for Gmail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);

            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully to " + to);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error sending email: " + e.getMessage());
        }
    }

    // Main method for testing the email functionality
    public static void main(String[] args) {
        // Replace with a valid recipient email for testing
        sendEmail("recipient@example.com", "Test Email", "This is a test email from CalSync's email service.");
    }
}

