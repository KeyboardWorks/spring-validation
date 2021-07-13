package keyboard.works.service;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import keyboard.works.entity.request.UserRequest;
import keyboard.works.entity.response.UserResponse;
import keyboard.works.entity.validation.group.UpdateData;

@Validated(UpdateData.class)
public interface UserServiceUpdate {

	UserResponse updateUser(@Valid String id, UserRequest userRequest);
}
