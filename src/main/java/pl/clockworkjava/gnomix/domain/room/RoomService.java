package pl.clockworkjava.gnomix.domain.room;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.clockworkjava.gnomix.controllers.dto.GuestUpdateDTO;
import pl.clockworkjava.gnomix.domain.guest.Guest;
import pl.clockworkjava.gnomix.domain.guest.GuestRepository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RoomService {


    private RoomRepository repository;

    @Autowired
    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    public List<Room> findAllRooms() {
        return this.repository.findAllRooms();
    }

    public Room createNewRoom(String roomNumber, String bedsDescription) {
        String[] splitedBedsDescription = bedsDescription.split("\\+");
        List<BedType> beds = Arrays.stream(splitedBedsDescription)
                .map(stringToBedTypeMapping)
                .collect(Collectors.toList());
        return this.repository.createNewRoom(roomNumber, beds);
    }

    private final Function<String, BedType> stringToBedTypeMapping = value -> {
        if ("1".equals(value)) {
            return BedType.SINGLE;
        } else if ("2".equals(value)) {
            return BedType.DOUBLE;
        } else {
            throw new IllegalArgumentException();
        }
    };

    public void removeRoomById(long id){

        this.repository.removeRoomById(id);
    }

    public Room getRoomById(long id) {
      return   this.repository.getRoomId(id);

    }


}
