package pl.clockworkjava.gnomix.domain.room;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Data
public class Room {

    private final long roomId;
    private final String number;
    private final List<BedType> beds;
    private  final Integer size;



    public Room(String number, List<BedType> beds) {

        if (beds==null){
            throw new IllegalArgumentException("Beds list cannot be null");
        }

        this.number = number;
        List<BedType> bedsField=new ArrayList<>(beds);
         Collections.copy(bedsField,beds);
         this.beds=bedsField;
         this.size=this.beds.stream().mapToInt(BedType::getSize).sum();

         this.roomId=UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

    }

    @Override
    public String toString() {
        return "Pokoj oznaczony jako:  " + number;
    }
}
