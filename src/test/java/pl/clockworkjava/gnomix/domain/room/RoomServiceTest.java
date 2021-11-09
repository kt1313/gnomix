package pl.clockworkjava.gnomix.domain.room;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomServiceTest {

    @Test
    public void createNewRoomValidData() {

        //give
        RoomRepository roomRepository = Mockito.mock(RoomRepository.class);
        ArgumentCaptor<String> numberCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<List<BedType>> bedsListCaptor = ArgumentCaptor.forClass(List.class);
        RoomService rs = new RoomService(roomRepository);

        //when
        rs.createNewRoom("102A", "2+1+1");

        //then
        Mockito.verify(roomRepository)
                .createNewRoom(numberCaptor.capture(), bedsListCaptor.capture());
        assertEquals("102A", numberCaptor.getValue());
        assertEquals(3, bedsListCaptor.getValue().size());
        assertEquals(BedType.DOUBLE, bedsListCaptor.getValue().get(0));
        assertEquals(BedType.SINGLE, bedsListCaptor.getValue().get(1));
        assertEquals(BedType.SINGLE, bedsListCaptor.getValue().get(2));


    }


}
