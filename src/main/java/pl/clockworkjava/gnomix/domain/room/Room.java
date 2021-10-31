package pl.clockworkjava.gnomix.domain.room;

import lombok.Data;

@Data
public class Room {

    private final String number;

    @Override
    public String toString() {
        return "Pokoj oznaczony jako:  " + number;
    }
}
