package ru.uziki.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.uziki.AuthorizedUser;
import ru.uziki.model.Vote;
import ru.uziki.repository.Restaurant.RestaurantRepository;
import ru.uziki.repository.User.UserRepository;
import ru.uziki.repository.Vote.VoteRepository;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    static final String REST_URL = "/votes";

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Vote> getAll() {
        return voteRepository.getAll();
    }

    @GetMapping("/{restId}")
    public List<Vote> getAllByRestaurant(@PathVariable int restId) {
        return voteRepository.getAllByRestaurant(restId);
    }

    @PostMapping(value = "/{restId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> save(@AuthenticationPrincipal AuthorizedUser authorizedUser, @PathVariable int restId) {
        LocalDate now = LocalDate.now();
        Vote checkVote = voteRepository.get(authorizedUser.getId(), now);
        if (checkVote == null) {
            Vote vote = new Vote();
            vote.setRestaurant(restaurantRepository.get(restId));
            vote.setUser(userRepository.get(authorizedUser.getId()));
            vote.setDate(now);
            return new ResponseEntity<>(voteRepository.save(vote), HttpStatus.OK);
        } else if (LocalTime.now().isAfter(LocalTime.parse("11:00")) ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            checkVote.setRestaurant(restaurantRepository.get(restId));
            return new ResponseEntity<>(voteRepository.save(checkVote), HttpStatus.OK);
        }
    }
}
