package keyboard.works.service;

import java.util.List;

import keyboard.works.entity.Product;
import keyboard.works.entity.request.ProductRequest;

public interface ProductService {

	List<Product> getProducts();
	
	Product getProduct(String id);
	
	Product createProduct(ProductRequest productRequest);
	
	Product updateProduct(String id, ProductRequest productRequest);
	
	void deleteProduct(String id);
	
}
