package pl.clockworkjava.gnomix.util.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender sender;

    @Autowired
    public EmailService(JavaMailSender sender){
        this.sender=sender;
    }
    public void sendConfirmationEmail(String email, long reservationID) {
        String text="http://localhost:8080/reservation/confirm/"+reservationID;
        SimpleMailMessage mail=new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom("systemgnomix@gmail.com");
        mail.setSubject("Potweirdzenie rezerwacji");
        mail.setText("Dziękujemy za rezerwację, by ją potwierdzić, kliknij w link "+text);
        this.sender.send(mail);
    }
}
