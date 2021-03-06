package keyboard.works.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import keyboard.works.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	Optional<Product> findByCode(String code);
	
}
