package ru.uziki.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = "restaurant_id", name = "restaurants_unique_name_idx")})
public class Dish extends AbstractNamedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    public Dish() {}

    public Dish(String name, Restaurant restaurant, BigDecimal price, LocalDateTime dateTime) {
        this(null, name, restaurant, price, dateTime);
    }

    public Dish(Integer id, String name, Restaurant restaurant, BigDecimal price, LocalDateTime dateTime) {
        super(id, name);
        this.restaurant = restaurant;
        this.price = price;
        this.dateTime = dateTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", restaurant=" + restaurant.getName() +
                ", name=" + name +
                ", price=" + price +
                '}';
    }
}
