package ru.uziki.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.uziki.AuthorizedUser;
import ru.uziki.model.Dish;
import ru.uziki.repository.Dish.DishRepository;

import java.net.URI;
import java.util.List;

import static ru.uziki.Util.UserUtil.isAdmin;

@RestController
@RequestMapping(DishController.REST_URL)
public class DishController {
    static final String REST_URL = "/restaurants/{restId}/dishes";

    @Autowired
    private DishRepository dishRepository;

    @GetMapping
    public List<Dish> getAll(@PathVariable int restId) {
        return dishRepository.getAll(restId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable int id) {
        return dishRepository.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish) {
        Dish created = dishRepository.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Dish update(@RequestBody Dish dish, @PathVariable int id) {
        return dishRepository.save(dish);
    }
}
