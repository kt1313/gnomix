package pl.clockworkjava.gnomix.domain.guest.dto;

import org.springframework.format.annotation.DateTimeFormat;
import pl.clockworkjava.gnomix.domain.guest.Gender;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Objects;

//@Data
public class GuestCreationDTO {

    @NotBlank
    private final String firstName;
    @NotBlank
    private final String lastName;
    @Past(message = "Data w przeszłości musi byc")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate dateOfBirth;
    private final Gender gender;
//    private final boolean confirmed;
    private final boolean vip;

    public GuestCreationDTO(@NotBlank String firstName, @NotBlank String lastName,
                            @Past(message = "Data w przeszłości musi byc") LocalDate dateOfBirth,
                            Gender gender, String vip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;

        if(vip==null || !vip.equals("on")){
            this.vip=false;
        }else{
            this.vip=true;
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

//    public boolean isConfirmed() {
//        return confirmed;
//    }
    public boolean isVip(){
        return vip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestCreationDTO dto = (GuestCreationDTO) o;
        return vip == dto.vip &&
                Objects.equals(firstName, dto.firstName) &&
                Objects.equals(lastName, dto.lastName) &&
                Objects.equals(dateOfBirth, dto.dateOfBirth) &&
                gender == dto.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth, gender, vip);
    }
}