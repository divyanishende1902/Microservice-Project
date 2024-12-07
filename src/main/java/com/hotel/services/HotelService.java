package com.hotel.services;

import com.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {
    //create
    Hotel create (Hotel hotel);

    //get
    Hotel get(String id);

    //getall
    List<Hotel> getAll();

}
