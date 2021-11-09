package pl.clockworkjava.gnomix.domain.room;


import org.springframework.stereotype.Repository;
import pl.clockworkjava.gnomix.domain.guest.Gender;
import pl.clockworkjava.gnomix.domain.guest.Guest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RoomRepository {

    List<Room> rooms = new ArrayList<>();

    public RoomRepository() {
        Room room1 = new Room("10A", Arrays.asList(BedType.SINGLE));
        Room room2 = new Room("103", Arrays.asList(BedType.DOUBLE));
        this.rooms.add(room1);
        this.rooms.add(room1);


    }

    public List<Room> findAllRooms() {

       return this.rooms;
    }

    public Room createNewRoom(String roomNumber, List<BedType> beds) {

Room room = new Room(roomNumber, beds);
this.rooms.add(room);
return room;
    }
}

