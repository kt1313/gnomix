package pl.clockworkjava.gnomix.util.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.clockworkjava.gnomix.domain.reservation.events.TempReservationCreatedEvent;

@Component
public class HandleMailTempReservationCreateEvent implements ApplicationListener<TempReservationCreatedEvent> {

    private final pl.clockworkjava.gnomix.util.mail.EmailService emailService;

    @Autowired
    public HandleMailTempReservationCreateEvent(pl.clockworkjava.gnomix.util.mail.EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void onApplicationEvent(TempReservationCreatedEvent event) {
        System.out.println("MAIL: Handle event by implementing AppListener");
        this.emailService.sendConfirmationEmail(event.getEmail(), event.getReservationId());
    }
}
