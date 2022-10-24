package pl.clockworkjava.gnomix.domain.guest;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Setter(value = AccessLevel.NONE)
@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    private boolean vip;
    private String customerId;

    @Column(name = "phone")
    private String phoneNumber;

    Guest() {

    }

    public Guest(String firstName, String lastName, LocalDate birthDate, Gender gender) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.vip = false;
    }

    public Guest(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName= lastName;
        this.birthDate = dateOfBirth;
    }

    public void update(String firstName, String lastName, LocalDate birthDate, Gender gender, String customerId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
//        this.vip = false;
        this.customerId = customerId;
    }

    public Guest(String firstName, String lastName, LocalDate birthDate, Gender gender, boolean vip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.vip = vip;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setPhoneNumber(String phoneNumber) {

        if(phoneNumber.length()>20) {
            throw new IllegalArgumentException("Phone number to long");
        }

        this.phoneNumber = phoneNumber;
    }
}
