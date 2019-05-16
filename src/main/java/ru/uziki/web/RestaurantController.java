package ru.uziki.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.uziki.AuthorizedUser;
import ru.uziki.model.Restaurant;
import ru.uziki.repository.Restaurant.RestaurantRepository;

import java.net.URI;
import java.util.List;

import static ru.uziki.Util.UserUtil.isAdmin;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    static final String REST_URL = "/restaurants";

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return restaurantRepository.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@AuthenticationPrincipal AuthorizedUser authorizedUser, @RequestBody Restaurant restaurant) {
        if (isAdmin(authorizedUser)) {
            Restaurant created = restaurantRepository.save(restaurant);
            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(created.getId()).toUri();
            return ResponseEntity.created(uriOfNewResource).body(created);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> update(@AuthenticationPrincipal AuthorizedUser authorizedUser, @RequestBody Restaurant restaurant, @PathVariable int id) {
        if (isAdmin(authorizedUser) && restaurant.getId() == id) {
            return new ResponseEntity<>(restaurantRepository.save(restaurant), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
