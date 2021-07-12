package keyboard.works.entity.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import keyboard.works.entity.validation.annotation.ProductCodeMustUnique;
import keyboard.works.repository.ProductRepository;

public class ProductCodeMustUniqueValidator implements ConstraintValidator<ProductCodeMustUnique, String> {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !productRepository.findByCode(value).isPresent();
	}

}
