package org.c07.movie_booking.repository.auth;

import org.c07.movie_booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IUserDetailRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
