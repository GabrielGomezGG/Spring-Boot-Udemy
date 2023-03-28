package com.auth.jwt.service;

import com.auth.jwt.dto.AuthUserDto;
import com.auth.jwt.dto.TokenDto;
import com.auth.jwt.entity.AuthUser;
import com.auth.jwt.repository.AuthUserRepository;
import com.auth.jwt.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private JwtProvider jwtProvider;

    public AuthUser save(AuthUserDto authUserDto){
        var user = authUserRepository.findByUserName(authUserDto.getUserName());
        if (user.isPresent()){
            return null;
        }
        var password = passwordEncoder.encode(authUserDto.getPassword());
        var authUser = AuthUser.builder()
                .userName(authUserDto.getUserName())
                .password(password)
                .build();
        return authUserRepository.save(authUser);
    }

    public TokenDto login(AuthUserDto authUserDto){
        var user = authUserRepository.findByUserName(authUserDto.getUserName());
        if (user.isEmpty()){
            return null;
        }
        if(passwordEncoder.matches(authUserDto.getPassword(),user.get().getPassword())){
            return new TokenDto(jwtProvider.createToken(user.get()));
        }
        return null;
    }

    public TokenDto validate(String token){
        if(!jwtProvider.validate(token)){
            return null;
        }
        var userName = jwtProvider.getUserNameFromToken(token);
        if(authUserRepository.findByUserName(userName).isEmpty()){
            return null;
        }
        return new TokenDto(token);
    }
}
