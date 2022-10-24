package pl.clockworkjava.gnomix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.clockworkjava.gnomix.domain.guest.dto.GuestCreationDTO;
import pl.clockworkjava.gnomix.domain.guest.dto.GuestUpdateDTO;
import pl.clockworkjava.gnomix.domain.guest.Guest;
import pl.clockworkjava.gnomix.domain.guest.GuestService;
import pl.clockworkjava.gnomix.domain.reservation.ReservationService;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/guests")
public class GuestController {

    private GuestService guestService;
    private ReservationService reservationService;

    @Autowired
    public GuestController(GuestService service, ReservationService reservationService) {
        this.guestService = service;
        this.reservationService = reservationService;
    }

    @GetMapping
    public String guests(Model model) {
        model.addAttribute("guests", this.guestService.findAll());
        return "guests";
    }

    @GetMapping("/create")
    public String createNewGuest() {
        return "createNewGuest";
    }

    @PostMapping
    public String handleCreateNewGuest(@Valid GuestCreationDTO dto, BindingResult result, Model model) {

        if(result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "createNewGuest";
        } else {
            this.guestService.createNewGuest(dto);
            return "redirect:/guests";
        }

    }

    @GetMapping("/delete/{id}")
    public String removeGuest(@PathVariable long id) {

        this.guestService.removeById(id);

        return "redirect:/guests";
    }

    @GetMapping("/edit/{id}")
    public String editGuest(@PathVariable long id, Model model) {

        Guest guest = this.guestService.getById(id);
        model.addAttribute("guest", guest);

        return "editGuest";
    }

    @PostMapping("/edit")
    public String editGuest(GuestUpdateDTO updatedGuest) {

        this.guestService.update(updatedGuest);

        return "redirect:/guests";
    }

    @PostMapping("/createAndAttachToReservation")
    public String createAndAttachToReservation(
            String firstName,
            String lastName,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
            long reservationId,
            String customerId
    ) {
        Guest g;
        if(customerId==null || customerId.isEmpty()) {
            g = this.guestService.createNewGuest(firstName, lastName, dateOfBirth);
        } else {
            g = this.guestService.getGuestByCustomerId(firstName, lastName, dateOfBirth, customerId);
        }
        this.reservationService.attachGuestToReservation(g, reservationId);

        return "thankyoupage";
    }

}