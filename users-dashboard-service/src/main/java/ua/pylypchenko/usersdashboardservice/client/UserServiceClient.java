package ua.pylypchenko.usersdashboardservice.client;




import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import ua.pylypchenko.usersdashboardservice.domain.UserInfo;

import java.util.List;

@FeignClient(name="user-service")
@RibbonClient(name = "user-service")
public interface UserServiceClient {


    @GetMapping("/users/{name}")
    UserInfo getUserByName(@PathVariable(value = "name") String name);

    @GetMapping("/users")
    List<UserInfo> getAllUsers();

}
