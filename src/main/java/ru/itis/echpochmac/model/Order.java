package ru.itis.echpochmac.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long quantity;

    @NotNull
    private Double priceOrder;

    @NotBlank
    private String comment;

    @NotBlank
    @Size(max = 40)
    private String destinationAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10, columnDefinition = "varchar(10) default 'NEW'")
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "user_ID")
    private User user;

    @OneToMany(mappedBy = "order")
    private Set<Dish> dishes;


  //  private Long courierId;

    public Order() {
    }

    public Order(@NotNull Long quantity, @NotNull Double priceOrder, @NotBlank String comment,
                 @NotBlank @Size(max = 40) String destinationAddress,
                 User user, Set<Dish> dishes) {
        this.quantity = quantity;
        this.priceOrder = priceOrder;
        this.comment = comment;
        this.destinationAddress = destinationAddress;
        this.user = user;
        this.dishes = dishes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPriceOrder() {
        return priceOrder;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public void setPriceOrder(Double priceOrder) {
        this.priceOrder = priceOrder;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
