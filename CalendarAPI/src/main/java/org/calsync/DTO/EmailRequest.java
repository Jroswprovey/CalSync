package org.calsync.DTO;

public class EmailRequest {
    private String recipient;
    private String subject;
    private String text;

    //setters
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setText(String text) {
        this.text = text;
    }

    //getters
    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
}
