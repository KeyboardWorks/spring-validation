package keyboard.works.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import keyboard.works.entity.Product;
import keyboard.works.entity.request.ProductRequest;
import keyboard.works.entity.response.ProductResponse;
import keyboard.works.repository.ProductRepository;
import keyboard.works.service.ProductService;
import keyboard.works.utils.ResponseHelper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<ProductResponse> getProducts() {
		
		List<Product> products = new LinkedList<>();
		
		productRepository.findAll().forEach(products::add);
		
		return ResponseHelper.createResponses(ProductResponse.class, products);
	}

	@Override
	public ProductResponse getProduct(String id) {
		Product product = productRepository.findById(id).orElseThrow(RuntimeException::new);
		return ResponseHelper.createResponse(ProductResponse.class, product);
	}

	@Override
	public ProductResponse createProduct(ProductRequest productRequest) {
		
		Product product = new Product();
		BeanUtils.copyProperties(productRequest, product);
		
		product = productRepository.save(product);
		
		return ResponseHelper.createResponse(ProductResponse.class, product);
	}

	@Override
	public ProductResponse updateProduct(String id, ProductRequest productRequest) {
		
		Product product = productRepository.findById(id).orElseThrow(RuntimeException::new);
		BeanUtils.copyProperties(productRequest, product);
		product = productRepository.save(product);
		
		return ResponseHelper.createResponse(ProductResponse.class, product);
	}

	@Override
	public void deleteProduct(String id) {
		
		Product product = productRepository.findById(id).orElseThrow(RuntimeException::new);
		
		productRepository.delete(product);
		
	}

}
