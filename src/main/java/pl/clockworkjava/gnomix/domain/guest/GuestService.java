package pl.clockworkjava.gnomix.domain.guest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.clockworkjava.gnomix.controllers.dto.GuestCreationDTO;

import java.time.LocalDate;
import java.util.List;

@Service
public class GuestService {

    private GuestRepository repository;

    @Autowired
    public GuestService(GuestRepository repository) {
        this.repository = repository;
    }

    public List<Guest> findAllGuests() {
        return this.repository.findAllGuests();
    }

    public void createNewGuest(GuestCreationDTO guestDTO) {

        this.repository.createNewGuest(guestDTO.getFirstName(), guestDTO.getLastName(), guestDTO.getDateOfBirth(), guestDTO.getGender());

    }

    public void removeById(long id){
        this.repository.remoceById(id);
    }
}
