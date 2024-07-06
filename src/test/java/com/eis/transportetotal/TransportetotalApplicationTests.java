package com.eis.transportetotal;

import com.eis.transportetotal.dtos.*;
import com.eis.transportetotal.entity.Ruta;
import com.eis.transportetotal.entity.Vehiculo;
import com.eis.transportetotal.entity.Viaje;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.json.JacksonTester;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransportetotalApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<RegisterUserDto> jsonUser;
    private JacksonTester<LoginUserDto> jsonLoginUser;
    private JacksonTester<LoginResponse> jsonLoginResponse;
    private RegisterUserDto registerUserDto;
    private LoginUserDto loginUserDto;
    static private LoginResponse loginResponse;

    Random rand = new Random();

    private JacksonTester<RegisterVehiculo> jsonRegVehiculo;
    JacksonTester<RegisterRuta> jsonRegRuta;
    JacksonTester<Ruta> jsonRuta;
    static private Ruta ruta;

    static private Vehiculo vehiculo;
    private JacksonTester<Vehiculo> jsonVehiculo;

    private JacksonTester<RegisterViaje> jsonRegViaje;
    private JacksonTester<Viaje> jsonViaje;
    static private Viaje viaje;

    private JacksonTester<RegisterGasto> jsonRegGasto;



    @BeforeEach
    public void setup() {

        JacksonTester.initFields(this, new ObjectMapper());
        registerUserDto = new RegisterUserDto();
        registerUserDto.setEmail("test_user@d.com");
        registerUserDto.setName("John doe");
        registerUserDto.setPassword("secret");
    }
    @Test
    @Order(1)
    void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    @Order(1)
    void shouldResgisterUser() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUser.write(registerUserDto).getJson())
                ).andExpect(status().isOk()).andReturn();
        mvcResult.getResponse();

    }

    @Test
    @Order(2)
    void shouldLogin() throws Exception {
        loginUserDto = new LoginUserDto();
        loginUserDto.setEmail(registerUserDto.getEmail()).setPassword(registerUserDto.getPassword());
        MvcResult mvcResult = this.mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonLoginUser.write(loginUserDto).getJson())
                ).andExpect(status().isOk()).andReturn();
        loginResponse = jsonLoginResponse.parseObject(mvcResult.getResponse().getContentAsString());


    }

    @Test
    @Order(3)
    void shouldCreateVehicle() throws Exception {


        RegisterVehiculo registrVehiculo = new RegisterVehiculo("aaa"+rand.nextInt(1000), "2024", "sin marca", "John");
        MvcResult mvcResult = this.mockMvc.perform(post("/vehiculos")
                        .header("Authorization", STR."Bearer \{loginResponse.getToken()}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRegVehiculo.write(registrVehiculo).getJson())
        ).andExpect(status().isOk()).andReturn();
        vehiculo = jsonVehiculo.parseObject(mvcResult.getResponse().getContentAsString());

    }
    @Test
    @Order(3)
    void shouldCrateRoute() throws Exception {

        RegisterRuta registerRuta = new RegisterRuta("Villavicencio", "Bógota", 90.0);

        MvcResult mvcResult = this.mockMvc.perform(post("/rutas")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", STR."Bearer \{loginResponse.getToken()}")
                .content(jsonRegRuta.write(registerRuta).getJson())
        ).andExpect(status().isOk()).andReturn();
        ruta = jsonRuta.parseObject(mvcResult.getResponse().getContentAsString());

    }

    @Test
    @Order(4)
    void shouldCrateViaje() throws Exception {

        RegisterViaje registerViaje = new RegisterViaje(
                90.0,
                LocalDate.of(2024,7,1),
                ruta.getId(),
                vehiculo.getId()
                );

        MvcResult mvcResult = this.mockMvc.perform(post("/viajes")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", STR."Bearer \{loginResponse.getToken()}")
                .content(jsonRegViaje.write(registerViaje).getJson())
        ).andExpect(status().isOk()).andReturn();
        viaje = jsonViaje.parseObject(mvcResult.getResponse().getContentAsString());


    }

    @Test
    @Order(5)
    void shouldCrateGasto() throws Exception {

        RegisterGasto registerGasto = new RegisterGasto(
                90.0,
                "Gasto de prueba",
                viaje.getId()

        );

        MvcResult mvcResult = this.mockMvc.perform(post("/viajes/add_gasto")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", STR."Bearer \{loginResponse.getToken()}")
                .content(jsonRegGasto.write(registerGasto).getJson())
        ).andExpect(status().isOk()).andReturn();

    }

}
