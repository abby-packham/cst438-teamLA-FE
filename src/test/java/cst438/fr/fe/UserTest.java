package cst438.fr.fe;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserTest {
	
	@Autowired
	private User User;
	
	@MockBean 
	private UserRepository userRepo;
	
	private User test1 = new User(1248, "John", "Doe", "usernjohn", "passwjohn"); 
	private User test2 = new User(1397, "Jane", "Smith", "userjane", "passwjane"); 
	
	@Test
	public void contextLoads() {
	}
	
	
	
	@Test
	{
		Optional<User> optionalVersion = Optional.of(test1);
		
		given(userRepo.findByUserId(Long.toString(1))).willReturn(optionalVersion);
		
		
		User theUser = userRepository.getUserByID(1);
		assertThat(theUser).isEqualTo(test1);
	}
	
	

}
