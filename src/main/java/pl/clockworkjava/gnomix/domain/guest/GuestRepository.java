package pl.clockworkjava.gnomix.domain.guest;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest,Long> {

    Optional<Guest> findTop1ByCustomerIdAndFirstNameAndLastNameAndBirthDate(
            String customerId,
            String firstName,
            String lastName,
            LocalDate birthDate
    );

}
