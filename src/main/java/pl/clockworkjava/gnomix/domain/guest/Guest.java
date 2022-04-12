package pl.clockworkjava.gnomix.domain.guest;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;


@Data
@Setter(value = AccessLevel.NONE)
@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;


    private  String firstName;
    private  String lastName;
    private  LocalDate birthDate;
    private  Gender gender;
    private boolean vip;


    public Guest( String firstName,
                 String lastName, LocalDate birthDate,
                 Gender gender, boolean vip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.vip=vip;

    }

    public Guest() {

    }

    public void update(String firstName,
                       String lastName, LocalDate birthDate,
                       Gender gender, boolean vip){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.vip=vip;
    }



    @Override
    public String toString() {
        return "Imię: " + firstName +
                ", Nazwisko: " + lastName  +
                ", Data urodzenia: " + birthDate +
                ", Płeć: " + gender+", VIP: "+vip ;
    }
}
