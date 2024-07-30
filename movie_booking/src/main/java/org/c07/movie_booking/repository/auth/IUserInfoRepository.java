package org.c07.movie_booking.repository.auth;

import org.c07.movie_booking.model.User;
import org.c07.movie_booking.model.auth_entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserInfoRepository extends JpaRepository<User,Long> {
    @Query(nativeQuery = true,value = "select * from user s where email =%?1%")
    Optional<User> getUsersByEmail(String email);
}
