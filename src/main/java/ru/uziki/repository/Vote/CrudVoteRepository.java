package ru.uziki.repository.Vote;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import ru.uziki.model.Vote;

import java.time.LocalDate;
import java.util.List;
@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    @Override
    @Transactional
    Vote save(Vote vote);

    @Query("SELECT v FROM Vote v where v.restaurant.id=:restId ORDER BY v.date DESC")
    List<Vote> getAllByRestaurant(@Param("restId") Integer restId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.date=:date")
    Vote get(@Param("userId") Integer userId, @Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);


    @Override
    List<Vote> findAll(Sort sort);
}
