package pl.clockworkjava.gnomix.domain.guest;

import lombok.Data;

import java.time.LocalDate;


@Data
public class Guest {

    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private  final  Gender gender;

    @Override
    public String toString() {
        return "Imię: " + firstName +
                ", Nazwisko: " + lastName  +
                ", Data urodzenia: " + birthDate +
                ", Płeć: " + gender ;
    }
}
