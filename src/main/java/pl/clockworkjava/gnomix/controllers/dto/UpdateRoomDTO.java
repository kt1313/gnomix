package pl.clockworkjava.gnomix.controllers.dto;

import java.util.List;

public record UpdateRoomDTO(
        long id,
        String roomNumber,
        String bedsDescription,
        String descrition,
        List<String> photosUrl) {
}
