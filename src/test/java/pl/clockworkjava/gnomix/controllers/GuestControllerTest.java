package pl.clockworkjava.gnomix.controllers;



import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import pl.clockworkjava.gnomix.controllers.HomeController;

@WebMvcTest(GuestController.class)
public class GuestControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void basic() throws Exception {
        mockMvc.perform(get("/guests"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("guest"))
                .andExpect(view().name("guests"))
                .andExpect(content().string(containsString("1986-11-13")));

    }
}
