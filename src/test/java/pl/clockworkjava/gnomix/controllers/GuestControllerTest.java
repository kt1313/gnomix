package pl.clockworkjava.gnomix.controllers;


import static org.hamcrest.Matchers.containsString;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mockingDetails;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static pl.clockworkjava.gnomix.domain.guest.Gender.MALE;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.clockworkjava.gnomix.controllers.dto.GuestCreationDTO;
import pl.clockworkjava.gnomix.controllers.dto.GuestUpdateDTO;
import pl.clockworkjava.gnomix.domain.guest.Gender;
import pl.clockworkjava.gnomix.domain.guest.Guest;
import pl.clockworkjava.gnomix.domain.guest.GuestRepository;
import pl.clockworkjava.gnomix.domain.guest.GuestService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebMvcTest(GuestController.class)
public class GuestControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuestService guestService;

    @Test
    public void basic() throws Exception {

        Guest guest = new Guest("Pawel", "Cwik", LocalDate.of(1986, 11, 13), MALE, true);

        Mockito.when(guestService.findAllGuests()).thenReturn(Arrays.asList(guest));

        mockMvc.perform(get("/guests"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("guests"))
                .andExpect(view().name("guests"))
                .andExpect(content().string(containsString("1986-11-13")));

    }

    @Test
    public void handlePostTest() throws Exception {

        String postContent = "firstName=Tom&lastName=Klimkiewicz&dateOfBirth=2021-11-01&gender=MALE";


        MockHttpServletRequestBuilder request = post("/guests")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .content(postContent);


        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/guests"));

        GuestCreationDTO dto = new GuestCreationDTO("Tomek", "Klimkiewicz"
                , LocalDate.parse("2021-11-01"), Gender.MALE, "on");


        Mockito
                .verify(guestService, Mockito.times(1))
                .createNewGuest(dto);
    }

    @Test
    public void handleDeleteTest() throws Exception {

        //jezeli dostal na taki adres: /geusts/delete/21
        MockHttpServletRequestBuilder request = get("/guests/delete/21");

        //taki request
        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/guests"));

        //to powinna sie wykonac taka metoda
        Mockito
                .verify(guestService, Mockito.times(1))
                .removeById(21);
    }


    @Test
    public void handleShowEditFormTest() throws Exception {

        //jezeli dostal na taki adres: /geusts/delete/21
        MockHttpServletRequestBuilder request = get("/guests/edit/21");

        Guest guest = new Guest
                ("Tomasz",
                        "Klimkiewicz",
                        LocalDate.of(1976, 5, 8),
                        MALE, true);

        Mockito.when(guestService.getGuestById(21)).thenReturn(guest);

        //taki request (spodziewamy sie konkretnego modelu i konkr view)
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("guest"))
                .andExpect(view().name("editGuest"));

        //to powinna sie wykonac taka metoda
        Mockito
                .verify(guestService, Mockito.times(1))
                .removeById(21);

    }

    @Test
    public void editGuestTest() throws Exception {

        //given
        GuestRepository guestRepository = Mockito.mock(GuestRepository.class);
        GuestService guestService = new GuestService(guestRepository);

        List<Guest> guests = new ArrayList<>();
        Mockito.when(guestRepository.findAll()).thenReturn(guests);
        Guest guestBefore = new Guest(
                "Tom", "Klmx"
                , LocalDate.parse("1976-05-08")
                , MALE, false);
        guests.add(guestBefore);
        Guest guestBefore2 = new Guest(
                "Han", "Klmx"
                , LocalDate.parse("1976-05-08")
                , MALE, false);
        guests.add(guestBefore2);

        //guestAfter to oczekiwany wynik
        Guest guestAfter = new Guest(
                "Tom", "After"
                , LocalDate.parse("1976-05-08")
                , MALE
                , true);

        //guestUpdateDTO to sztuczne DTO niby z templatki
        GuestUpdateDTO guestToModifyDTO = new GuestUpdateDTO(21
                , "Tom", "After"
                , LocalDate.parse("1976-05-08")
                , MALE
                , true);

        //when
        doCallRealMethod().when(guestService).update(guestToModifyDTO);
        long id = 21;
        Mockito.when(guestRepository.getById((long) 21)).thenReturn(guestAfter);
        guestService.update(guestToModifyDTO);
        Guest guestUpdated = guestService.getGuestById(21);

        //then
        assertEquals(guestAfter, guestUpdated);
    }
}
