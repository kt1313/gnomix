package pl.clockworkjava.gnomix.util.email;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.clockworkjava.gnomix.domain.reservation.events.TempReservationCreatedEvent;

public class HandleEmailTempReservationCreateEvent {

    @Component
    public class HandleTempReservationCreatedEvent implements ApplicationListener<TempReservationCreatedEvent> {
        @Override
        public void onApplicationEvent(TempReservationCreatedEvent event) {
            System.out.println("EMAIL: - Handle eventb by implementing AppListener");
        }
    }
}
