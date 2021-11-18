package pl.clockworkjava.gnomix.domain.room;

import lombok.Data;
import pl.clockworkjava.gnomix.domain.guest.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Data
public class Room {

    private final long roomId;
    private  String roomNumber;
    private  List<BedType> beds;
    private  Integer size;


    public Room(String roomNumber, List<BedType> beds) {

        this.roomId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

        if (beds == null) {
            throw new IllegalArgumentException("Beds list cannot be null");
        }

        this.roomNumber = roomNumber;
        List<BedType> bedsField = new ArrayList<>(beds);
        Collections.copy(bedsField, beds);
        this.beds = bedsField;
        this.size = this.beds.stream().mapToInt(BedType::getSize).sum();


    }

    @Override
    public String toString() {
        return "Pokoj oznaczony jako:  " + roomNumber;
    }


}
