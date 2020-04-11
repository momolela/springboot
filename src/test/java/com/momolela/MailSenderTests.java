package com.momolela;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailSenderTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    public void testSendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("mc音乐注册");
        message.setText("恭喜你成为mc音乐网的一员，momolela&caca");
        message.setFrom("1083910359@qq.com");
        message.setTo("1548011224@qq.com");
        mailSender.send(message);
    }

    @Test
    public void testSendMimeMail() {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject("mc音乐注册");
            helper.setText("<div>恭喜你成为mc音乐网的一员，<strong>momolela&caca</strong></div>", true);
            helper.setFrom("1083910359@qq.com");
            helper.setTo("1548011224@qq.com");

            helper.addAttachment("momolela.png", new File("E:\\wlop.png"));
            helper.addAttachment("caca.png", new File("E:\\wlop.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }
}
