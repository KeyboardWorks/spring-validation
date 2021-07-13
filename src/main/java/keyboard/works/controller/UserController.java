package keyboard.works.controller;

import java.util.List;

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

import keyboard.works.entity.request.UserRequest;
import keyboard.works.entity.response.GenericResponse;
import keyboard.works.entity.response.UserResponse;
import keyboard.works.entity.validation.group.CreateData;
import keyboard.works.entity.validation.group.UpdateData;
import keyboard.works.service.UserService;
import keyboard.works.utils.GenericResponseHelper;

@RestController
@RequestMapping(
	path = "/users",
	produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public GenericResponse<List<UserResponse>> getUsers() {
		return GenericResponseHelper.ok(userService.getUsers());
	}
	
	@GetMapping(path = "{id}")
	public GenericResponse<UserResponse> getUser(@PathVariable("id") String id) {
		return GenericResponseHelper.ok(userService.getUser(id));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse<UserResponse> createUser(@Validated(CreateData.class) @RequestBody UserRequest userRequest) {
		return GenericResponseHelper.ok(userService.createUser(userRequest));
		
	}
	
	@PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse<UserResponse> updateUser(@PathVariable("id")String id, @Validated(UpdateData.class) @RequestBody UserRequest userRequest) {
		return GenericResponseHelper.ok(userService.updateUser(id, userRequest));
		
	}
	
	@DeleteMapping(path = "{id}")
	public GenericResponse<?> deleteUser(@PathVariable("id")String id) {
		
		userService.deleteUser(id);
		
		return GenericResponseHelper.ok();
	}
	
}
