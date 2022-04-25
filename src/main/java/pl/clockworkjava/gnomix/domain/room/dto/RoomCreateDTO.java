package pl.clockworkjava.gnomix.domain.room.dto;

import java.util.List;

public record RoomCreateDTO(
        String number,
        String bedsDesc,
        String description,
        List<String> photosUrl)
        {}
