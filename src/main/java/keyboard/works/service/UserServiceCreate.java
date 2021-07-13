package keyboard.works.service;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import keyboard.works.entity.request.UserRequest;
import keyboard.works.entity.response.UserResponse;
import keyboard.works.entity.validation.group.CreateData;

@Validated(CreateData.class)
public interface UserServiceCreate {

	UserResponse createUser(@Valid UserRequest userRequest);
	
}
