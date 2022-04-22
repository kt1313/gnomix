package pl.clockworkjava.gnomix.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.clockworkjava.gnomix.domain.reservation.ReservationService;
import pl.clockworkjava.gnomix.domain.room.Room;

import java.time.LocalDate;
import java.util.List;

@RestController
public class RestRoomController {

    private ReservationService reservationService;

    @Autowired
    public RestRoomController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/api/getFreeRooms")
    public List<Room> listOfAvailAbleRooms(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            int size
    ) {
        return this.reservationService.getAvailableRooms(fromDate, toDate, size);
    }
}
