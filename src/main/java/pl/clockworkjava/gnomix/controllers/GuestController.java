package pl.clockworkjava.gnomix.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.clockworkjava.gnomix.domain.guest.Gender;
import pl.clockworkjava.gnomix.domain.guest.Guest;

import java.time.LocalDate;

@Controller
public class GuestController {

    //localhost8080://guests
    @GetMapping("/guests")
    public String guests(Model model) {
        Guest guest=new Guest("Pawel", "Cwik", LocalDate.of(1986,11,13), Gender.MALE);
model.addAttribute("guest", guest);
        return "guests";
    }

}
