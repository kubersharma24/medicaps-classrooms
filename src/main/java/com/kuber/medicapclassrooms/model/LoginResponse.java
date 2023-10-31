package com.kuber.medicapclassrooms.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String email;
    private String password;
    private String status; // will return role on successful authentication of user else will generate an error message
}
