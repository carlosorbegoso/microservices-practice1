package com.skyblue.userservice.controllers;

import com.skyblue.userservice.entities.User;
import com.skyblue.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User userRequest) {
        User user = userService.saveUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


    @GetMapping()
    ResponseEntity<List<User>> getUserAll() {
        List<User> user = userService.getAllUsers();
        return ResponseEntity.ok(user);
    }
    Integer countRetries = 1;
    @GetMapping("/{userId}")
   // @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
    ResponseEntity<User> getUserId(@PathVariable String userId) {
        log.info("list only user: userController");
        log.info("count retries : {}", countRetries);
        countRetries++;
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
    public ResponseEntity<User> ratingHotelFallback(String userId,Exception exception){
        log.info("User rating ejecution for why service is inactive", exception.getMessage());
        User user = User.builder()
                .email("root1@gmail.com")
                .name("root")
                .information("This user created for whom rating is inactive")
                .userId("1234")
                .build();
        return  new ResponseEntity<>(user,HttpStatus.OK);
    }
}
