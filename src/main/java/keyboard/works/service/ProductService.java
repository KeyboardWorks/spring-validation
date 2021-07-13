package keyboard.works.service;

import java.util.List;

import keyboard.works.entity.request.ProductRequest;
import keyboard.works.entity.response.ProductResponse;

public interface ProductService {

	List<ProductResponse> getProducts();
	
	ProductResponse getProduct(String id);
	
	ProductResponse createProduct(ProductRequest productRequest);
	
	ProductResponse updateProduct(String id, ProductRequest productRequest);
	
	void deleteProduct(String id);
	
}
