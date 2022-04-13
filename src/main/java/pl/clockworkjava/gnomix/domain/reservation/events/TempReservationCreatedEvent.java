package pl.clockworkjava.gnomix.domain.reservation.events;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

//@Data
public class TempReservationCreatedEvent extends ApplicationEvent {

    public final LocalDateTime creationDate;
    public final String email;
    public final long reservationID;



    public TempReservationCreatedEvent(Object context,String email, long reservationID){
        super(context);
        this.email=email;
        this.reservationID=reservationID;
        this.creationDate=LocalDateTime.now();
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getEmail() {
        return email;
    }

    public long getReservationID() {
        return reservationID;
    }
}
