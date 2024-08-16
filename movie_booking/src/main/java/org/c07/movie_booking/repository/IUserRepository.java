package org.c07.movie_booking.repository;

import org.c07.movie_booking.dto.UserResponse;
import org.c07.movie_booking.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


@Repository
public interface IUserRepository extends JpaRepository<User, Long> {


    //BuiTheThien
    // Show List and Search Employee
    @Query(value = "SELECT u.* " +
            "FROM user u " +
            "JOIN role r ON u.role_id = r.id " +
            "WHERE r.name = 'employee' AND u.status = false " +
            "AND (u.name LIKE %:valueSearch% OR u.code LIKE %:valueSearch%)",
            countQuery = "SELECT COUNT(*) " +
                    "FROM user u " +
                    "JOIN role r ON u.role_id = r.id " +
                    "WHERE r.name = 'employee' AND u.status = false " +
                    "AND (u.name LIKE %:valueSearch% OR u.code LIKE %:valueSearch%)", nativeQuery = true)
      Page<User> SearchEmployees(@Param("valueSearch") String valueSearch, Pageable pageable);
    // Remove Employee
    @Modifying
    @Transactional
    @Query(value = "update user set status = 1 where id = ?1", nativeQuery = true)
    void deleteEmployeeByQuery( Long id);

    // Tìm employee theo id
    @Query(value = "SELECT u.* " +
            "FROM user u " +
            "JOIN role r ON u.role_id = r.id " +
            "WHERE r.name = 'employee' AND u.status = false AND u.id = ?1"
            , nativeQuery = true)
    Optional<User> findEmployeeById(Long id);

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



