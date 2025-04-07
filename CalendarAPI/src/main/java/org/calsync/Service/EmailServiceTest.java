package org.calsync.Service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class EmailServiceTest {

    @Test
    void sendEmail() {
        assertTrue(EmailService.sendEmail("jroswprovey@gmail.com", "test", "test"));
    }
}