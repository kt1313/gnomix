package pl.clockworkjava.gnomix.domain.guest.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.clockworkjava.gnomix.domain.guest.Gender;

import java.time.LocalDate;

@Data
public class GuestUpdateDTO {

    private final long id;
    private final String firstName;
    private final String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate dateOfBirth;

    private final Gender gender;

    private final String customerId;
}
