package com.auth.jwt.controller;

import com.auth.jwt.dto.LoginDto;
import com.auth.jwt.dto.TokenDto;
import com.auth.jwt.entity.UserEntity;
import com.auth.jwt.security.JwtUtil;
import com.auth.jwt.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtUtil jwtUtil;
    private  final AuthenticationManager authenticationManager;
    private final AuthService authService;


    public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto){
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.username(),loginDto.password());
        Authentication authentication = this.authenticationManager.authenticate(login);

        System.out.println(authentication.isAuthenticated());
        System.out.println(authentication.getPrincipal());

        String  jwt = this.jwtUtil.create(loginDto.username());
        return  ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,jwt).build();
    }
    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token) {

        TokenDto tokenDto = this.authService.validate(token);
        return  ResponseEntity.ok(tokenDto);
    }
    @PostMapping("/create")
    public ResponseEntity<UserEntity> create(@RequestBody LoginDto loginDto) {
       UserEntity userEntity = null;
       if(userEntity == null){
           return  ResponseEntity.badRequest().build();
       }

        String  jwt = this.jwtUtil.create(loginDto.username());
        return  ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,jwt).build();
    }
}
