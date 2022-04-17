package pl.clockworkjava.gnomix.util.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    //
    private final JavaMailSender sender;
    //
    @Value("${gnomix.protocol}")
    private String protocol;
    @Value("${gnomix.domain}")
    private String domain;
    @Value("${gnomix.port}")
    private String port;
    private String confirmationEndpoint = "reservation/confirm";

    @Autowired
    public EmailService(JavaMailSender sender) {
        this.sender = sender;
    }

    public void sendConfirmationEmail(String email, long reservationID) {

        String endpoint = String.format("%s://%s:%s/%s/%d", protocol, domain, port, confirmationEndpoint, reservationID);
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom("systemgnomix@gmail.com");
        mail.setSubject("Potwierdzenie rezerwacji hotelu TEST TEST TEST");
        mail.setText("Dziękujemy za rezerwację, by ją potwierdzić, kliknij w link " + endpoint);
        this.sender.send(mail);
    }

}
