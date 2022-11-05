package pl.clockworkjava.gnomix.domain.room.dto;

import java.util.List;
import java.util.Objects;

public class  RoomCreateDTO {

    private String number;
    private String bedsDesc;
    private String description;
    private List<String> photosUrls;

    public RoomCreateDTO(String number, String bedsDesc, String description, List<String> photosUrls) {
        this.number = number;
        this.bedsDesc = bedsDesc;
        this.description = description;
        this.photosUrls = photosUrls;
    }

    public String getNumber() {
        return number;
    }

    public String getBedsDesc() {
        return bedsDesc;
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
        RoomCreateDTO that = (RoomCreateDTO) o;
        return Objects.equals(number, that.number) && Objects.equals(bedsDesc, that.bedsDesc) && Objects.equals(description, that.description) && Objects.equals(photosUrls, that.photosUrls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, bedsDesc, description, photosUrls);
    }
}
