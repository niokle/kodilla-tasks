package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    SimpleEmailService simpleEmailService;

    @Mock
    JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail() {
        //given
        Mail mail = new Mail("test@test.com", "test2@test.com", "test", "to jest test");

        MimeMessagePreparator mimeMessagePreparator = simpleEmailService.createMimeMessage(mail);

        //when
        simpleEmailService.send(mail);

        //then
        Mockito.verify(javaMailSender, times(1)).send(refEq(mimeMessagePreparator));
    }
}