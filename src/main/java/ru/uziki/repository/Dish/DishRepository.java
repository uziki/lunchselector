package ru.uziki.repository.Dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.uziki.model.Dish;

import java.util.List;

@Repository
public class DishRepository {

    @Autowired
    private CrudDishRepository crudDishRepository;

    public Dish save(Dish dish) {
        return crudDishRepository.save(dish);
    }

    public boolean delete(int id) {
        return crudDishRepository.delete(id) != 0;
    }

    public Dish get(int id) {
        return crudDishRepository.findById(id).orElse(null);
    }

    public List<Dish> getAll(int id) {
        return crudDishRepository.getAll(id);
    }
}
