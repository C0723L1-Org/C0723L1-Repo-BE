package org.c07.movie_booking.repository;

import org.c07.movie_booking.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findRoleByName(String name);


}
