package com.skyblue.hotelservice.controller;

import com.skyblue.hotelservice.entity.Hotel;
import com.skyblue.hotelservice.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }
    @PostMapping
    public ResponseEntity<Hotel> saveHotel( @RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.hotelService.create(hotel));
    }
    @GetMapping
    public ResponseEntity<List<Hotel>> HotelList(){
        return  ResponseEntity.status(HttpStatus.OK).body(this.hotelService.getAll());
    }
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){
        return  ResponseEntity.status(HttpStatus.OK).body(this.hotelService.getId(hotelId));
    }
}
