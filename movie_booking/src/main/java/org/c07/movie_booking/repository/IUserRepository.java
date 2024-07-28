package org.c07.movie_booking.repository;

import org.c07.movie_booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    @Query(nativeQuery = true, value = "select * from user where email =?1")
    Optional<User> findUserByEmail(String email);
}
