package com.reviewservice.services.impl;

import com.reviewservice.entities.Rating;
import com.reviewservice.exception.ResourceNotFoundException;
import com.reviewservice.repository.RatingRepository;
import com.reviewservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    RatingRepository ratingRepository;

    @Override
    public Rating create(Rating rating) {
        String id = UUID.randomUUID().toString();
        rating.setRatingId(id);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {

        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingsByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }



    @Override
    public Rating getSingleRating(String ratingId) {
        return ratingRepository.findById(ratingId).orElseThrow(()-> new ResourceNotFoundException("Rating not found with given id"));
    }


    @Override
    public Rating update( String ratingId,Rating rating) {
        Rating byId = ratingRepository.findById(ratingId).orElseThrow(()-> new ResourceNotFoundException("Rating not found with given id"));
        Rating rating1 = new Rating();
        rating1.setRating(byId.getRating());
        rating1.setFeedback(rating.getFeedback());

        return rating1;
    }
}
