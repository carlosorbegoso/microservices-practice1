package com.auth.jwt.service;

import com.auth.jwt.dto.TokenDto;
import com.auth.jwt.entity.UserEntity;
import com.auth.jwt.repository.AuthUserRepository;
import com.auth.jwt.security.JwtUtil;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    private final AuthUserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthService(AuthUserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserEntity userEntity = this.userRepository.findById(username)
               .orElseThrow(()-> new UsernameNotFoundException("User " + username+ "not found"));
        System.out.println(userEntity);
        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .build();
    }
    public TokenDto validate(String token){

        if (!this.jwtUtil.isValid(token)) {
            return null;
        }

        String user = this.jwtUtil.getUserName(token);
        if(!userRepository.findByUsername(user).isPresent()){
            return  null;
        }

        return new TokenDto(token);
    }

}
