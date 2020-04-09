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

import cst438.fr.fe.controller.CityRestController;
import cst438.fr.fe.domain.CityInfo;
import cst438.fr.fe.service.CityService;

@WebMvcTest(CityRestController.class)
public class CityRestControllerTest {

	@MockBean
	private CityService cityService;

	@Autowired
	private MockMvc mvc;

	// This object will be magically initialized by the initFields method below.
	private JacksonTester<CityInfo> cityInfoJson;

	@BeforeEach
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void getCityInfo() throws Exception {
        String countryCode = "US";
        String countryName = "USA";
        String cityName = "Los Angeles";
        int population = 10000;
        String district = "Los Angeles District";
	        
	    CityInfo cityInfo = new CityInfo(0, cityName, countryCode, countryName, district, population, 0, "");

	    given(cityService.getCityInfo(cityName)).willReturn(cityInfo);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/cities/" + cityName).contentType(MediaType.APPLICATION_JSON)
                        .content(cityInfoJson.write(cityInfo).getJson()))
                .andReturn().getResponse();
        
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                cityInfoJson.write(cityInfo).getJson());

	}

}
