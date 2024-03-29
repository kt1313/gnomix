package pl.clockworkjava.gnomix.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.clockworkjava.gnomix.domain.reservation.ReservationCreateTmpRestDTO;
import pl.clockworkjava.gnomix.domain.reservation.ReservationService;

import javax.validation.Valid;

@RestController
public class RestReservationController {

    private final ReservationService reservationService;

    @Autowired
    public RestReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("api/createTemporaryReservation")
    public void createTempReservation(@Valid @RequestBody ReservationCreateTmpRestDTO payload) {

        boolean result =  this.reservationService
                .createTemporaryReservation(payload.getRoomId(), payload.getFromDate(), payload.getToDate(), payload.getEmail());

        if(!result) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to find room with such ID");
        }
    }


}
