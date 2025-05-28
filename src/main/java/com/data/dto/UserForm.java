package com.data.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserForm {
    @NotEmpty(message = "{error.username.required}")
    private String username;

    @NotEmpty(message = "{error.password.required}")
    private String password;

    @NotEmpty(message = "{error.email.invalid}")
    @Email(message = "{error.email.invalid}")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
