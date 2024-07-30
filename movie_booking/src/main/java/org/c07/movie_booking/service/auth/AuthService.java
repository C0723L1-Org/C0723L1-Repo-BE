package org.c07.movie_booking.service.auth;

import lombok.RequiredArgsConstructor;
import org.c07.movie_booking.model.auth_entity.AuthRequest;
import org.c07.movie_booking.model.auth_entity.AuthenticationResponse;
import org.c07.movie_booking.repository.auth.IUserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    JwtService jwtService;
    @Autowired
    IUserInfoRepository userInfoRepository;
    public AuthenticationResponse authenticate(AuthRequest authRequest){
        var user = userInfoRepository.getUsersByEmail(authRequest.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user.getEmail());
        return new AuthenticationResponse(jwtToken,user);
    }
}
