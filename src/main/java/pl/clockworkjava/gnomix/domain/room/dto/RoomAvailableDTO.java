package pl.clockworkjava.gnomix.domain.room.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.clockworkjava.gnomix.domain.room.BedType;
import pl.clockworkjava.gnomix.domain.room.Room;

import java.util.List;
import java.util.Objects;

public class RoomAvailableDTO {

    private final String number;
    private final long id;
    private final List<BedType> beds;
    private final int size;
    private final String description;
    private final List<String> photosUrls;

    public RoomAvailableDTO(String number, long id, List<BedType> beds, int size, String description, List<String> photosUrls) {
        this.number = number;
        this.id = id;
        this.beds = beds;
        this.size = size;
        this.description = description;
        this.photosUrls = photosUrls;
    }

    public RoomAvailableDTO(Room room) {
        this.number = room.getNumber();
        this.id = room.getId();
        this.beds = room.getBeds();
        this.size = room.getSize();
        this.description = room.getDescription();
        this.photosUrls = room.getPhotosUrls();
    }

    @JsonProperty("roomNumber")
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

    public String getDescription() {
        return description;
    }

    public List<String> getPhotosUrls() {
        return photosUrls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomAvailableDTO that = (RoomAvailableDTO) o;
        return id == that.id && size == that.size && Objects.equals(number, that.number) && Objects.equals(beds, that.beds) && Objects.equals(description, that.description) && Objects.equals(photosUrls, that.photosUrls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, id, beds, size, description, photosUrls);
    }
}
