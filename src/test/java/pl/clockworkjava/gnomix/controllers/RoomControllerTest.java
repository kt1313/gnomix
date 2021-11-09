package pl.clockworkjava.gnomix.controllers;

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
import pl.clockworkjava.gnomix.domain.room.BedType;
import pl.clockworkjava.gnomix.domain.room.Room;
import pl.clockworkjava.gnomix.domain.room.RoomService;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(RoomController.class)
public class RoomControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Test
    public void basic() throws Exception {

        Room room1 = new Room("10A", Arrays.asList( BedType.DOUBLE ));

       Mockito.when(roomService.findAllRooms()).thenReturn(Arrays.asList(room1));

        mockMvc.perform(get("/rooms"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("rooms"))
                .andExpect(view().name("rooms"))
                .andExpect(content().string(containsString("10A")));

    }

    @Test
    public void handlePostTest() throws Exception {

        String postContent = "roomNumber=107C&bedsDescription=2%2B1";


        MockHttpServletRequestBuilder request = post("/createNewRoom")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .content(postContent);


        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("rooms"));



        Mockito
                .verify(roomService,Mockito.times(1))
                .createNewRoom("107C", "2+1");
    }

}
