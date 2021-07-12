package keyboard.works.service;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import keyboard.works.entity.User;
import keyboard.works.entity.request.UserRequest;
import keyboard.works.entity.validation.group.UpdateData;

@Validated(UpdateData.class)
public interface UserServiceUpdate {

	User updateUser(@Valid String id, UserRequest userRequest);
}
