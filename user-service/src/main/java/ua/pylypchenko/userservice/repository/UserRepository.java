package ua.pylypchenko.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.pylypchenko.userservice.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{


    User findUserByName(String name);
}
