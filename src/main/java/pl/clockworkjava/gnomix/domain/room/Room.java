package pl.clockworkjava.gnomix.domain.room;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Room {

    private final String number;

    private final List<BedType> beds;

    private  final Integer size;

//    public Room(String number) {
//        this.number = number;
//    }

    public Room(String number, List<BedType> beds) {

        if (beds==null){
            throw new IllegalArgumentException("Beds list cannot be null");
        }

        this.number = number;
        List<BedType> bedsField=new ArrayList<>(beds);
         Collections.copy(bedsField,beds);
         this.beds=bedsField;
         this.size=this.beds.stream().mapToInt(BedType::getSize).sum();

//        for (BedType bed:beds
//             ) {
//            this.size=size+bed.getSize();
//        }
    }

    @Override
    public String toString() {
        return "Pokoj oznaczony jako:  " + number;
    }
}
