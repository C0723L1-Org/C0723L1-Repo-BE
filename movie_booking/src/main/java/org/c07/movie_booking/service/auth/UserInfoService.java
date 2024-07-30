package org.c07.movie_booking.service.auth;

import org.c07.movie_booking.model.User;
import org.c07.movie_booking.repository.auth.IUserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private IUserInfoRepository userInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail =userInfoRepository.getUsersByEmail(username);
        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new).orElseThrow(()-> new UsernameNotFoundException("Email not found "+username));
    }

}
