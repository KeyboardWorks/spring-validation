package keyboard.works.service;

import java.util.List;

import keyboard.works.entity.User;

public interface UserService extends UserServiceCreate, UserServiceUpdate {

	List<User> getUsers();
	
	User getUser(String id);
	
	void deleteUser(String id);
	
}
