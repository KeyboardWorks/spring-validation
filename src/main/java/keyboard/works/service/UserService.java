package keyboard.works.service;

import java.util.List;

import keyboard.works.entity.User;
import keyboard.works.entity.request.UserRequest;

public interface UserService {

	List<User> getUsers();
	
	User getUser(String id);
	
	User createUser(UserRequest userRequest);
	
	User updateUser(String id, UserRequest userRequest);
	
	void deleteUser(String id);
	
}
