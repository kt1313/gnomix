package pl.clockworkjava.gnomix.domain.guest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.clockworkjava.gnomix.controllers.dto.GuestCreationDTO;
import pl.clockworkjava.gnomix.controllers.dto.GuestUpdateDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class GuestService {

    private GuestRepository repository;

    @Autowired
    public GuestService(GuestRepository repository) {
        this.repository = repository;
    }

    public List<Guest> findAllGuests() {
        return this.repository.findAll();
    }

    public void createNewGuest(GuestCreationDTO guestDTO) {

        Guest newOne = new Guest(guestDTO.getFirstName()
                , guestDTO.getLastName()
                , guestDTO.getDateOfBirth(), guestDTO.getGender()
                , guestDTO.isVip());
        this.repository.save(newOne);
    }

    public void removeById(long id) {
        this.repository.deleteById(id);
    }


    public Guest getGuestById(long id) {
        return this.repository.getById(id);
    }

    public void update(GuestUpdateDTO updatedGuest) {
        Guest byId = this.repository.getById(updatedGuest.getId());
        byId.update(
                updatedGuest.getFirstName(),
                updatedGuest.getLastName(),
                updatedGuest.getDateOfBirth(),
                updatedGuest.getGender(),
//                updatedGuest.isVip(),
                updatedGuest.getCustomerId()
        );

        this.repository.save(byId);
    }

    public Guest createNewGuest(String firstName, String lastName, LocalDate dateOfBirth) {
        Guest newOne = new Guest(firstName, lastName, dateOfBirth);
        this.repository.save(newOne);
        return newOne;
    }

    public Guest getGuestByCustomerId(String firstName, String lastName, LocalDate dateOfBirth, String customerId) {
        Optional<Guest> first = this.repository.findAll().stream()
                .filter(guest -> guest.getCustomerId().equals(customerId))
                .filter(guest -> guest.getFirstName().equals(firstName))
                .filter(guest -> guest.getLastName().equals(lastName))
                .filter(guest -> guest.getBirthDate().equals(dateOfBirth))
                .findFirst();

        return first.get();
    }
}
