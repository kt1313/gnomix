package pl.clockworkjava.gnomix.domain.reservation.events;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TempReservationCreatedEvent {

    public final LocalDateTime creationDate;
    public final String email;
    public final long reservationID;

    public TempReservationCreatedEvent(String email, long reservationID){
        this.email=email;
        this.reservationID=reservationID;
        this.creationDate=LocalDateTime.now();
    }


}
