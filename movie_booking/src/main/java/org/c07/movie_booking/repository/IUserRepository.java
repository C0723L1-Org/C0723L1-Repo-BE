package org.c07.movie_booking.repository;

import jakarta.transaction.Transactional;
import org.c07.movie_booking.dto.UserResponse;
import org.c07.movie_booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name); //Tìm kiếm User có tồn tại trong DB không?

    Boolean existsByEmail(String email); // Kiểm tra email đã có trong DB chưa

    @Query(nativeQuery = true, value = "select * from user where email =?1")
    Optional<User> findByEmail(String email);

    Boolean existsByCardId(String cardId);
    Boolean existsByPhoneNumber(String phoneNumber);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.name = :name, u.cardId = :cardId, u.email = :email, u.phoneNumber = :phoneNumber, u.avatar = :avatar, u.address = :address, u.password = :password, u.gender = :gender WHERE u.id = :id")
    void updateUser(@Param("id") Long id,
                    @Param("name") String name,
                    @Param("cardId") String cardId,
                    @Param("email") String email,
                    @Param("phoneNumber") String phoneNumber,
                    @Param("avatar") String avatar,
                    @Param("address") String address,
                    @Param("password") String password,
                    @Param("gender") Boolean gender);

    @Query(nativeQuery = true,
            value = "select u.id,u.name,u.card_id,u.email,u.gender,u.phone_number,u.avatar,u.address, r.name as role" +
            " from user u join role r on r.id = u.role_id where u.email =?1")
    UserResponse findUserByEmail(String email);
}
