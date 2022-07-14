package pl.clockworkjava.gnomix.domain.reservation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.clockworkjava.gnomix.domain.guest.Guest;
import pl.clockworkjava.gnomix.domain.guest.GuestRepository;
import pl.clockworkjava.gnomix.domain.room.BedType;
import pl.clockworkjava.gnomix.domain.room.Room;
import pl.clockworkjava.gnomix.domain.room.RoomRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GuestRepository guestRepository;

//    @Test
//    public void getConfirmed() {
//
//        Guest g1 = new Guest("Pawel", "Cwik", LocalDate.now());
//        Guest g2 = new Guest("Pawel", "Kowalski", LocalDate.now());
//        this.guestRepository.save(g1);
//        this.guestRepository.save(g1);
//
//        Room r = new Room("10", Arrays.asList(BedType.DOUBLE));
//        this.roomRepository.save(r);
//
//        Reservation r1 = new Reservation(LocalDate.now(), LocalDate.now().plusDays(1), g1, r);
//        r1.confirm();
//        Reservation r2 = new Reservation(LocalDate.now(), LocalDate.now().plusDays(1), g2, r);
//
//        this.reservationRepository.save(r1);
//        this.reservationRepository.save(r2);
//
//        List<Reservation> result = this.reservationRepository.findByConfirmed(true);
//        assertTrue(result.get(0).getOwner().getLastName().equals("Cwik"));
//
//        List<Reservation> result2 = this.reservationRepository.findByConfirmed(false);
//        assertTrue(result2.get(0).getOwner().getLastName().equals("Kowalski"));

    @Test
    public void getConfirmed() {
        Guest g1 = new Guest("Pawel", "Cwik", LocalDate.now());
        Guest g2 = new Guest("Pawel", "Kowalski", LocalDate.now());
        this.guestRepository.save(g1);
        this.guestRepository.save(g2);
        Room r = new Room("10", Arrays.asList(BedType.DOUBLE));
        this.roomRepository.save(r);
        Reservation r1 = new Reservation(LocalDate.now(), LocalDate.now().plusDays(1), g1, r);
        r1.confirm();
        Reservation r2 = new Reservation(LocalDate.now(), LocalDate.now().plusDays(1), g2, r);
        this.reservationRepository.save(r1);
        this.reservationRepository.save(r2);
        List<Reservation> result = this.reservationRepository.findByConfirmed(true);
        assertTrue(result.get(0).getOwner().getLastName().equals("Cwik"));
        List<Reservation> result2 = this.reservationRepository.findByConfirmed(false);
        assertTrue(result2.get(0).getOwner().getLastName().equals("Kowalski"));
    }
}
