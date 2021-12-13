package pl.clockworkjava.gnomix.controllers.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.clockworkjava.gnomix.domain.guest.Gender;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class GuestCreationDTO {

@NotBlank
    private final String firstName;
    @NotBlank
    private final String lastName;
    @Past(message = "Data w w przeszłości musi byc")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate dateOfBirth;
    private final Gender gender;
    private final boolean confirmed;
}
