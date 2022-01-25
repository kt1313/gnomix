package pl.clockworkjava.gnomix.domain.reservation;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.clockworkjava.gnomix.domain.room.RoomService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservationServiceTest {

    @Test
    public void testIfGetAvRoomsFailsForTooSmallSize() {
//given
        ReservationRepository repo = Mockito.mock(ReservationRepository.class);
        RoomService rs = Mockito.mock(RoomService.class);
        ReservationService reservationService = new ReservationService(repo, rs);


        //when then
        assertThrows(IllegalArgumentException.class,
                () -> {
                    reservationService.getAvailableRooms(LocalDate.now(),
                            LocalDate.now().plus(1, ChronoUnit.DAYS), -5);

                }

        );
    }
    @Test
    public void testIfGetAvRoomsFailsForTooBigSize() {
//given
        ReservationRepository repo = Mockito.mock(ReservationRepository.class);
        RoomService rs = Mockito.mock(RoomService.class);
        ReservationService reservationService = new ReservationService(repo, rs);


        //when then
        assertThrows(IllegalArgumentException.class,
                () -> {
                    reservationService.getAvailableRooms(LocalDate.now(),
                            LocalDate.now().plus(1, ChronoUnit.DAYS), 15);

                }

        );
    }

    @Test
    public void testIfGetAvRoomsFailsForSameDates() {
//given
        ReservationRepository repo = Mockito.mock(ReservationRepository.class);
        RoomService rs = Mockito.mock(RoomService.class);
        ReservationService reservationService = new ReservationService(repo, rs);


        //when then
        assertThrows(IllegalArgumentException.class,
                () -> {
                    reservationService.getAvailableRooms(LocalDate.now(),
                            LocalDate.now(), 4);

                }

        );
    }
    @Test
    public void testIfGetAvRoomsFailsForToBeforeFromDate() {
//given
        ReservationRepository repo = Mockito.mock(ReservationRepository.class);
        RoomService rs = Mockito.mock(RoomService.class);
        ReservationService reservationService = new ReservationService(repo, rs);


        //when then
        assertThrows(IllegalArgumentException.class,
                () -> {
                    reservationService.getAvailableRooms(LocalDate.now().plus(1, ChronoUnit.DAYS), LocalDate.now()
                            , 15);

                }

        );
    }
}