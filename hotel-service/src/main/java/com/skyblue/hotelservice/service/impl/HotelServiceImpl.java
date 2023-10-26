package com.skyblue.hotelservice.service.impl;

import com.skyblue.hotelservice.entity.Hotel;
import com.skyblue.hotelservice.exceptions.ResourceNotFoundException;
import com.skyblue.hotelservice.repository.HotelRepository;
import com.skyblue.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return this.hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return this.hotelRepository.findAll();
    }

    @Override
    public Hotel getId(String id) {
        return this.hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with id " + id));
    }
}
