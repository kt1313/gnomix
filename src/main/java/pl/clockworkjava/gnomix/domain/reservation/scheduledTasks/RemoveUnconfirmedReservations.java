package pl.clockworkjava.gnomix.domain.reservation.scheduledTasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pl.clockworkjava.gnomix.domain.reservation.ReservationService;

@Configuration
@EnableScheduling
public class RemoveUnconfirmedReservations {

    private final ReservationService reservationService;

    @Autowired
    public RemoveUnconfirmedReservations(ReservationService rs) {
        this.reservationService = rs;
    }

    @Scheduled(initialDelayString = "PT15M", fixedDelayString = "PT15M")
    public void removeUnconfirmedReservations() {
        this.reservationService.removeUnconfirmedReservations();
    }

}
