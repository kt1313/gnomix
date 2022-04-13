package pl.clockworkjava.gnomix.domain.reports;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.clockworkjava.gnomix.domain.reservation.events.TempReservationCreatedEvent;

@Component
public class HandleTempReservationCreatedEvent implements ApplicationListener<TempReservationCreatedEvent> {
    @Override
    public void onApplicationEvent(TempReservationCreatedEvent event) {
        System.out.println("IMPLEMENTED WAY");
        System.out.println("TIMESTAMP:"+event.getTimestamp());
        System.out.println("Handling event creaed at"+event.toString());
    }
}
