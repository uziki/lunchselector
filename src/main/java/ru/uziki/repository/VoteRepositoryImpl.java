package ru.uziki.repository;

import ru.uziki.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
//TODO
@Repository
public class VoteRepositoryImpl{
    @Autowired
    private CrudVoteRepository crudVoteRepository;

    public Vote save(Vote vote) {
        if (!vote.isNew()) {

        }
        return null;
    }

    public Vote get(int id) {
        return null;
    }

    public List<Vote> getAll(int restaurantId) {
        return crudVoteRepository.getAll(restaurantId);
    }


}
