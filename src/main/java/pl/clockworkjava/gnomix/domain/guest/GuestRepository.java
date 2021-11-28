package pl.clockworkjava.gnomix.domain.guest;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class GuestRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void createNewGuest
            (String firstName, String lastName,
             LocalDate parsedDate, Gender gender) {

        Guest newGuest = new Guest(firstName, lastName, parsedDate, Gender.MALE);
        entityManager.persist(newGuest);
    }

    @Transactional
    public void removeById(long id) {
        this.entityManager.remove(getGuestById(id));

    }


    public Guest getGuestById(long id) {
        return this.entityManager.find(Guest.class, id);

    }

    public List<Guest> findAllGuests() {

        //w SQL jest zapytanie takie:
        // "SELECT * FROM GUESTS"
        // i wtedy dostaje sie liste obiektow

        //w javie tak: tworzymy zapytanie query

        return this.entityManager
                .createQuery("SELECT guest FROM Guest guest", Guest.class)
                .getResultList();
    }

    @Transactional
    public Guest update(Guest guest) {
        return this.entityManager.merge(guest);
    }
}
