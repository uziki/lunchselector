package ru.uziki.repository.Restaurant;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.uziki.model.Restaurant;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Override
    @Transactional
    Restaurant save(Restaurant restaurant);

    @Override
    List<Restaurant> findAll(Sort sort);

    @Override
    Optional<Restaurant> findById(Integer id);
}
