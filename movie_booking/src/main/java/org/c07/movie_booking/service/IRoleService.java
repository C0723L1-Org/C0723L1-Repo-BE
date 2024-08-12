package org.c07.movie_booking.service;

import org.c07.movie_booking.model.Role;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();
}
