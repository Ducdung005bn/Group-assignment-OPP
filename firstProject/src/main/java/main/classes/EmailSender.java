package main.classes;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;

public class EmailSender {

    // SMTP server configuration
    private final String smtpHost = "smtp.gmail.com";
    private final String smtpPort = "587";
    private final String senderEmail = "ducdung005bn@gmail.com";
    private final String senderPassword = "lyvl zcfp xsod gtlg"; // App Password or email password

    // Method to send an email
    public boolean sendEmail(String recipientEmail, String subject, String messageText) {
        // Configure SMTP properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // STARTTLS
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);

        // Authenticate the sender's email account
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(messageText); // Send as plain text

            // Send the email
            Transport.send(message);
            JOptionPane.showMessageDialog(null,
                    "Email sent successfully to " + recipientEmail,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null,
                    "Error sending email: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
