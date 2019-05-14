package ru.uziki.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DishRepositoryImpl {
    @Autowired
    private CrudDishRepository crudDishRepository;
}
