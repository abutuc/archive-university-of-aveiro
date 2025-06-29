package ua.tqs.hw1.repository;

import org.springframework.data.repository.CrudRepository;
import ua.tqs.hw1.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
