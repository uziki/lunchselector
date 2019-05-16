package ru.uziki.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.uziki.AuthorizedUser;
import ru.uziki.model.User;
import ru.uziki.repository.User.UserRepository;

import java.net.URI;
import java.util.List;

import static ru.uziki.Util.UserUtil.isAdmin;

@RestController
@RequestMapping(value = UserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    static final String REST_URL = "/users";

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> getAll(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        if (isAdmin(authorizedUser)) {
            return new ResponseEntity<>(userRepository.getAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@AuthenticationPrincipal AuthorizedUser authorizedUser, @PathVariable int id) {
        if (isAdmin(authorizedUser) || authorizedUser.getId() == id) {
            return new ResponseEntity<>(userRepository.get(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@AuthenticationPrincipal AuthorizedUser authorizedUser, @PathVariable int id) {
        if (isAdmin(authorizedUser) || authorizedUser.getId() == id) {
            return new ResponseEntity<>(userRepository.delete(id), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocation(@AuthenticationPrincipal AuthorizedUser authorizedUser, @RequestBody User user) {
        if (isAdmin(authorizedUser)) {
            User created = userRepository.save(user);
            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(created.getId()).toUri();
            return ResponseEntity.created(uriOfNewResource).body(created);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> update(@AuthenticationPrincipal AuthorizedUser authorizedUser, @RequestBody User user, @PathVariable int id) {
        if (isAdmin(authorizedUser) || authorizedUser.getId() == id && user.getId() == id) {
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/by-email")
    public ResponseEntity<User> getByEmail(@AuthenticationPrincipal AuthorizedUser authorizedUser, @RequestBody String email) {
        if (isAdmin(authorizedUser)) {
            return new ResponseEntity<>(userRepository.getByEmail(email), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
