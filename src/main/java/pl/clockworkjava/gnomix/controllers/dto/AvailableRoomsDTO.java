package pl.clockworkjava.gnomix.controllers.dto;

import pl.clockworkjava.gnomix.domain.room.BedType;
import pl.clockworkjava.gnomix.domain.room.Room;

import java.util.List;

public class AvailableRoomsDTO {

    private final String number;
    private final long id;
    private final List<BedType> beds;
    private final int size;

    public AvailableRoomsDTO(String number, long id, List<BedType> beds, int size) {
        this.number = number;
        this.id = id;
        this.beds = beds;
        this.size = size;
    }

    public AvailableRoomsDTO(Room room){
        this.number= room.getRoomNumber();
        this.id= room.getRoomId();
        this.beds=room.getBeds();
        this.size=room.getSize();
    }

    public String getNumber() {
        return number;
    }

    public long getId() {
        return id;
    }

    public List<BedType> getBeds() {
        return beds;
    }

    public int getSize() {
        return size;
    }
}