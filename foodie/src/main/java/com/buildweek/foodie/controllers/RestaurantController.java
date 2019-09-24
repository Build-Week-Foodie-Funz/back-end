package com.buildweek.foodie.controllers;


import com.buildweek.foodie.models.ErrorDetail;
import com.buildweek.foodie.models.Restaurant;
import com.buildweek.foodie.services.RestaurantService;
import io.swagger.annotations.*;
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
@RequestMapping("/user/")
public class RestaurantController
{
    @Autowired
    private RestaurantService restaurantService;

    // GET localhost:2019/restaurants
    @ApiOperation(value = "Return All Restaurants", response = Restaurant.class, responseContainer = "List")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve(0..N)"),
                               @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Results of records per page"),
                               @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "All Restaurant Found", response = Restaurant.class),
            @ApiResponse(code = 404, message = "Restaurant Not Found", response = ErrorDetail.class)})
    @GetMapping(value = "restaurants", produces = {"application/json"})
    public ResponseEntity<?> listAllRestaurants()
    {

        ArrayList<Restaurant> allRestaurants = restaurantService.findAll();
        return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
    }

    //GET localhost:2019/restaurant/{restid}
    @ApiOperation(value = "Return Restaurant by id", response = Restaurant.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Restaurant Found By ID", response = Restaurant.class),
            @ApiResponse(code = 404, message = "Restaurant Not Found By ID", response = ErrorDetail.class)})
    @GetMapping(value = "/restaurant/{restid}", produces = {"application/json"})
    public ResponseEntity<?> getRestaurantById(@PathVariable long restid)
    {
        Restaurant r = restaurantService.findRestaurantById(restid);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    //DELETE localhost:2019/restaurant/{restid}
    @ApiOperation(value = "Delete by Restaurant Id", response = Restaurant.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Restaurant Deleted By Id", response = void.class),
            @ApiResponse(code = 500, message = "Restaurant Not Deleted By Id", response = ErrorDetail.class)})
    @DeleteMapping(value = "restaurant/{restid}")
    public ResponseEntity<?> deleteRestaurantById(@PathVariable long restid)
    {
        restaurantService.delete(restid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //PUT localhost:2019/restaurant/{restid}
    @ApiOperation(value = "Update restaurant by Id", response = Restaurant.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Restaurant Updated", response = void.class),
            @ApiResponse(code = 500, message = "Restaurant Not Updated", response = ErrorDetail.class)})
    @PutMapping(value = "/restaurant/{restid}")
    public ResponseEntity<?> updateRestaurant(@RequestBody Restaurant updateRestaurant, @PathVariable long restid)
    {
        restaurantService.update(updateRestaurant, restid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //POST localhost:2019/restaurants
    @ApiOperation(value = "Add Restaurant", response = Restaurant.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Restaurant Added ", response = void.class),
            @ApiResponse(code = 500, message = "Restaurant Not Added ", response = ErrorDetail.class)})
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



