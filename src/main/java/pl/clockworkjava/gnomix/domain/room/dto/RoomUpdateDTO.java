package pl.clockworkjava.gnomix.domain.room.dto;

import java.util.List;

public record RoomUpdateDTO(
        long id,
        String roomNumber,
        String bedsDescription,
        String descrition,
        List<String> photosUrl) {
}
