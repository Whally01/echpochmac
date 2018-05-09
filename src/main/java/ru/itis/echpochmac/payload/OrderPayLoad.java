package ru.itis.echpochmac.payload;

import ru.itis.echpochmac.model.Dish;
import ru.itis.echpochmac.model.OrderStatus;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

public class OrderPayLoad {
    @NotNull
    private String quantity;

    @NotNull
    private String priceOrder;

    @NotBlank
    @Size(max = 40)
    private String comment;

    @NotBlank
    @Size(max = 40)
    private String destinationAddress;

    @NotBlank
    private OrderStatus status;

    @NotBlank
    private Set<Dish> dishSet;

    @NotBlank
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Set<Dish> getDishSet() {
        return dishSet;
    }

    public void setDishSet(Set<Dish> dishSet) {
        this.dishSet = dishSet;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(String priceOrder) {
        this.priceOrder = priceOrder;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }
}
