package com.hotel.controller;

import com.hotel.entities.Hotel;
import com.hotel.repository.HotelRepository;
import com.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;



    //create
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel hotel1 = hotelService.create(hotel);
        return new ResponseEntity<>(hotel1, HttpStatus.CREATED);
    }
    //get single
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getSingleHtel(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
    }

    //get all
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotel(){
        return ResponseEntity.ok(hotelService.getAll());
    }
}
