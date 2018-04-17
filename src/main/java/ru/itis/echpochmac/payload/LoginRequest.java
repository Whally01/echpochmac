package ru.itis.echpochmac.payload;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String loginOrPhone_number;

    @NotBlank
    private String password;

    public String getLoginOrPhone_number() {
        return loginOrPhone_number;
    }

    public void setLoginOrPhone_number(String loginOrPhone_number) {
        this.loginOrPhone_number = loginOrPhone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
