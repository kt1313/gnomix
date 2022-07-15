package pl.clockworkjava.gnomix.domain.room;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void getByRoomNumber() {

        Room r1 = new Room("1A", Arrays.asList(BedType.SINGLE));
        this.roomRepository.save(r1);

        Optional<Room> result = this.roomRepository.findFirstByNumberCaseInsensitive("1a");
        assertTrue(result.isPresent());

    }

}
