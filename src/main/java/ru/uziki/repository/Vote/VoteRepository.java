package ru.uziki.repository.Vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.uziki.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VoteRepository {
    private static final Sort SORT_RESTAURANT = new Sort(Sort.Direction.ASC, "restaurant");

    @Autowired
    private CrudVoteRepository crudVoteRepository;

    public Vote get(Integer userId, LocalDate localDate) {
        return crudVoteRepository.get(userId, localDate);
    }

    public Vote save(Vote vote) {
        return crudVoteRepository.save(vote);
    }

    public List<Vote> getAllByRestaurant(Integer restId) {
        return crudVoteRepository.getAllByRestaurant(restId);
    }

    public List<Vote> getAll() {
        return crudVoteRepository.findAll(SORT_RESTAURANT);
    }
}
