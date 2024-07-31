package org.c07.movie_booking.repository;

import org.c07.movie_booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepositoty extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
