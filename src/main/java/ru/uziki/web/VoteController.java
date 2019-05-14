package ru.uziki.web;

import ru.uziki.model.Restaurant;
import ru.uziki.model.User;
import ru.uziki.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.uziki.repository.CrudVoteRepository;

import java.util.List;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    static final String REST_URL = "/votes";

    @Autowired
    protected CrudVoteRepository crudVoteRepository;


    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        return crudVoteRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Vote> getAll() {
        return crudVoteRepository.getAll(100002);
    }
}
