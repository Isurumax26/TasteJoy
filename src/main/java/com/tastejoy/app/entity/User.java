package com.tastejoy.app.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class User {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String secondName;
    private String phoneNumber;
    private String address;

    private Role[] roles;
}
