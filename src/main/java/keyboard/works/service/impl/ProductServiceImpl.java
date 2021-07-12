package keyboard.works.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import keyboard.works.entity.Product;
import keyboard.works.entity.request.ProductRequest;
import keyboard.works.repository.ProductRepository;
import keyboard.works.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getProducts() {
		
		List<Product> products = new LinkedList<>();
		
		productRepository.findAll().forEach(products::add);
		
		return products;
	}

	@Override
	public Product getProduct(String id) {
		return productRepository.findById(id).orElseThrow(RuntimeException::new);
	}

	@Override
	public Product createProduct(ProductRequest productRequest) {
		
		Product product = new Product();
		BeanUtils.copyProperties(productRequest, product);
		
		product = productRepository.save(product);
		
		return product;
	}

	@Override
	public Product updateProduct(String id, ProductRequest productRequest) {
		
		Product product = getProduct(id);
		
		BeanUtils.copyProperties(productRequest, product);

		product = productRepository.save(product);
		
		return product;
	}

	@Override
	public void deleteProduct(String id) {
		
		Product product = getProduct(id);
		
		productRepository.delete(product);
		
	}

}
