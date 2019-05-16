package ru.uziki.repository.Vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.uziki.model.Vote;

import java.util.List;

@Repository
public class VoteRepository {
    private static final Sort SORT_RESTAURANT = new Sort(Sort.Direction.ASC, "restaurant_id");

    @Autowired
    private CrudVoteRepository crudVoteRepository;

    public Vote save(Vote vote) {
        return crudVoteRepository.save(vote);
    }

    public List<Vote> getAll() {
        return crudVoteRepository.findAll(SORT_RESTAURANT);
    }
}
