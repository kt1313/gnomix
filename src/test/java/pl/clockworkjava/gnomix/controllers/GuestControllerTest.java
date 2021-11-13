package pl.clockworkjava.gnomix.controllers;



import static org.hamcrest.Matchers.containsString;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static pl.clockworkjava.gnomix.domain.guest.Gender.MALE;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.clockworkjava.gnomix.controllers.dto.GuestCreationDTO;
import pl.clockworkjava.gnomix.domain.guest.Gender;
import pl.clockworkjava.gnomix.domain.guest.Guest;
import pl.clockworkjava.gnomix.domain.guest.GuestService;

import java.time.LocalDate;
import java.util.Arrays;

@WebMvcTest(GuestController.class)
public class GuestControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuestService guestService;

    @Test
    public void basic() throws Exception {

        Guest guest = new Guest("Pawel", "Cwik", LocalDate.of(1986, 11, 13), MALE);

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

        GuestCreationDTO dto=new GuestCreationDTO("Tom","Klimkiewicz", LocalDate.parse("2021-11-01"), Gender.MALE);


        Mockito
                .verify(guestService,Mockito.times(1))
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
                .verify(guestService,Mockito.times(1))
                .removeById(21);
    }


    @Test
    public void handleShowEditFormTest() throws Exception {

        //jezeli dostal na taki adres: /geusts/delete/21
        MockHttpServletRequestBuilder request = get("/guests/edit/21");

        Guest guest=new Guest
                ("Tomasz",
                "Klimkiewicz",
                        LocalDate.of(1976,5,8),
                        MALE);

        Mockito.when(guestService.getGuestById(21)).thenReturn(guest);

        //taki request (spodziewamy sie konkretnego modelu i konkr view)
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("guest"))
                .andExpect(view().name("editGuest"));

        //to powinna sie wykonac taka metoda
        Mockito
                .verify(guestService,Mockito.times(1))
                .removeById(21);

    }

}
