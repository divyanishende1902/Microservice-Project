package com.mcr.user.service.external.services;

import com.mcr.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@Service
@FeignClient(name = "REVIEWSERVICE", url = "http://localhost:8083")
public interface RatingService {

     //GET
    @GetMapping("/ratings/{ratingId}")
    public Rating getRating(@PathVariable("ratingId") String ratingId);

    //POST
    @PostMapping("/ratings")
    public Rating createRating(Rating rating);

    //PUT
    @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable("ratingId") String ratingId, Rating rating);


    @DeleteMapping("/ratings/{ratingId}")
    public Rating deleteRating(@PathVariable String ratingId);

}
