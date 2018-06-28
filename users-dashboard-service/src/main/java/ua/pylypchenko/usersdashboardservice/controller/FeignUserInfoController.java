package ua.pylypchenko.usersdashboardservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import ua.pylypchenko.usersdashboardservice.client.UserServiceClient;
import ua.pylypchenko.usersdashboardservice.domain.UserInfo;

import java.util.List;

@RefreshScope
@RestController
public class FeignUserInfoController {

    @Autowired
    private UserServiceClient userService;

    @RequestMapping(method = RequestMethod.GET, value = "/dashboard/users/{name}")
    public UserInfo findUser(@PathVariable(name = "name") String name){
        return userService.getUserByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dashboard/users")
    public List<UserInfo> findAll(){
        return userService.getAllUsers();
    }


}
