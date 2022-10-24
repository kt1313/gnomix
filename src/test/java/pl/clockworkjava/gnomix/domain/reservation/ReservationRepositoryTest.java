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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository repository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GuestRepository guestRepository;

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

        this.repository.save(r1);
        this.repository.save(r2);

        List<Reservation> result = this.repository.findByConfirmed(true);

        assertTrue(result.get(0).getOwner().getLastName().equals("Cwik"));

        List<Reservation> result2 = this.repository.findByConfirmed(false);

        assertTrue(result2.get(0).getOwner().getLastName().equals("Kowalski"));

    }


    @Test
    public void getByRoomId() {

        Guest g1 = new Guest("Pawel", "Cwik", LocalDate.now());
        Guest g2 = new Guest("Pawel", "Kowalski", LocalDate.now());

        this.guestRepository.save(g1);
        this.guestRepository.save(g2);

        Room room1 = new Room("10", Arrays.asList(BedType.DOUBLE));
        Room room2 = new Room("11", Arrays.asList(BedType.SINGLE));

        this.roomRepository.save(room1);
        this.roomRepository.save(room2);

        Reservation r1 = new Reservation(LocalDate.now(), LocalDate.now().plusDays(1), g1, room1);
        Reservation r2 = new Reservation(LocalDate.now(), LocalDate.now().plusDays(1), g2, room1);
        Reservation r3 = new Reservation(LocalDate.now(), LocalDate.now().plusDays(1), g2, room2);

        this.repository.save(r1);
        this.repository.save(r2);
        this.repository.save(r3);

        List<Reservation> result = this.repository.findByRoom_Id(room1.getId());

        assertTrue(result.size()==2);

    }
}
