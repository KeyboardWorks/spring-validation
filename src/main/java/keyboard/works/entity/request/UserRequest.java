package keyboard.works.entity.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import keyboard.works.entity.validation.group.CreateData;
import keyboard.works.entity.validation.group.UpdateData;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequest {

	@NotBlank(message = "Name is mandatory !", groups = {CreateData.class, UpdateData.class})
	private String name;
	
	@NotBlank(message = "Username is mandatory !", groups = {CreateData.class, UpdateData.class})
	@Size(min = 8, message = "Username minimal 8 characters", groups = {CreateData.class, UpdateData.class})
	private String username;
	
	@NotBlank(message = "Password is mandatory !", groups = {CreateData.class})
	@Size(min = 8, message = "Password minimal 8 characters", groups = {CreateData.class})
	private String password;
	
}
