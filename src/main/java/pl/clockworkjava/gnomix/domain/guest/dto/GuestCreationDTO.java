package pl.clockworkjava.gnomix.domain.guest.dto;


import org.springframework.format.annotation.DateTimeFormat;
import pl.clockworkjava.gnomix.domain.guest.Gender;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Objects;

public class GuestCreationDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Past(message = "Data urodzenia musi być w przeszłości")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    private Gender gender;

    private boolean vip;

    public GuestCreationDTO(String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String vip) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;

        if(vip==null || !vip.equals("on")) {
            this.vip = false;
        } else {
            this.vip = true;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public boolean isVip() {
        return vip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestCreationDTO that = (GuestCreationDTO) o;
        return vip == that.vip && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(dateOfBirth, that.dateOfBirth) && gender == that.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth, gender, vip);
    }
}
