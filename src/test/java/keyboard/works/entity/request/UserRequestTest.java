package keyboard.works.entity.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import keyboard.works.entity.validation.group.CreateData;
import keyboard.works.entity.validation.group.UpdateData;

@SpringBootTest
public class UserRequestTest {
	
	@Autowired
	private Validator validator;

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
