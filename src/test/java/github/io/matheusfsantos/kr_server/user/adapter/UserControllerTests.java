package github.io.matheusfsantos.kr_server.user.adapter;

import github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.UserControllerImpl;
import github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.advice.response.UserExceptionResponse;
import github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.request.NewUserRequest;
import github.io.matheusfsantos.kr_server.user.application.ports.in.CreateUserInputPort;
import github.io.matheusfsantos.kr_server.user.application.ports.in.ValidateUserCredentialsInputPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest(UserControllerImpl.class)
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CreateUserInputPort createUserInputPort;
    @MockitoBean
    private ValidateUserCredentialsInputPort validateUserCredentialsInputPort;

    @Test
    void shouldBeReturnAUserCredentials() throws Exception {
        NewUserRequest user = new NewUserRequest("Matheus", "Santos", "matheus.fs.contato@gmail.com", "123456");
        Mockito.doNothing().when(this.createUserInputPort).create(Mockito.any());

        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user))
        ).andExpect(MockMvcResultMatchers.status().isCreated());

        Assertions.assertEquals(HttpStatus.CREATED.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    void shouldBeReturn422BecausePasswordIsBlank() throws Exception {
        NewUserRequest user = new NewUserRequest("Matheus", "Santos", "matheus.fs.contato@gmai.com", null);

        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user))
        );

        UserExceptionResponse response = this.objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), UserExceptionResponse.class);
        Assertions.assertEquals("Password field cannot be blank", response.message());
    }

    @Test
    void shouldBeReturn422BecausePasswordHaveAInvalidLength() throws Exception {
        NewUserRequest user = new NewUserRequest("Matheus", "Santos", "matheus.fs.contato@gmai.com", "1");

        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders.post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.objectMapper.writeValueAsString(user))
        );

        UserExceptionResponse response = this.objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), UserExceptionResponse.class);
        Assertions.assertEquals("Password field must be between 4 and 20 characters", response.message());
    }

    @Test
    void shouldBeReturn422BecauseEmailHaveInvalidFormat() throws Exception {
        NewUserRequest newUser = new NewUserRequest("Matheus", "Santos", "matheus@.com", "123456");

        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(newUser))
        );
        UserExceptionResponse response = this.objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), UserExceptionResponse.class);


        Assertions.assertEquals("Email must be valid", response.message());
    }

    @Test
    void shouldBeReturn422BecauseEmailIsBlank() throws Exception {
        NewUserRequest newUser = new NewUserRequest("Matheus", "Santos", null, "123456");

        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(newUser))
        );
        UserExceptionResponse response = this.objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), UserExceptionResponse.class);

        Assertions.assertEquals("Email field cannot be blank", response.message());
    }

    @Test
    void shouldBeReturn422BecauseEmailHaveAInvalidLength() throws Exception {
        NewUserRequest newUser = new NewUserRequest("Matheus", "Santos", "m@g.com", "123456");

        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(newUser))
        );
        UserExceptionResponse response = this.objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), UserExceptionResponse.class);

        Assertions.assertEquals("Email field must be between 8 and 150 characters", response.message());
    }

    @Test
    void shouldBeReturn422BecauseFirstNameIsBlank() throws Exception {
        NewUserRequest newUser = new NewUserRequest(null, "Santos", "matheus.fs.contato@gmail.com", "123456");

        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(newUser))
        );
        UserExceptionResponse response = this.objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), UserExceptionResponse.class);

        Assertions.assertEquals("First name field cannot be blank", response.message());
    }

    @Test
    void shouldBeReturn422BecauseFirstNameHaveAInvalidLength() throws Exception {
        NewUserRequest newUser = new NewUserRequest("Mat", "Santos", "matheus.fs.contato@gmail.com", "123456");

        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(newUser))
        );
        UserExceptionResponse response = this.objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), UserExceptionResponse.class);

        Assertions.assertEquals("First name field must be between 4 and 100 characters", response.message());
    }

    @Test
    void shouldBeReturn422BecauseLastNameIsBlank() throws Exception {
        NewUserRequest newUser = new NewUserRequest("Matheus", null, "matheus.fs.contato@gmail.com", "123456");

        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(newUser))
        );
        UserExceptionResponse response = this.objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), UserExceptionResponse.class);

        Assertions.assertEquals("Last name field cannot be blank", response.message());
    }

    @Test
    void shouldBeReturn422BecauseLastNameHaveAInvalidLength() throws Exception {
        NewUserRequest newUser = new NewUserRequest("Matheus", "San", "matheus.fs.contato@gmail.com", "123456");

        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(newUser))
        );
        UserExceptionResponse response = this.objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), UserExceptionResponse.class);

        Assertions.assertEquals("Last name field must be between 4 and 100 characters", response.message());
    }
}
