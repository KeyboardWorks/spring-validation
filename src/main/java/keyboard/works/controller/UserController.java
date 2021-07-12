package keyboard.works.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import keyboard.works.entity.User;
import keyboard.works.entity.request.UserRequest;
import keyboard.works.entity.response.GenericResponse;
import keyboard.works.entity.response.UserReponse;
import keyboard.works.entity.validation.group.CreateData;
import keyboard.works.entity.validation.group.UpdateData;
import keyboard.works.service.UserService;
import keyboard.works.utils.ResponseHelper;

@RestController
@RequestMapping(
	path = "/users",
	produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public GenericResponse<List<UserReponse>> getUsers() {
		
		List<UserReponse> list = userService.getUsers().stream()
				.map(user -> {
					UserReponse userReponse = new UserReponse();
					BeanUtils.copyProperties(user, userReponse);
					return userReponse;
				})
				.collect(Collectors.toList());
		
		return ResponseHelper.ok(list);
	}
	
	@GetMapping(path = "{id}")
	public GenericResponse<UserReponse> getUser(@PathVariable("id") String id) {
		
		User user = userService.getUser(id);
		UserReponse userReponse = new UserReponse();
		
		BeanUtils.copyProperties(user, userReponse);
		
		return ResponseHelper.ok(userReponse);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse<UserReponse> createUser(@Validated(CreateData.class) @RequestBody UserRequest userRequest) {
		
		User user = userService.createUser(userRequest);
		UserReponse userReponse = new UserReponse();
		
		BeanUtils.copyProperties(user, userReponse);
		
		return ResponseHelper.ok(userReponse);
		
	}
	
	@PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse<UserReponse> updateUser(@PathVariable("id")String id, @Validated(UpdateData.class) @RequestBody UserRequest userRequest) {
		
		User user = userService.updateUser(id, userRequest);
		UserReponse userReponse = new UserReponse();
		
		BeanUtils.copyProperties(user, userReponse);
		
		return ResponseHelper.ok(userReponse);
		
	}
	
	@DeleteMapping(path = "{id}")
	public GenericResponse<?> deleteUser(@PathVariable("id")String id) {
		
		userService.deleteUser(id);
		
		return ResponseHelper.ok();
	}
	
}
