package pl.clockworkjava.gnomix.domain.room;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.clockworkjava.gnomix.domain.guest.Guest;
import pl.clockworkjava.gnomix.domain.guest.GuestRepository;

import java.util.List;

@Service
public class RoomService {


    private RoomRepository repository;

    @Autowired
    public RoomService(RoomRepository repository){
        this.repository=repository;
    }

    public List<Room> findAllRooms(){
        return this.repository.findAllRooms();
    }

}
