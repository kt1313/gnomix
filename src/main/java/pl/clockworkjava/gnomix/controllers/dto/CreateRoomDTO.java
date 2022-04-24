package pl.clockworkjava.gnomix.controllers.dto;

import java.util.List;

public record CreateRoomDTO(
        String number,
        String bedsDesc,
        String description,
        List<String> photosUrl)
        {}
