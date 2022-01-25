package pl.clockworkjava.gnomix.domain.reservation;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.clockworkjava.gnomix.domain.room.RoomService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    public void testPredicateResevationStartsAtTheSameDatePositive() {
    //given
        List<Reservation> reservations = new ArrayList<>();

        LocalDate fromOne = LocalDate.parse("2022-01-08");
        LocalDate toOne = LocalDate.parse("2022-01-10");
        reservations.add(new Reservation(fromOne, toOne, null, null));

        LocalDate fromTwo = LocalDate.parse("2022-02-05");
        LocalDate totwo = LocalDate.parse("2022-02-15");
        reservations.add(new Reservation(fromOne, toOne, null, null));

        LocalDate myStartDate = LocalDate.parse("2022-01-09");
    //when
        List<Reservation> collect = reservations
                .stream().filter(ReservationService.reservationStartsAtTheSameDate(myStartDate))
                .collect(Collectors.toList());
        //then

        assertEquals(1,collect.size());


    }

    @Test
    public void testPredicateResevationStartsAtTheSameDateNegative() {

        //given
        List<Reservation> reservations = new ArrayList<>();

        LocalDate fromOne = LocalDate.parse("2022-01-08");
        LocalDate toOne = LocalDate.parse("2022-01-10");
        reservations.add(new Reservation(fromOne, toOne, null, null));

        LocalDate fromTwo = LocalDate.parse("2022-02-05");
        LocalDate totwo = LocalDate.parse("2022-02-15");
        reservations.add(new Reservation(fromOne, toOne, null, null));

        LocalDate myStartDate = LocalDate.parse("2022-02-25");
        //when
        List<Reservation> collect = reservations
                .stream().filter(ReservationService.reservationStartsAtTheSameDate(myStartDate))
                .collect(Collectors.toList());
        //then
        assertEquals(0,collect.size());

    }

    @Test
    public void testPredicateResevationEndsAtTheSameDatePositive() {
        //given
        List<Reservation> reservations = new ArrayList<>();

        LocalDate fromOne = LocalDate.parse("2022-01-08");
        LocalDate toOne = LocalDate.parse("2022-01-10");
        reservations.add(new Reservation(fromOne, toOne, null, null));

        LocalDate fromTwo = LocalDate.parse("2022-02-05");
        LocalDate totwo = LocalDate.parse("2022-02-15");
        reservations.add(new Reservation(fromOne, toOne, null, null));

        LocalDate myStartDate = LocalDate.parse("2022-02-15");
        //when
        List<Reservation> collect = reservations
                .stream().filter(ReservationService.reservationEndsAtTheSameDate(myStartDate))
                .collect(Collectors.toList());
        //then

        assertEquals(1,collect.size());


    }

    @Test
    public void testPredicateResevationEndsAtTheSameDateNegative() {

        //given
        List<Reservation> reservations = new ArrayList<>();

        LocalDate fromOne = LocalDate.parse("2022-01-08");
        LocalDate toOne = LocalDate.parse("2022-01-10");
        reservations.add(new Reservation(fromOne, toOne, null, null));

        LocalDate fromTwo = LocalDate.parse("2022-02-05");
        LocalDate totwo = LocalDate.parse("2022-02-15");
        reservations.add(new Reservation(fromOne, toOne, null, null));

        LocalDate myStartDate = LocalDate.parse("2022-02-25");
        //when
        List<Reservation> collect = reservations
                .stream().filter(ReservationService.reservationStartsAtTheSameDate(myStartDate))
                .collect(Collectors.toList());
        //then
        assertEquals(0,collect.size());

    }

    @Test
    public void testPredicateResevationStartsBetweenPositive() {
        //given
        List<Reservation> reservations = new ArrayList<>();

        LocalDate fromOne = LocalDate.parse("2022-01-08");
        LocalDate toOne = LocalDate.parse("2022-01-10");
        reservations.add(new Reservation(fromOne, toOne, null, null));

        LocalDate fromTwo = LocalDate.parse("2022-02-05");
        LocalDate totwo = LocalDate.parse("2022-02-15");
        reservations.add(new Reservation(fromOne, toOne, null, null));

        LocalDate myStartDate = LocalDate.parse("2022-01-05");
        LocalDate myEndDate = LocalDate.parse("2022-01-12");

        //when
        List<Reservation> collect = reservations
                .stream().filter(ReservationService.reservationStartsBetween(myStartDate, myEndDate))
                .collect(Collectors.toList());
        assertEquals(1,collect.size());


    }

    @Test
    public void testPredicateResevationStartsBetweenNegative() {

        //given
        List<Reservation> reservations = new ArrayList<>();

        LocalDate fromOne = LocalDate.parse("2022-01-08");
        LocalDate toOne = LocalDate.parse("2022-01-10");
        reservations.add(new Reservation(fromOne, toOne, null, null));

        LocalDate fromTwo = LocalDate.parse("2022-02-05");
        LocalDate totwo = LocalDate.parse("2022-02-15");
        reservations.add(new Reservation(fromOne, toOne, null, null));

        LocalDate myStartDate = LocalDate.parse("2022-01-25");
        LocalDate myEndDate = LocalDate.parse("2022-01-29");

        //when
        List<Reservation> collect = reservations
                .stream().filter(ReservationService.reservationStartsBetween(myStartDate, myEndDate))
                .collect(Collectors.toList());
        //then
        assertEquals(0,collect.size());

    }
    @Test
    public void testPredicateResevationEndsBetweenPositive() {
        //given
        List<Reservation> reservations = new ArrayList<>();

        LocalDate fromOne = LocalDate.parse("2022-01-08");
        LocalDate toOne = LocalDate.parse("2022-01-10");
        reservations.add(new Reservation(fromOne, toOne, null, null));

        LocalDate fromTwo = LocalDate.parse("2022-02-05");
        LocalDate totwo = LocalDate.parse("2022-02-15");
        reservations.add(new Reservation(fromOne, toOne, null, null));

        LocalDate myStartDate = LocalDate.parse("2022-01-05");
        LocalDate myEndDate = LocalDate.parse("2022-01-12");

        //when
        List<Reservation> collect = reservations
                .stream().filter(ReservationService.reservationEndsBetween(myStartDate, myEndDate))
                .collect(Collectors.toList());
        assertEquals(1,collect.size());


    }

    @Test
    public void testPredicateResevationEndsBetweenNegative() {

        //given
        List<Reservation> reservations = new ArrayList<>();

        LocalDate fromOne = LocalDate.parse("2022-01-08");
        LocalDate toOne = LocalDate.parse("2022-01-10");
        reservations.add(new Reservation(fromOne, toOne, null, null));

        LocalDate fromTwo = LocalDate.parse("2022-02-05");
        LocalDate totwo = LocalDate.parse("2022-02-15");
        reservations.add(new Reservation(fromOne, toOne, null, null));

        LocalDate myStartDate = LocalDate.parse("2022-01-25");
        LocalDate myEndDate = LocalDate.parse("2022-01-29");

        //when
        List<Reservation> collect = reservations
                .stream().filter(ReservationService.reservationEndsBetween(myStartDate, myEndDate))
                .collect(Collectors.toList());
        //then
        assertEquals(0,collect.size());

    }

}