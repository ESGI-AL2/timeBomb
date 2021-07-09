package fr.esgi.timebomb.Player;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esgi.timebomb.authentification.app.dto.LoginDTO;
import fr.esgi.timebomb.authentification.app.dto.RegisterDTO;
import fr.esgi.timebomb.authentification.domain.Role;
import fr.esgi.timebomb.service.PlayerService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlayerService playerService;

    @Test
    @Order(1)
    public void should_Not_Allow_Access_To_Unauthenticated_Users() throws Exception {
        mockMvc.perform(get("/cards"))
                .andExpect(status().isForbidden());
    }

    @Test
    @Order(2)
    public void Should_Send_Method_Not_Allowed() throws Exception {
        mockMvc.perform(get("/auth/login"))
                .andExpect(status().isMethodNotAllowed());
    }

/*    @Test
    @Order(4)
    public void Should_Authentication() throws Exception {
        LoginDTO loginDTO = new LoginDTO().setUsername("USER_TEST").setPassword("TEST");
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void Should_Create_New_Account() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO().setUsername("USER_TEST").setPassword("TEST").setRole(Role.USER);
        mockMvc.perform(post("/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(registerDTO)))
                .andExpect(status().isCreated());
    }*/
}
