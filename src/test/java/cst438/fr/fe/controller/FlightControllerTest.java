package cst438.fr.fe.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cst438.fr.fe.domain.FlightInfo;
import cst438.fr.fe.domain.FlightRepository;
import cst438.fr.fe.service.FlightListService;

@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @MockBean
    private FlightListService flightListService;

    @MockBean
    private FlightRepository flightRepository;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }
    
    // This object will be magically initialized by the initFields method below.
    private JacksonTester<FlightInfo> flightInfoJson;
    
    @Test
    public void listFlightsTest() throws Exception {
        FlightInfo flightInfo = new FlightInfo();
        given(flightListService.getFlightList()).willReturn(flightInfo);
        
        MockHttpServletResponse response = mvc.perform(
                get("/flight/list").contentType(MediaType.APPLICATION_JSON)
                        .content(flightInfoJson.write(flightInfo).getJson()))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
}
