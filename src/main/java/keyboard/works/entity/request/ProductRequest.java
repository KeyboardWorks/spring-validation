package keyboard.works.entity.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductRequest {

//	@ProductCodeMustUnique
	@NotBlank(message = "Code is mandatory")
	@Size(min = 5, message = "Product code length must greather than 5")
	private String code;
	
	@NotBlank(message = "Code is mandatory")
	@Size(min = 5, message = "Product name length must greather than 5")
	private String name;
	
	@NotNull(message = "Price is mandatory")
	@Positive(message = "Product must greather than zero")
	private BigDecimal price;
	
}
