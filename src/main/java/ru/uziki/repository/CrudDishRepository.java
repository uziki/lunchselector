package ru.uziki.repository;

import ru.uziki.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudDishRepository extends JpaRepository<Dish, Integer> {
}
