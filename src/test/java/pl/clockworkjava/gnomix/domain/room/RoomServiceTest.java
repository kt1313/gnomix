package pl.clockworkjava.gnomix.domain.room;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import pl.clockworkjava.gnomix.domain.reservation.ReservationService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomServiceTest {

    @Test
    public void createNewRoomValidData() {

        //give
        RoomRepository roomRepository = Mockito.mock(RoomRepository.class);
        ReservationService reservationService = Mockito.mock(ReservationService.class);
        ArgumentCaptor<Room> roomCaptor = ArgumentCaptor.forClass(Room.class);
        RoomService rs = new RoomService(roomRepository, reservationService);
        List<BedType> bedTypes = Arrays.asList(BedType.DOUBLE, BedType.SINGLE, BedType.SINGLE);
        Room r = new Room("102", bedTypes);

        //when
        rs.createNewRoom("102", "2+1+1", "", null);



        //then
        Mockito.verify(roomRepository).save(roomCaptor.capture());
        assertEquals("102", roomCaptor.getValue().getNumber());
        assertEquals(3, roomCaptor.getValue().getBeds().size());
        assertEquals(BedType.DOUBLE, roomCaptor.getValue().getBeds().get(0));
        assertEquals(BedType.SINGLE, roomCaptor.getValue().getBeds().get(1));
        assertEquals(BedType.SINGLE, roomCaptor.getValue().getBeds().get(2));
    }


    @Test
    public void testGetNoRoomsWrongSize() {
        //given
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("101", Arrays.asList(BedType.DOUBLE)));
        rooms.add(new Room("102", Arrays.asList(BedType.SINGLE)));
        rooms.add(new Room("103", Arrays.asList(BedType.DOUBLE, BedType.SINGLE)));
        RoomRepository roomRepository = Mockito.mock(RoomRepository.class);
        ReservationService reservationService = Mockito.mock(ReservationService.class);
        Mockito.when(roomRepository.findAll()).thenReturn(rooms);
        RoomService roomService = new RoomService(roomRepository, reservationService);

        //when
        List<Room> result = roomService.getRoomsForSize(-1);

        //then
        assertEquals(0, result.size());

    }
}
