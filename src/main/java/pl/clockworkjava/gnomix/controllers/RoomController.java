package pl.clockworkjava.gnomix.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.clockworkjava.gnomix.domain.room.Room;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RoomController {

    List<Room> rooms=new ArrayList<>();


    @GetMapping("/rooms")
public String getAllRooms(Model model){
    Room room = new Room("10A");
//    rooms.add(room);
    model.addAttribute("room", room);
    return "rooms";
}


}
