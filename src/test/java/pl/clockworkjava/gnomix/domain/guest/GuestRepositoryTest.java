//package pl.clockworkjava.gnomix.domain.guest;
//
//import org.apache.tomcat.jni.Local;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@DataJpaTest
//public class GuestRepositoryTest {
//
//    @Autowired
//    private GuestRepository guestRepository;
//
//    @Test
//    public void getCustomerById() {
//        LocalDate n = LocalDate.now();
//
//        Guest g1 = new Guest("Pawel", "Cwik", n, Gender.MALE);
//        g1.setCustomerId("A123");
//        Guest g2 = new Guest("Pawel", "Cwik", n, Gender.FEMALE);
//        g2.setCustomerId("A124");
//
//        this.guestRepository.save(g1);
//        this.guestRepository.save(g2);
//
//
//        Optional<Guest> result = this.guestRepository.findTop1ByCustomerIdAndFirstNameAndLastNameAndBirthDate(
//                "A123",
//                "Pawel",
//                "Cwik",
//                n
//        );
//
//        assertTrue(result.isPresent());
//
//        result = this.guestRepository.findTop1ByCustomerIdAndFirstNameAndLastNameAndBirthDate(
//                "ASSS",
//                "Pawel",
//                "Cwik",
//                n
//        );
//
//        assertTrue(result.isEmpty());
//    }
//
//
//}
