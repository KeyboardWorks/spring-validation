package keyboard.works.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import keyboard.works.entity.Product;
import keyboard.works.entity.request.ProductRequest;
import keyboard.works.entity.response.ProductResponse;
import keyboard.works.repository.ProductRepository;
import keyboard.works.service.impl.ProductServiceImpl;

@SpringBootTest
public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;
	
	@Captor
	private ArgumentCaptor<Product> productCaptor;
	
	@InjectMocks
	private ProductServiceImpl productService;
	
	@Test
	public void getProductTest() {
		
		Product product = Product.builder()
				.id(UUID.randomUUID().toString())
				.code("Product Code")
				.name("Product Name")
				.price(new BigDecimal(100))
				.build();
		
		when(productRepository.findById(Mockito.anyString())).thenReturn(Optional.of(product));
		
		ProductResponse response = productService.getProduct("123");
		
		assertNotNull(response);
		assertEquals("Product Code", response.getCode());
		assertEquals("Product Name", response.getName());
		assertEquals(new BigDecimal(100), response.getPrice());
		
		verify(productRepository).findById(Mockito.anyString());
	}
	
	@Test
	public void createProductTest() {
	
		Product product = Product.builder()
				.id(UUID.randomUUID().toString())
				.code("Product Code")
				.name("Product Name")
				.price(new BigDecimal(100))
				.build();
		
		when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
		
		ProductRequest productRequest = ProductRequest.builder()
				.code("Product Code")
				.name("Product Name")
				.price(new BigDecimal(100))
				.build();
		
		ProductResponse response = productService.createProduct(productRequest);
		
		assertNotNull(response);
		assertEquals("Product Code", response.getCode());
		assertEquals("Product Name", response.getName());
		assertEquals(new BigDecimal(100), response.getPrice());
		
		verify(productRepository).save(productCaptor.capture());
		
		Product productValue = productCaptor.getValue();
		
		assertNotNull(productValue);
		assertNotNull(product.getId());
		
	}
	
	@Test
	public void deleteProductTest() {
		
		Product product = Product.builder()
				.id(UUID.randomUUID().toString())
				.code("Product Code")
				.name("Product Name")
				.price(new BigDecimal(100))
				.build();
		
		when(productRepository.findById(Mockito.anyString())).thenReturn(Optional.of(product));
		
		productService.deleteProduct("123");
		
		verify(productRepository).findById(Mockito.anyString());
		verify(productRepository).delete(product);
		
	}
	
	@AfterEach
	public void tearDown() {
		verifyNoMoreInteractions(productRepository);
	}
	
}
