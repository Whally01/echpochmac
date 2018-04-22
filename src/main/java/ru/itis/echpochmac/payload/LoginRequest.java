package ru.itis.echpochmac.payload;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String loginOrPhone;

    @NotBlank
    private String password;

    public String getLoginOrPhone() {
        return loginOrPhone;
    }

    public void setLoginOrPhone(String loginOrPhone) {
        this.loginOrPhone = loginOrPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
