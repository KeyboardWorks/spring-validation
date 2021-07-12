package keyboard.works.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import keyboard.works.entity.Product;
import keyboard.works.entity.request.ProductRequest;

@SpringBootTest
public class ProductServiceTest {

	@Autowired
	private ProductService productService;
	
	@Test
	public void createProductTest() {
	
		ProductRequest productRequest = ProductRequest.builder()
				.code("Product Code")
				.name("Product Name")
				.price(new BigDecimal(100))
				.build();
		
		Product product = productService.createProduct(productRequest);
		
		assertNotNull(product);
		assertNotNull(product.getId());
		assertEquals("Product Code", product.getCode());
		assertEquals("Product Name", product.getName());
		assertEquals(new BigDecimal(100), product.getPrice());
		
	}
	
}
