package pl.clockworkjava.gnomix.domain.room;

import lombok.Data;
import pl.clockworkjava.gnomix.domain.guest.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class Room {

    private final long roomId;
    private String roomNumber;
    private List<BedType> beds;
    private Integer size;


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

    public String getBedsAsStr() {

        String bedAsstr = this.beds.stream()
                .map(bedTypeStringFunction).collect(Collectors.joining("+"));
        return bedAsstr;

    }


    public void update(String roomNumber, List<BedType> beds) {
    this.roomNumber=roomNumber;
    this.beds=beds;
    }
    private Function<BedType, String> bedTypeStringFunction = bedType -> {
        if (bedType == BedType.DOUBLE) {
            return "2";
        } else if (bedType == BedType.SINGLE) {
            return "1";
        } else {
            throw new IllegalStateException();
        }
    };


    @Override
    public String toString() {
        return "Pokoj oznaczony jako:  " + roomNumber;
    }



}
