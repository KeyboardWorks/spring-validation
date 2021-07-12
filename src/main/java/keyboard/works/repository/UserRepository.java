package keyboard.works.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import keyboard.works.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}
