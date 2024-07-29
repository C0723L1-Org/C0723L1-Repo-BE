package org.c07.movie_booking.service.implement;

import org.c07.movie_booking.model.Role;
import org.c07.movie_booking.repository.IRoleRepository;
import org.c07.movie_booking.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository iRoleRepository;

    @Override
    public List<Role> findAll() {
        return iRoleRepository.findAll();
    }
}
