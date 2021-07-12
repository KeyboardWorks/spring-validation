package keyboard.works.entity.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import keyboard.works.entity.validation.validator.ProductCodeMustUniqueValidator;

@Constraint(validatedBy = ProductCodeMustUniqueValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductCodeMustUnique {

	String message() default "Product code already used";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

	
}
