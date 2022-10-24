package pl.clockworkjava.gnomix.domain.reservation;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ReservationCreateTmpRestDTO {

    @NotNull
    private long roomId;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fromDate;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate toDate;

    @Email
    @NotNull
    private String email;

    public ReservationCreateTmpRestDTO(long roomId, LocalDate fromDate, LocalDate toDate, String email) {
        this.roomId = roomId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.email = email;
    }

    public long getRoomId() {
        return roomId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public String getEmail() {
        return email;
    }
}
