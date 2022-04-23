package pl.clockworkjava.gnomix.domain.room;

import lombok.Data;
import pl.clockworkjava.gnomix.domain.reservation.Reservation;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roomId;

    private String roomNumber;

    @ElementCollection(targetClass = BedType.class)
    private List<BedType> beds;

    private Integer size;

    @ElementCollection(targetClass = String.class)
    private List<String> photosUrl;

    private String description;


    public Room(String roomNumber, List<BedType> beds) {

        if (beds == null) {
            throw new IllegalArgumentException("Beds list cannot be null");
        }

        this.roomNumber = roomNumber;
        List<BedType> bedsField = new ArrayList<>(beds);
//        Collections.copy(bedsField, beds);
        this.beds = bedsField;
//        this.size = this.beds.stream().mapToInt(BedType::getSize).sum();

        updateBeds();
    }

    public Room(String roomNumber, List<BedType> beds, String description, List<String> photosUrl) {
        this(roomNumber, beds);
        this.description = description;
        this.photosUrl = photosUrl;

    }

    public Room(){}

    public String getBedsAsStr() {

        String bedAsstr = this.beds.stream()
                .map(bedTypeStringFunction()).collect(Collectors.joining("+"));
        return bedAsstr;
    }


    public void update(String roomNumber, List<BedType> beds) {
        this.roomNumber = roomNumber;
        this.beds = beds;
        updateBeds();
    }

    public void update(String roomNumber, List<BedType> beds, String description, List<String> photosUrl) {
        this.description = description;
        this.photosUrl = photosUrl;
        this.updateBeds();
    }

    private Function<BedType, String> bedTypeStringFunction() {
        return bedType -> {
            if (bedType == BedType.DOUBLE) {
                return "2";
            } else if (bedType == BedType.SINGLE) {
                return "1";
            } else {
                throw new IllegalStateException();
            }
        };
    }

    private void updateBeds() {
        this.size =
                this.beds.stream().mapToInt(BedType::getSize).sum();
    }

    @Override
    public String toString() {
        return "Pokoj oznaczony jako:  " + roomNumber;
    }


}
