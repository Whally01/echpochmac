/*
package ru.itis.echpochmac.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bag")
public class Bag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Order order;

    private List<Dish> dishes;

    private int countDish;

    public Bag() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public int getCountDish() {
        return countDish;
    }

    public void setCountDish(int countDish) {
        this.countDish = countDish;
    }
}
*/
