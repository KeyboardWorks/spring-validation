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

import keyboard.works.entity.request.ProductRequest;
import keyboard.works.entity.response.GenericResponse;
import keyboard.works.entity.response.ProductResponse;
import keyboard.works.service.ProductService;
import keyboard.works.utils.GenericResponseHelper;

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
		return GenericResponseHelper.ok(productService.getProducts());
	}
	
	@GetMapping(path = "{id}")
	public GenericResponse<ProductResponse> getProduct(@PathVariable("id") String id) {
		return GenericResponseHelper.ok(productService.getProduct(id));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse<ProductResponse> createUser(@Validated @RequestBody ProductRequest productRequest) {
		return GenericResponseHelper.ok(productService.createProduct(productRequest));
	}
	
	@PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse<ProductResponse> updateUser(@PathVariable("id")String id, @Validated @RequestBody ProductRequest productRequest) {
		return GenericResponseHelper.ok(productService.updateProduct(id, productRequest));
		
	}
	
	@DeleteMapping(path = "{id}")
	public GenericResponse<?> deleteUser(@PathVariable("id")String id) {
		productService.deleteProduct(id);
		return GenericResponseHelper.ok();
	}
	
}
