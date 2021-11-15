package pl.clockworkjava.gnomix.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.clockworkjava.gnomix.controllers.dto.GuestCreationDTO;
import pl.clockworkjava.gnomix.controllers.dto.GuestUpdateDTO;
import pl.clockworkjava.gnomix.domain.guest.GuestService;
import pl.clockworkjava.gnomix.domain.room.Room;
import pl.clockworkjava.gnomix.domain.room.RoomService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService service) {
        this.roomService = service;
    }

    List<Room> rooms = new ArrayList<>();


    @GetMapping()
    public String getAllRooms(Model model) {
//    rooms.add(room);
        model.addAttribute("rooms", this.roomService.findAllRooms());
        return "rooms";
    }

    @GetMapping("/create")
    public String createNewRoomForm() {
        return "create";
    }

    @PostMapping("/create")
    public String handleCreateNewRoom(String roomNumber, String bedsDescription) {
        this.roomService.createNewRoom(roomNumber, bedsDescription);

        return "redirect:rooms";
    }

    @GetMapping("/delete/{id}")
    public String removeRoomById(@PathVariable("id") long id) {
        this.roomService.removeRoomById(id);

        return "redirect:/rooms";
    }

    @GetMapping("/edit/{id}")
    public String editRoomById(@PathVariable("id") long id, Model model) {

        Room room = this.roomService.getRoomById(id);
        model.addAttribute("room", room);


        return "redirect:/rooms";
    }



}
