package ru.itis.echpochmac.model;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import ru.itis.echpochmac.model.audit.DateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "login"),
        @UniqueConstraint(columnNames = "phone")})
public class User extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String firstname;

    @NotBlank
    @Size(max = 15)
    private String lastname;

    @NotBlank
    @NaturalId
    @Size(max = 15)
    private String login;
    /* @NaturalId
     @Size(max = 100)
     @Email
     private String email;*/
    @NotBlank
    @Size(max = 11)
    private String phone;

    @NotBlank
    @Size(max = 100)
    private String password;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] avatar;

   /* @JoinColumn(name = "likes")
    @OneToMany(mappedBy = "user")
    private List<Boolean> likes;*/

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>(0);

    @OneToMany(mappedBy = "user")
    private Set<Order> orders;


    public User() {
    }

    public User(String firstname, String lastname, String login, String phone_number, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.phone = phone_number;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return login;
    }

    public void setEmail(String email) {
        this.login = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }


    /*public List<Boolean> getLikes() {
        return likes;
    }

    public void setLikes(List<Boolean> likes) {
        this.likes = likes;
    }*/
}
