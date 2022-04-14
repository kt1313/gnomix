package pl.clockworkjava.gnomix.domain.reports;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.clockworkjava.gnomix.domain.reservation.events.TempReservationCreatedEvent;

@Component
public class HandleTempReservationCreateEvent implements ApplicationListener<TempReservationCreatedEvent> {
    @Override
    public void onApplicationEvent(TempReservationCreatedEvent event) {
        System.out.println("Handle eventb by implementing AppListener");
    }
}
