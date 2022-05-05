package pl.clockworkjava.gnomix.domain.room;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
        return this.repository.findAll();
    }

    public Room createNewRoom(String roomNumber, String bedsDescription, String description, List<String> photosUrl) {
        List<BedType> beds = getBedTypesList(bedsDescription);

        Room newOne = new Room(roomNumber, beds, description, photosUrl);

        return this.repository.save(newOne);
    }
    public Room createNewRoom(String roomNumber, List<BedType> beds, String description, List<String> photosUrl) {

        Room newOne = new Room(roomNumber, beds, description, photosUrl);

        return this.repository.save(newOne);
    }

    public void removeRoomById(long id) {

        this.repository.deleteById(id);
    }

    public Room findById(long id) {
        return this.repository.getById(id);

    }


    public void update(long id, String roomNumber, String bedsDescription, String description, List<String> photosUrl) {
        Room roomToUpdate = this.repository.getById(id);
        List<BedType> beds = getBedTypesList(bedsDescription);
        roomToUpdate.update(roomNumber, beds, description, photosUrl);
        this.repository.save(roomToUpdate);
    }

    private List<BedType> getBedTypesList(String bedsDescription) {
        String[] splitedBedsDescription = bedsDescription.split("\\+");
        return Arrays.stream(splitedBedsDescription)
                .map(stringToBedTypeMapping)
                .collect(Collectors.toList());
    }

    private final Function<String, BedType> stringToBedTypeMapping
            = value -> {
        if ("1".equals(value)) {
            return BedType.SINGLE;
        } else if ("2".equals(value)) {
            return BedType.DOUBLE;
        } else {
            throw new IllegalArgumentException();
        }
    };

    public List<Room> getRoomsForSize(int size) {
        if (size <= 0) {
            return new ArrayList<>();
        }
        return this.repository.findAll()
                .stream().filter(room -> room.getSize() >= size)
                .collect(Collectors.toList());

    }

    public Optional<Room> getRoomById(long roomId) {
        return this.repository.findById(roomId);
    }
}
