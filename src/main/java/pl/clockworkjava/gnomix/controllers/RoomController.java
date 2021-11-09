package pl.clockworkjava.gnomix.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.clockworkjava.gnomix.controllers.dto.GuestCreationDTO;
import pl.clockworkjava.gnomix.domain.guest.GuestService;
import pl.clockworkjava.gnomix.domain.room.Room;
import pl.clockworkjava.gnomix.domain.room.RoomService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RoomController {

    private RoomService roomService;

    @Autowired
      public       RoomController(RoomService service){
        this.roomService=service;
    }
    List<Room> rooms = new ArrayList<>();


    @GetMapping("/rooms")
    public String getAllRooms(Model model) {
//    rooms.add(room);
        model.addAttribute("rooms", this.roomService.findAllRooms());
        return "rooms";
    }

    @GetMapping("/createNewRoom")
    public String createNewRoomForm(){
        return "createNewRoom";
    }

    @PostMapping("/createNewRoom")
    public String handleCreateNewRoom(String roomNumber, String bedsDescription ){
        this.roomService.createNewRoom(roomNumber, bedsDescription);

        return "redirect:rooms";
    }
}
