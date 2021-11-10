package pl.clockworkjava.gnomix.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.clockworkjava.gnomix.controllers.dto.GuestCreationDTO;
import pl.clockworkjava.gnomix.controllers.dto.GuestUpdateDTO;
import pl.clockworkjava.gnomix.domain.guest.Gender;
import pl.clockworkjava.gnomix.domain.guest.Guest;
import pl.clockworkjava.gnomix.domain.guest.GuestService;

import java.time.LocalDate;

@Controller
@RequestMapping("/guests")
public class GuestController {


    private GuestService guestService;

    @Autowired
    public GuestController(GuestService service) {
        this.guestService = service;
    }

    //localhost8080://guests
    @GetMapping()
    public String guests(Model model) {
        model.addAttribute("guests", this.guestService.findAllGuests());
        return "guests";
    }

    @GetMapping("/create")
    public String createNewGuest(){
        return "createNewGuest";
    }

    @PostMapping()
    public String handleCreateNewGuest
            (GuestCreationDTO guestDTO){
        System.out.println(guestDTO);
        this.guestService.createNewGuest(guestDTO);

       return "redirect:guests";
    }

    @GetMapping("/delete/{id}")
    public String removeGuest(@PathVariable("id") long id){
        this.guestService.removeById(id);

        return "redirect:/guests";
    }

    @GetMapping("/edit/{id}")
    public String editGuest(@PathVariable("id") long id, Model model){

        Guest guest=this.guestService.getGuestById(id);
        model.addAttribute("guest", guest);

        return "editGuest";
    }

    @PostMapping("/edit/{id}")
    public String editGuest(GuestUpdateDTO updatedGuest){

        this.guestService.update(updatedGuest);

        return "redirect:/guests";
    }

}
