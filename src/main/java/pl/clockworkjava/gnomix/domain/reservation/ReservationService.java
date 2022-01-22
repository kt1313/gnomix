package pl.clockworkjava.gnomix.domain.reservation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.clockworkjava.gnomix.domain.room.Room;
import pl.clockworkjava.gnomix.domain.room.RoomService;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {


    private ReservationRepository reservationRepository;
    private RoomService roomService;

    @Autowired
    public ReservationService(
            ReservationRepository reservationRepository,
            RoomService roomService) {
        this.reservationRepository = reservationRepository;
        this.roomService = roomService;
    }


    public List<Reservation> getAll() {

        return this.reservationRepository.findAll();
    }

    public List<Room> getAvailableRooms(LocalDate from, LocalDate to, int size) {
        if (size < 0 || size > 10) {
throw new IllegalArgumentException("Wrong argument - size: (1-10)");        }
        if (from.equals(to) || to.isBefore(from)) {
            throw new IllegalArgumentException("Wrong dates") ;       }

        return this.roomService.findAllRooms();
    }
}
