package cst438.fr.fe.domain;

import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SpringBootTest
public class UserTest {
	
    @Test
    public void testUser() throws Exception {
        String firstName = "Charlie";
        long id = 123;
        String lastName = "Kim";
        String password = "test";
        String userId = "testID";
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setUserId(userId);
        user.setId(id);
        
        assertThat(user.getFirstName()).isEqualTo(firstName);
        assertThat(user.getLastName()).isEqualTo(lastName);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getUserId()).isEqualTo(userId);
        assertThat(user.getId()).isEqualTo(id);
    }

}
