package ua.pylypchenko.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pylypchenko.userservice.domain.User;
import ua.pylypchenko.userservice.repository.UserRepository;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.saveAndFlush(user);
    }
}
