package cst438.fr.fe.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cst438.fr.fe.domain.City;
import cst438.fr.fe.domain.CityInfo;
import cst438.fr.fe.domain.CityRepository;
import cst438.fr.fe.domain.Country;
import cst438.fr.fe.domain.CountryRepository;
import cst438.fr.fe.domain.TempAndTime;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private WeatherService weatherService;
	
	
	 @Autowired
	private RabbitTemplate rabbitTemplate;
	
	 
	@Autowired
	private FanoutExchange fanout;
	 
	public CityService() {
	    
	}
	
	public CityService(CityRepository cityRepository, CountryRepository countryRepository
	        , WeatherService weatherService) {
	    this.cityRepository = cityRepository;
	    this.countryRepository = countryRepository;
	    this.weatherService = weatherService;
	}
	public CityInfo getCityInfo(String cityName) {
	    
	    List<City> cityList = cityRepository.findByName(cityName);
	    
	    if (!cityList.isEmpty()) {
	        //just return the first City.
	        City city = cityList.get(0);
	        
	        Country country = countryRepository.findByCode(city.getCountryCode());

	        TempAndTime tempAndTime = weatherService.getTempAndTime(cityName);

	        double kelvin = tempAndTime.temp;
	        double fahrenheit = Math.round((kelvin - 273.15) * 9.0/5.0 + 32.0);

	        //openweathermap seems to have delay of 10 mins
	        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond((tempAndTime.time + tempAndTime.timezone), 0, ZoneOffset.UTC); 
	        
	        CityInfo cityInfo = new CityInfo(city.getId(), city.getName(), 
	                country.getCode(), country.getName(), city.getDistrict(), city.getPopulation(), fahrenheit, localDateTime.toLocalTime().toString());

	        return cityInfo;
	    }

	    return null;
	}

    public void requestReservation(String cityName, String level, String email) {
        String msg  = "{\"cityName\": \""+ cityName + 
                "\" \"level\": \""+level+
                "\" \"email\": \""+email+"\"}" ;
        
        System.out.println("Sending message:"+msg);
        
        rabbitTemplate.convertSendAndReceive(
                fanout.getName(), 
                "",   // routing key none.
                msg);

    }
	
}
