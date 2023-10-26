package com.skyblue.userservice.service.imp;

import com.skyblue.userservice.entities.Hotel;
import com.skyblue.userservice.entities.Qualification;
import com.skyblue.userservice.entities.User;
import com.skyblue.userservice.exceptions.ResourceNotFoundException;
import com.skyblue.userservice.external.services.HotelService;
import com.skyblue.userservice.repository.UserRepository;
import com.skyblue.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private final HotelService hotelService;

    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate, HotelService hotelService) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.hotelService= hotelService;
    }

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User " + userId + "not found in repository"));

        Qualification[] qualificationOfUsers = this.restTemplate.getForObject("http://QUALIFICATION-SERVICE/qualifications/users/" + user.getUserId(), Qualification[].class);

        List<Qualification> qualifications = Arrays.stream(Objects.requireNonNull(qualificationOfUsers)).toList();

        List<Qualification> qualificationList = qualifications.stream().map(qualification -> {
            System.out.println(qualification.getHotelId());
            //ResponseEntity<Hotel> foEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + qualification.getHotelId(), Hotel.class);

            Hotel hotel = this.hotelService.getHotel(qualification.getHotelId());
            //logger.info("Hotel: Response with code of state " + foEntity.getStatusCode());
            qualification.setHotel(hotel);
            return qualification;
        }).toList();
        logger.info("{}", (Object) qualificationOfUsers);
        user.setQualificationList(qualificationList);
        return user;
    }
}
