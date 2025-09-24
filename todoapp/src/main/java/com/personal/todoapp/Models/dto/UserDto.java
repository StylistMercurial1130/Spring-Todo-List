package com.personal.todoapp.Models.dto;

import java.io.Serializable;

public class UserDto implements Serializable {
    public final String accountName;
    public final String password;
    public final String emailId;

    public UserDto(String emailId,String accountName, String password) {
        this.emailId = emailId;
        this.accountName = accountName;
        this.password = password;
    }
}
