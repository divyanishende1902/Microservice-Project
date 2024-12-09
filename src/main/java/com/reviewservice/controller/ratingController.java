package com.reviewservice.controller;

import com.reviewservice.entities.Rating;
import com.reviewservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class ratingController {

    @Autowired
    public RatingService ratingService;

    //create
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }

    //update
    @PutMapping("/update/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable String ratingId ,@RequestBody Rating rating){
        return ResponseEntity.status(200).body(ratingService.update(ratingId, rating));
    }

    //get All
    @GetMapping
    public ResponseEntity<List<Rating>>getAll(){
        return ResponseEntity.ok(ratingService.getAllRating());
    }

    //get All ratings by user id

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>>getAllRatingsByUserId(@PathVariable String userId){
        return ResponseEntity.ok(ratingService.getRatingsByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>>getAllRatingsByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }

    //get single by id
    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getSingleRatingById(@PathVariable String ratingId ){
        return ResponseEntity.ok(ratingService.getSingleRating(ratingId));
    }
}
