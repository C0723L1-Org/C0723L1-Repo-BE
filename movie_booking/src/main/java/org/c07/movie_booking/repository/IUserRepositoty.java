package org.c07.movie_booking.repository;

import org.c07.movie_booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUserRepositoty extends JpaRepository<User, Long> {

    Optional<User> findByName(String name); //Tìm kiếm User có tồn tại trong DB không?

    Boolean existsByEmail(String email); // Kiểm tra email đã có trong DB chưa

    @Query(nativeQuery = true, value = "select * from user where email =?1")
    Optional<User> findUserByEmail(String email);

    Boolean existsByCardId(String cardId); // Sửa tên phương thức theo quy ước của Spring Data JPA
    Boolean existsByPhoneNumber(String phoneNumber);
}
