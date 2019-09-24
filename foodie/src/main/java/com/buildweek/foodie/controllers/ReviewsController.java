package com.buildweek.foodie.controllers;

import com.buildweek.foodie.models.Restaurant;
import com.buildweek.foodie.models.Reviews;
import com.buildweek.foodie.services.RestaurantService;
import com.buildweek.foodie.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewsController
{
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private RestaurantService restaurantService;

    // GET Localhost:2019/restaurants/{restid}/reviews
    @GetMapping(value = "/restaurants/{restid}/reviews")
    public ResponseEntity<?> listReviewsOfRestaurant(@PathVariable long restid)
    {
        List<Reviews> restreviews = restaurantService.findRestaurantById(restid).getReviews();
        return new ResponseEntity<>(restreviews, HttpStatus.OK);
    }

    //GET localhost:2019/restaurants/{restid}/reviews/{reviewid}

}
