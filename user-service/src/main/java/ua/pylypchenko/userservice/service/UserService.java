package ua.pylypchenko.userservice.service;



import ua.pylypchenko.userservice.domain.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserByName(String name);

    User saveUser(User user);
}
