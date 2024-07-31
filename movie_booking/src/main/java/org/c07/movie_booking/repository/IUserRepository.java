package org.c07.movie_booking.repository;

import org.c07.movie_booking.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
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
//    @Query(value= "select * from user where id = ?1 and status = false", nativeQuery = true)
    @Query(value = "SELECT u.* " +
            "FROM user u " +
            "JOIN role r ON u.role_id = r.id " +
            "WHERE r.name = 'employee' AND u.status = false AND u.id = ?1"
            , nativeQuery = true)
    Optional<User> findEmployeeById(Long id);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (code, name, card_id, email, gender, status, phone_number, avatar, address, password,birthday, role_id) VALUES (:code, :name, :cardId, :email, :gender, :status, :phoneNumber, :avatar, :address, :password,:birthday, :roleId)", nativeQuery = true)
    void insertUser(@Param("code") String code, @Param("name") String name, @Param("cardId") String cardId, @Param("email") String email, @Param("gender") Boolean gender, @Param("status") Boolean status, @Param("phoneNumber") String phoneNumber, @Param("avatar") String avatar, @Param("address") String address, @Param("password") String password, @Param("birthday") Date birthday, @Param("roleId") Long roleId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user SET code = :code, name = :name, card_id = :cardId, email = :email, gender = :gender, status = :status, phone_number = :phoneNumber, avatar = :avatar, address = :address, password = :password,birthday=:birthday, role_id = :roleId WHERE id = :id", nativeQuery = true)
    void updateUser(@Param("id") Long id, @Param("code") String code, @Param("name") String name, @Param("cardId") String cardId, @Param("email") String email, @Param("gender") Boolean gender, @Param("status") Boolean status, @Param("phoneNumber") String phoneNumber, @Param("avatar") String avatar, @Param("address") String address, @Param("password") String password,@Param("birthday")Date birthday, @Param("roleId") Long roleId);
    @Query(value = "SELECT email FROM user", nativeQuery = true)
    List<String> findAllEmails();
}
