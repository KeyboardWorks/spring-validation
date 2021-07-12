package keyboard.works.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import keyboard.works.entity.User;
import keyboard.works.entity.request.UserRequest;
import keyboard.works.repository.UserRepository;
import keyboard.works.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getUsers() {
		
		List<User> users = new LinkedList<>();
		
		userRepository.findAll().forEach(users::add);
		
		return users;
	}

	@Override
	public User getUser(String id) {
		return userRepository.findById(id).orElseThrow(RuntimeException::new);
	}

	@Override
	public User createUser(UserRequest userRequest) {
		
		User user = new User();
		BeanUtils.copyProperties(userRequest, user);
		user = userRepository.save(user);
		
		return user;
	}

	@Override
	public User updateUser(String id, UserRequest userRequest) {
		
		User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
		
		BeanUtils.copyProperties(userRequest, user, "password");
		
		user = userRepository.save(user);
		
		return user;
	}

	@Override
	public void deleteUser(String id) {
		
		User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
		
		userRepository.delete(user);
	}

}
