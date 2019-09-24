package com.buildweek.foodie.controllers;


import com.buildweek.foodie.models.Restaurant;
import com.buildweek.foodie.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
public class RestaurantController
{
    @Autowired
    private RestaurantService restaurantService;

    // GET localhost:2019/restaurants
    @GetMapping(value = "restaurants", produces = {"application/json"})
    public ResponseEntity<?> listAllRestaurants()
    {

        ArrayList<Restaurant> allRestaurants = restaurantService.findAll();
        return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
    }

    //GET localhost:2019/restaurant/{restid}
    @GetMapping(value = "/restaurant/{restid}", produces = {"application/json"})
    public ResponseEntity<?> getRestaurantById(@PathVariable long restid)
    {
        Restaurant r = restaurantService.findRestaurantById(restid);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    //DELETE localhost:2019/restaurant/{restid}
    @DeleteMapping(value = "restaurant/{restid}")
    public ResponseEntity<?> deleteRestaurantById(@PathVariable long restid)
    {
        restaurantService.delete(restid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //PUT localhost:2019/restaurant/{restid}
    @PutMapping(value = "/restaurant/{restid}")
    public ResponseEntity<?> updateRestaurant(@RequestBody Restaurant updateRestaurant, @PathVariable long restid)
    {
        restaurantService.update(updateRestaurant, restid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //POST localhost:2019/restaurants
    @PostMapping(value = "/restaurant", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewRestaurant(@Valid
                                              @RequestBody Restaurant newRestaurant) throws URISyntaxException
    {
        newRestaurant = restaurantService.save(newRestaurant);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newStudentURI = ServletUriComponentsBuilder.fromCurrentRequest()
                                                       .path("/{restid}")
                                                       .buildAndExpand(newRestaurant.getRestid())
                                                       .toUri();
        responseHeaders.setLocation(newStudentURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}



