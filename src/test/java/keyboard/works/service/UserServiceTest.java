package keyboard.works.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import keyboard.works.entity.User;
import keyboard.works.entity.request.UserRequest;
import keyboard.works.entity.validation.group.CreateData;
import keyboard.works.entity.validation.group.UpdateData;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Autowired
	private Validator validator;
	
	@Test
	public void createUserTest() {
		
		UserRequest userRequest = UserRequest.builder()
				.name("User Name")
				.username("username")
				.password("password")
				.build();
		
		User user = userService.createUser(userRequest);
		
		assertNotNull(user);
		assertNotNull(user.getId());
		assertEquals("User Name", user.getName());
		assertEquals("username", user.getUsername());
		assertEquals("password", user.getPassword());
	}
	
	@Test
	public void createUserTestValidation() {
		
		UserRequest userRequest = UserRequest.builder()
				.build();
		
		assertThrows(ConstraintViolationException.class, () -> {
			userService.createUser(userRequest);
		});
		
	}
	
	@Test
	public void userRequestValidationCreateData() {
		
		UserRequest userRequest = UserRequest.builder()
				.build();
		
		
		Set<ConstraintViolation<UserRequest>> constraintViolations = validator.validate(userRequest, CreateData.class);
		
		assertFalse(constraintViolations.isEmpty());
		assertEquals(3, constraintViolations.size());
	}
	
	@Test
	public void userRequestValidationUpdateData() {
		
		UserRequest userRequest = UserRequest.builder()
				.build();
		
		Set<ConstraintViolation<UserRequest>> constraintViolations = validator.validate(userRequest, UpdateData.class);
		
		assertFalse(constraintViolations.isEmpty());
		assertEquals(2, constraintViolations.size());
	}
	
	
}
