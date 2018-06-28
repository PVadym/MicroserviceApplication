package ua.pylypchenko.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pylypchenko.userservice.domain.User;
import ua.pylypchenko.userservice.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/users")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(value = "/users/{name}")
    public ResponseEntity getUserByName(@PathVariable String name){
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@RequestBody User user){
        User saved = userService.saveUser(User.builder()
                .name(user.getName())
                .age(user.getAge())
                .build());

        return ResponseEntity.ok(saved);
    }

}
