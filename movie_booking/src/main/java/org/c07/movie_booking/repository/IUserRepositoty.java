package org.c07.movie_booking.repository;

import org.c07.movie_booking.dto.UserResponse;
import org.c07.movie_booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IUserRepositoty extends JpaRepository<User, Long> {

    Optional<User> findByName(String name); //Tim kiem User co ton tai trong DB khong?
    Boolean existsByEmail(String email); //email da co trong DB chua
    @Query(nativeQuery = true,
            value = "select u.id,u.name,u.card_id,u.email,u.gender,u.phone_number,u.avatar,u.address, r.name as role" +
            " from user u join role r on r.id = u.role_id where u.email =?1")
    UserResponse findUserByEmail(String email);
}
