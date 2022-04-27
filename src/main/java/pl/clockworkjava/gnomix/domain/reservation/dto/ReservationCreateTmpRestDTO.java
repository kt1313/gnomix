package pl.clockworkjava.gnomix.domain.reservation.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ReservationCreateTmpRestDTO {

    @NotNull
    private final long roomId;
    @NotNull
    private final @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate fromDate;
    @NotNull
    private final @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate;
    @Email
    @NotNull
    private final String email;

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
