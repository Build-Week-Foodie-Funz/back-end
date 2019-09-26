package com.buildweek.foodie.controllers;

import com.buildweek.foodie.models.ErrorDetail;
import com.buildweek.foodie.models.Restaurant;
import com.buildweek.foodie.models.Reviews;
import com.buildweek.foodie.services.RestaurantService;
import com.buildweek.foodie.services.ReviewService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/user/")
public class ReviewsController
{
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private RestaurantService restaurantService;

    // GET Localhost:2019/restaurants/{restid}/reviews
    @ApiOperation(value = "Return Reviews by restaurant id", response = Reviews.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Reviews Found By Restaurant ID", response = Reviews.class),
            @ApiResponse(code = 404, message = "Reviews Not Found By Restaurant ID", response = ErrorDetail.class)})
    @GetMapping(value = "/restaurants/{restid}/reviews")
    public ResponseEntity<?> listReviewsOfRestaurant(@PathVariable long restid)
    {
        List<Reviews> restreviews = restaurantService.findRestaurantById(restid).getReviews();
        return new ResponseEntity<>(restreviews, HttpStatus.OK);
    }

    //GET localhost:2019/reviews/{reviewid}
    @ApiOperation(value = "Return Reviews by id", response = Reviews.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Reviews Found By ID", response = Reviews.class),
            @ApiResponse(code = 404, message = "Reviews Not Found By  ID", response = ErrorDetail.class)})
    @GetMapping(value = "/reviews/{reviewid}")
    public ResponseEntity<?> listReviewOfRestaurantById( @PathVariable long reviewid)
    {
        Reviews r =reviewService.findReviewById(reviewid);

        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    //DELETE localhost:2019/reviews/{reviewid}
    @ApiOperation(value = "Delete by Review Id", response = Reviews.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Review Deleted By Id", response = void.class),
            @ApiResponse(code = 500, message = "Review Not Deleted By Id", response = ErrorDetail.class)})
    @DeleteMapping(value = "/reviews/{reviewid}")
    public ResponseEntity<?> deleteReview(@PathVariable long reviewid)
    {
        reviewService.delete(reviewid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //POST localhost:2019/restaurant/{restid}/reviews/
    @ApiOperation(value = "Add Review to Restaurant", response = Reviews.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Review Added to Restaurant", response = void.class),
            @ApiResponse(code = 500, message = "Review Not Added to Restaurant", response = ErrorDetail.class)})
    @PostMapping(value = "/restaurant/{restid}/reviews", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewReview(@Valid
                                              @RequestBody Reviews newReviews, @PathVariable long restid) throws URISyntaxException
    {
        newReviews.setRestaurant(restaurantService.findRestaurantById(restid));
        reviewService.save(newReviews);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newStudentURI = ServletUriComponentsBuilder.fromCurrentRequest()
                                                       .path("/{reviewid}")
                                                       .buildAndExpand(newReviews.getReviewid())
                                                       .toUri();
        responseHeaders.setLocation(newStudentURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //PUT localhost:2019/restaurant/{restid}/reviews/{reviewid}
    @ApiOperation(value = "Update Review by Id", response = Reviews.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Review Updated", response = void.class),
            @ApiResponse(code = 500, message = "Review Not Updated", response = ErrorDetail.class)})
    @PutMapping(value = "/restaurant/{restid}/reviews/{reviewid}")
    public ResponseEntity<?> updateReview(@RequestBody Reviews updateReviews, @PathVariable long restid, @PathVariable long reviewid)
    {
//        updateReviews.setRestaurant(restaurantService.findRestaurantById(restid));
        ////        updateReviews=reviewService.findReviewById(reviewid);
//        restaurantService.update(updateRestaurant, restid);

        reviewService.update(updateReviews, reviewid, restid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
