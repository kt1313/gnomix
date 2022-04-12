package pl.clockworkjava.gnomix.domain.reports;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.clockworkjava.gnomix.domain.reservation.events.TempReservationCreatedEvent;

@Component
public class HandleEvents {

    @EventListener
    public void handleTempReservationCreatedEvents(TempReservationCreatedEvent event){
        System.out.println("Handling event creaed at"+event.toString());
    }
}
