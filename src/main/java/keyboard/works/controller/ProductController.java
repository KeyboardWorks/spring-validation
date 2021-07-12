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

import keyboard.works.entity.Product;
import keyboard.works.entity.request.ProductRequest;
import keyboard.works.entity.response.GenericResponse;
import keyboard.works.entity.response.ProductResponse;
import keyboard.works.service.ProductService;
import keyboard.works.utils.ResponseHelper;

@RestController
@RequestMapping(
	path = "/products",
	produces = MediaType.APPLICATION_JSON_VALUE
)
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public GenericResponse<List<ProductResponse>> getProducts() {
		
		List<ProductResponse> list = productService.getProducts().stream()
				.map(product -> {
					ProductResponse productResponse = new ProductResponse();
					BeanUtils.copyProperties(product, productResponse);
					return productResponse;
				})
				.collect(Collectors.toList());
		
		return ResponseHelper.ok(list);
	}
	
	@GetMapping(path = "{id}")
	public GenericResponse<ProductResponse> getProduct(@PathVariable("id") String id) {
		
		Product product = productService.getProduct(id);
		ProductResponse productResponse = new ProductResponse();
		
		BeanUtils.copyProperties(product, productResponse);
		
		return ResponseHelper.ok(productResponse);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse<ProductResponse> createUser(@Validated @RequestBody ProductRequest productRequest) {
		
		Product product = productService.createProduct(productRequest);
		ProductResponse productResponse = new ProductResponse();
		
		BeanUtils.copyProperties(product, productResponse);
		
		return ResponseHelper.ok(productResponse);
		
	}
	
	@PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse<ProductResponse> updateUser(@PathVariable("id")String id, @Validated @RequestBody ProductRequest productRequest) {
		
		Product product = productService.updateProduct(id, productRequest);
		ProductResponse productResponse = new ProductResponse();
		
		BeanUtils.copyProperties(product, productResponse);
		
		return ResponseHelper.ok(productResponse);
		
	}
	
	@DeleteMapping(path = "{id}")
	public GenericResponse<?> deleteUser(@PathVariable("id")String id) {
		
		productService.deleteProduct(id);
		
		return ResponseHelper.ok();
	}
	
}
