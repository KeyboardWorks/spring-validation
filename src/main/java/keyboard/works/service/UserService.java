package keyboard.works.service;

import java.util.List;

import keyboard.works.entity.response.UserResponse;

public interface UserService extends UserServiceCreate, UserServiceUpdate {

	List<UserResponse> getUsers();
	
	UserResponse getUser(String id);
	
	void deleteUser(String id);
	
}
