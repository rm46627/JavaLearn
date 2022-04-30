package web.javaLearn.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import web.javaLearn.model.ActivationEmail;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    @Async
    public void sendMail(ActivationEmail activationEmail){
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("javalearn@verification.com");
            messageHelper.setTo(activationEmail.getRecipient());
            messageHelper.setSubject(activationEmail.getSubject());
            messageHelper.setText(mailContentBuilder.build(activationEmail.getBody()));
        };
        mailSender.send(messagePreparator);
        log.info("Email sent.");
    }
}

@Service
@AllArgsConstructor
class MailContentBuilder {

    private final TemplateEngine templateEngine;

    String build(String message){
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mailTemplate", context);
    }
}
