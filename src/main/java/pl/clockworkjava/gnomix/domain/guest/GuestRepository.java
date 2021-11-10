package pl.clockworkjava.gnomix.domain.guest;


import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class GuestRepository {

List<Guest> guests=new ArrayList<>();

public GuestRepository(){
    Guest guest = new Guest("Pawel", "Cwik", LocalDate.of(1986, 11, 13), Gender.MALE);
    Guest gabriel = new Guest("Gabriel", "Cwik", LocalDate.of(2016, 12, 13), Gender.MALE);
this.guests.add(guest);
this.guests.add(gabriel);
}

    public List<Guest> findAllGuests() {

        return this.guests;
    }

    public void createNewGuest
            (String firstName, String lastName,
             LocalDate parsedDate, Gender gender) {

    Guest newGuest=new Guest(firstName,lastName,parsedDate,Gender.MALE);
this.guests.add(newGuest);
    }

    public void removeById(long id){

    Guest guestToBeDeleted=this.getGuestById(id);

    this.guests.remove(guestToBeDeleted);
    }

    public Guest getGuestById(long id){

        Guest guestById=this.guests.stream()
                .filter(guest -> guest.getId()==id)
                .findFirst()
                .orElseThrow();

        return guestById;

    }
}
