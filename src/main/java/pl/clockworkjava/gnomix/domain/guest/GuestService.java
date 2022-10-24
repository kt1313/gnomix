package pl.clockworkjava.gnomix.domain.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.clockworkjava.gnomix.domain.guest.dto.GuestCreationDTO;
import pl.clockworkjava.gnomix.domain.guest.dto.GuestUpdateDTO;

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

    public List<Guest> findAll() {
        return this.repository.findAll();
    }

    public void createNewGuest(GuestCreationDTO dto) {

        Guest newOne = new Guest(dto.getFirstName(), dto.getLastName(), dto.getDateOfBirth(), dto.getGender(), dto.isVip());
        this.repository.save(newOne);
    }

    public void removeById(long id) {
        this.repository.deleteById(id);
    }

    public Guest getById(long id) {
        return this.repository.getById(id);
    }

    public void update(GuestUpdateDTO updatedGuest) {
        Guest byId = this.repository.getById(updatedGuest.getId());
        byId.update(
                updatedGuest.getFirstName(),
                updatedGuest.getLastName(),
                updatedGuest.getDateOfBirth(),
                updatedGuest.getGender(),
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
        return this.repository.findTop1ByCustomerIdAndFirstNameAndLastNameAndBirthDate(customerId, firstName, lastName, dateOfBirth).get();
    }
}
