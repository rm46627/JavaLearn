package web.javaLearn.service;

import org.hibernate.sql.Template;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import web.javaLearn.model.User;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MailContentBuilderTest {

    @Mock
    TemplateEngine templateEngine;

    @InjectMocks
    MailContentBuilder mailContentBuilder;

//    @Test
//    public void buildMailText_Success(){
//        // given
//        String message = "Thats your activation email.";
//        Context context = new Context();
//        context.setVariable("message", message);
//
//        //
//        when(templateEngine.process("mailTemplate", context)).thenReturn(message);
//
//        // call
//        String result = mailContentBuilder.build(message);
//
//        // test
//        Assert.assertEquals(result, message);
//    }
}
