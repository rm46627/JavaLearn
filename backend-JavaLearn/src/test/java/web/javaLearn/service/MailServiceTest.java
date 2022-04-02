package web.javaLearn.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import web.javaLearn.model.ActivationEmail;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MailServiceTest {

    @Mock
    JavaMailSender mailSender;
    @Mock
    MailContentBuilder mailContentBuilder;

    @InjectMocks
    MailService mailService;

    @Test
    public void sendMail_Success(){
        // given
        ActivationEmail activationEmail = ActivationEmail.builder()
                .subject("test")
                .recipient("test")
                .body("test")
                .build();

        // call
        mailService.sendMail(activationEmail);

        // test
        verify(mailSender, times(1)).send(Mockito.any(MimeMessagePreparator.class));
    }
}
