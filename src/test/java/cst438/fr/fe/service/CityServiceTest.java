package cst438.fr.fe.service;
 
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import cst438.fr.fe.domain.City;
import cst438.fr.fe.domain.CityInfo;
import cst438.fr.fe.domain.CityRepository;
import cst438.fr.fe.domain.Country;
import cst438.fr.fe.domain.CountryRepository;
import cst438.fr.fe.domain.TempAndTime;
import cst438.fr.fe.service.CityService;
import cst438.fr.fe.service.WeatherService;

@SpringBootTest
public class CityServiceTest {

    //This is the class under test
    private CityService cityService;

    @MockBean
	private WeatherService weatherService;
	
	@MockBean
	private CityRepository cityRepository;
	
	@MockBean
	private CountryRepository countryRepository;

	@BeforeEach
	public void setupEach() {
	    MockitoAnnotations.initMocks(this);
	    cityService = new CityService(cityRepository, countryRepository, weatherService);
	}
	
	
	@Test
	public void contextLoads() {
	}


	@Test
	public void testCityFound() throws Exception {
	    String countryCode = "US";
	    String countryName = "USA";
	    String cityName = "Los Angeles";
	    int population = 10000;
	    String district = "Los Angeles District";
	    
	    List<City> cityList = new ArrayList<City>();
	    cityList.add(new City(0, cityName, countryCode, district, population));
	    given(cityRepository.findByName(cityName)).willReturn(cityList);
	    
        assertThat(cityService).isNotNull();

        double temp = -460.0;
        TempAndTime tempAndTime = new TempAndTime(0, 0, 0);
        given(weatherService.getTempAndTime(cityName)).willReturn(tempAndTime);

        Country country = new Country(countryCode, countryName);
        given(countryRepository.findByCode(countryCode)).willReturn(country);

        
        CityInfo actualCityInfo = cityService.getCityInfo(cityName);

        assertThat(actualCityInfo).isNotNull();
        assertThat(actualCityInfo.getName()).isEqualTo(cityName);
        assertThat(actualCityInfo.getCountryCode()).isEqualTo(countryCode);
        assertThat(actualCityInfo.getCountryName()).isEqualTo(countryName);
        assertThat(actualCityInfo.getPopulation()).isEqualTo(population);
        assertThat(actualCityInfo.getDistrict()).isEqualTo(district);
        assertThat(actualCityInfo.getTemp()).isEqualTo(temp);
	}
	
	@Test 
	public void  testCityNotFound() {
	    String cityName = "Los Angeles";
	    CityInfo actualCityInfo = cityService.getCityInfo(cityName);
        assertThat(actualCityInfo).isNull();
	}
	
	@Test 
	public void  testCityMultiple() {
		// test to make sure the first city is returned from the list. 
	    String countryCode = "US";
        String countryName = "USA";
        String cityName = "Los Angeles";
        int population = 10000;
        String district = "Los Angeles District";
        
        List<City> cityList = new ArrayList<City>();
        cityList.add(new City(0, cityName, countryCode, district, population));
        cityList.add(new City(0, cityName, "Ecuador", district, population));
        given(cityRepository.findByName(cityName)).willReturn(cityList);
        
        assertThat(cityService).isNotNull();
        
        double temp = -460.0;
        TempAndTime tempAndTime = new TempAndTime(0, 0, 0);
        given(weatherService.getTempAndTime(cityName)).willReturn(tempAndTime);
        
        Country country = new Country(countryCode, countryName);
        given(countryRepository.findByCode(countryCode)).willReturn(country);
        
        
        CityInfo actualCityInfo = cityService.getCityInfo(cityName);
        
        assertThat(actualCityInfo).isNotNull();
        assertThat(actualCityInfo.getName()).isEqualTo(cityName);
        assertThat(actualCityInfo.getCountryCode()).isEqualTo(countryCode);
        assertThat(actualCityInfo.getCountryName()).isEqualTo(countryName);
        assertThat(actualCityInfo.getPopulation()).isEqualTo(population);
        assertThat(actualCityInfo.getDistrict()).isEqualTo(district);
        assertThat(actualCityInfo.getTemp()).isEqualTo(temp);
	}

}
