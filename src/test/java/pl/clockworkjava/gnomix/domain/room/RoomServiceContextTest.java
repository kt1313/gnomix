package pl.clockworkjava.gnomix.domain.room;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class RoomServiceContextTest {

    @Autowired
    private RoomRepository repository;
    @Test
    @Sql("/creat_rooms.sql")
    public void testGetSize(){
        RoomService roomService=new RoomService(repository,null);

        //when
        List<Room> result=roomService.getRoomsForSize(2);
        for(Room r: result){
            System.out.printf(": "+r.getId());
        }
        //then
        assertEquals(2, result.size());
    }

    @Test
//    @Sql("/creat_rooms.sql")
    public void testGetSize2(){
        RoomService roomService=new RoomService(repository,null);

        //when
        List<Room> result=roomService.getRoomsForSize(1);
        for(Room r: result){
            System.out.printf(": "+r.getId());
        }
        //then
        assertEquals(3, result.size());
    }
}
