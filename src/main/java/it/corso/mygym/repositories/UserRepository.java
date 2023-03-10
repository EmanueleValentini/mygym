package it.corso.mygym.repositories;

import it.corso.mygym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User,Long> {
    List<User> findByActiveFlagTrue();

    // TODO: find by activeFlag=true AND have an active subscription --> @query
    @Query("SELECT u FROM User u, Subscription s WHERE u.activeFlag = true AND u.id = s.user.id AND" +
            " s.endDate > current_date GROUP BY u.id")
    List<User> findByActiveFlagTrueAndSubscriptionActive();

    User findByEmail(String email);
}
