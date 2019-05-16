package ru.uziki.repository.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.uziki.model.User;

import java.util.List;

@Repository
public class UserRepository {
    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

    @Autowired
    private CrudUserRepository crudUserRepository;


    public User save(User user) {
        return crudUserRepository.save(user);
    }

    public boolean delete(int id) {
        return crudUserRepository.delete(id) != 0;
    }

    public User get(int id) {
        return crudUserRepository.findById(id).orElse(null);
    }

    public User getByEmail(String email) {
        return crudUserRepository.getByEmail(email);
    }

    public List<User> getAll() {
        return crudUserRepository.findAll(SORT_NAME_EMAIL);
    }
}
