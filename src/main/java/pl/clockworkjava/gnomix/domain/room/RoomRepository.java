package pl.clockworkjava.gnomix.domain.room;


import org.springframework.stereotype.Repository;
import pl.clockworkjava.gnomix.domain.guest.Gender;
import pl.clockworkjava.gnomix.domain.guest.Guest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Repository
public class RoomRepository {

    public List<Room> findAllRooms() {

        Room room1 = new Room("10A");
        Room room2 = new Room("103");

        return Arrays.asList(room1, room2);
    }
}

