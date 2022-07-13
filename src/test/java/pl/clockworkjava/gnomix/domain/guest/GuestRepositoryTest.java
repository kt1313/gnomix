package pl.clockworkjava.gnomix.domain.guest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class GuestRepositoryTest {

    @Autowired
    private GuestRepository guestRepository;

    @Test

    public void getCustomerById() {

        LocalDate n = LocalDate.now();

        Guest g1 = new Guest("Pawel", "Cwik", n, Gender.MALE, true);
        g1.setCustomerId("A1234");

        Guest g2 = new Guest("Pawel", "Cwik", n, Gender.FEMALE, true);
        g2.setCustomerId("Awww");

        this.guestRepository.save(g1);
        this.guestRepository.save(g2);

        Optional<Guest> result=this.guestRepository.findTop1ByCustomerIdAndFirstNameAndLastNameAndBirthDate("" +
                "A1234",
                "Pawel",
                "Cwik",
                n
        );

        assertTrue(result.isPresent());

         result=this.guestRepository.findTop1ByCustomerIdAndFirstNameAndLastNameAndBirthDate("" +
                        "A333",
                "Pawel",
                "Cwik",
                n
        );

        assertTrue(result.isEmpty());
    }
}
