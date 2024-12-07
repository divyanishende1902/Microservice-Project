package com.hotel.services.impl;

import com.hotel.entities.Hotel;
import com.hotel.exception.ResourceNotFoundException;
import com.hotel.repository.HotelRepository;
import com.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    public HotelRepository hotelRepository;
    @Override
    public Hotel create(Hotel hotel) {
        String id = UUID.randomUUID().toString();
        hotel.setHotelId(id);  // Manually set the UUID here
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel get(String id) {
         return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("hotel not found with given id!!"));
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }
}
