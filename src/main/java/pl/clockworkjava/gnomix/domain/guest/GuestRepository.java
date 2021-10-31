package pl.clockworkjava.gnomix.domain.guest;


import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Repository
public class GuestRepository {


    public List<Guest> findAllGuests() {

        Guest guest = new Guest("Pawel", "Cwik", LocalDate.of(1986, 11, 13), Gender.MALE);
        Guest gabriel = new Guest("Gabriel", "Cwik", LocalDate.of(2016, 12, 13), Gender.MALE);

        return Arrays.asList(guest, gabriel);
    }

}
