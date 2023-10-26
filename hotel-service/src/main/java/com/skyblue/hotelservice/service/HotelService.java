package com.skyblue.hotelservice.service;

import com.skyblue.hotelservice.entity.Hotel;

import java.util.List;


public interface HotelService {
    Hotel create(Hotel hotel);
    List<Hotel> getAll();
    Hotel getId(String id);
}
