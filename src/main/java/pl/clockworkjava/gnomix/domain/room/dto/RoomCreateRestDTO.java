package pl.clockworkjava.gnomix.domain.room.dto;

import pl.clockworkjava.gnomix.domain.room.BedType;

import java.util.List;

public record RoomCreateRestDTO (
    String roomNumber,
    List<BedType> beds,
    String description,
    List<String> photosUrl)
    {}


