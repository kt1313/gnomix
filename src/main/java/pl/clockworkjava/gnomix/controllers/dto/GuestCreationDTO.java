package pl.clockworkjava.gnomix.controllers.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;
import pl.clockworkjava.gnomix.domain.guest.Gender;

import java.time.LocalDate;

@Data
public class GuestCreationDTO {


    private final String firstName;
    private final String lastName;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private final LocalDate dateOfBirth;
    private final Gender gender;
}
