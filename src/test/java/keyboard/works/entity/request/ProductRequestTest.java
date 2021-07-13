package keyboard.works.entity.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import keyboard.works.entity.Product;
import keyboard.works.repository.ProductRepository;

@SpringBootTest
public class ProductRequestTest {

	@Autowired
	private Validator validator;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	public void productRequestValid() {
		
		productRepository.deleteAll();
		
		ProductRequest productRequest = ProductRequest.builder()
				.code("Product Code")
				.name("Product Name")
				.price(new BigDecimal(100))
				.build();
		
		Set<ConstraintViolation<ProductRequest>> constraintViolations = validator.validate(productRequest);
		
		assertTrue(constraintViolations.isEmpty());
	}
	
	@Test
	public void productRequestInvalid() {
		
		ProductRequest producteRequest = ProductRequest.builder()
				.build();
		
		Set<ConstraintViolation<ProductRequest>> constraintViolations = validator.validate(producteRequest);
		
		assertFalse(constraintViolations.isEmpty());
		assertEquals(3, constraintViolations.size());
		
	}
	
	@Test
	public void productRequestInvalidCodeUnique() {
	
		Product product = Product.builder()
				.code("Product Code")
				.name("Product Name")
				.price(new BigDecimal(100))
				.build();
		
		productRepository.save(product);
		
		ProductRequest productRequest = ProductRequest.builder()
				.code("Product Code")
				.name("Product Name")
				.price(new BigDecimal(100))
				.build();
		
		Set<ConstraintViolation<ProductRequest>> constraintViolations = validator.validate(productRequest);
		
		assertFalse(constraintViolations.isEmpty());
		assertEquals(1, constraintViolations.size());
		
	}
	
}
