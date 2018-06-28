package ua.pylypchenko.usersdashboardservice.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {

    private int id;

    private String name;

    private int age;


}
