package keyboard.works.entity.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

	@NotBlank(message = "Name is mandatory !")
	private String name;
	
	@NotBlank(message = "Username is mandatory !")
	@Size(min = 8, message = "Username minimal 8 characters")
	private String username;
	
	@NotBlank(message = "Password is mandatory !")
	@Size(min = 8, message = "Password minimal 8 characters")
	private String password;
	
}
