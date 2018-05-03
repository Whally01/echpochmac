package ru.itis.echpochmac.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpRequest {
    @NotBlank(message = "firstname can't empty!")
    @Size(min = 4, max = 40)
    private String firstname;

    @NotBlank(message = "lastname can't empty!")
    @Size(min = 4, max = 40)
    private String lastname;


    @NotBlank(message = "login can't empty!")
    @Size(min = 3, max = 15)
    private String login;

    @NotBlank(message = "phone can't empty!")
    @Size(min = 11, max = 11)
    private String phone;

    @NotBlank(message = "password can't empty!")
    @Size(min = 6, max = 20)
    private String password;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}