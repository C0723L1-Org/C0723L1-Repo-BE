package org.c07.movie_booking.service;

import org.c07.movie_booking.model.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<Role> findAll();
    Optional<Role> findByName(String roleName);
}
