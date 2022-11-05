package pl.clockworkjava.gnomix.domain.room.dto;

import pl.clockworkjava.gnomix.domain.room.BedType;

import java.util.List;
import java.util.Objects;

public class RoomCreateRestDTO {

    private String roomNumber;
    private List<BedType> beds;
    private String description;
    private List<String> photosUrls;

    public RoomCreateRestDTO(String roomNumber, List<BedType> beds, String description, List<String> photosUrls) {
        this.roomNumber = roomNumber;
        this.beds = beds;
        this.description = description;
        this.photosUrls = photosUrls;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public List<BedType> getBeds() {
        return beds;
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
        RoomCreateRestDTO that = (RoomCreateRestDTO) o;
        return Objects.equals(roomNumber, that.roomNumber) && Objects.equals(beds, that.beds) && Objects.equals(description, that.description) && Objects.equals(photosUrls, that.photosUrls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, beds, description, photosUrls);
    }
}