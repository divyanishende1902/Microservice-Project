package com.reviewservice.services;

import com.reviewservice.entities.Rating;

import java.util.List;

public interface RatingService {

    //create

    Rating create(Rating rating);


    //get all by userId
    List<Rating> getRatingsByUserId(String userId);

    //get All by Hotel
    List<Rating> getRatingByHotelId(String hotelId);


    //get all
    List<Rating> getAllRating();

    //get one
    Rating getSingleRating(String ratingId);

    //update
    Rating update(String ratingId,Rating rating);
}
