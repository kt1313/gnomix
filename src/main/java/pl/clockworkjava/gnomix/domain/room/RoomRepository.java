package pl.clockworkjava.gnomix.domain.room;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.clockworkjava.gnomix.domain.guest.Gender;
import pl.clockworkjava.gnomix.domain.guest.Guest;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RoomRepository {


    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Room createNewRoom(String roomNumber, List<BedType> beds) {

        Room room = new Room(roomNumber, beds);
        this.entityManager.persist(room);
        return room;
    }
    @Transactional
    public void removeRoomById(long id) {

        Room roomToBeDeleted = this.getRoomId(id);

        this.entityManager.remove(roomToBeDeleted);
    }


    public Room getRoomId(long id) {

    return this.entityManager.find(Room.class, id);

    }


    public List<Room> findAllRooms() {

        return this.entityManager
                .createQuery("SELECT room FROM Room room", Room.class)
                .getResultList();
    }

    @Transactional
    public Room update(Room room){
        return this.entityManager.merge(room);
    }
}

