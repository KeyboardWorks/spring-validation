package keyboard.works.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import keyboard.works.entity.request.UserRequest;
import keyboard.works.entity.response.UserResponse;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void createUserTest() {
		
		UserRequest userRequest = UserRequest.builder()
				.name("User Name")
				.username("username")
				.password("password")
				.build();
		
		UserResponse response = userService.createUser(userRequest);
		
		assertNotNull(response);
		assertEquals("User Name", response.getName());
		assertEquals("username", response.getUsername());
	}
	
	@Test
	public void createUserTestValidation() {
		
		UserRequest userRequest = UserRequest.builder()
				.build();
		
		assertThrows(ConstraintViolationException.class, () -> {
			userService.createUser(userRequest);
		});
		
	}
	
}
