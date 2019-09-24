package com.buildweek.foodie.services;

import com.buildweek.foodie.exceptions.ResourceNotFoundException;
import com.buildweek.foodie.models.Restaurant;
import com.buildweek.foodie.models.Reviews;
import com.buildweek.foodie.models.User;
import com.buildweek.foodie.repository.RestaurantRepository;
import com.buildweek.foodie.repository.ReviewsRepository;
import com.buildweek.foodie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "reviewService")
public class ReviewServiceImpl implements ReviewService
{
    @Autowired
    ReviewsRepository reviewrepo;

    @Autowired
    RestaurantRepository restrepo;

    @Autowired
    UserRepository userrepos;



    @Override
    public List<Reviews> findAll()
    {
        List<Reviews> reviewsList = new ArrayList<>();
        reviewrepo.findAll()
                .iterator()
                .forEachRemaining(reviewsList::add);
        return reviewsList;
    }

    @Override
    public Reviews findReviewById(long id)
    {
        return reviewrepo.findById(id)
                       .orElseThrow(() -> new ResourceNotFoundException("Review id " + id + " not found!"));
    }

    @Override
    public void delete(long id)
    {
        if (reviewrepo.findById(id).isPresent())
        {
            reviewrepo.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }

    @Override
    public Reviews save(Reviews reviews)
    {
        Reviews newReview = new Reviews();
        newReview.setCuisinetype(reviews.getCuisinetype());
        newReview.setMenuitemname(reviews.getMenuitemname());
        newReview.setPhotomenu(reviews.getPhotomenu());
        newReview.setItemprice(reviews.getItemprice());
        newReview.setShortreview(reviews.getShortreview());
        newReview.setRestaurant(reviews.getRestaurant());
        newReview.setItemrating(reviews.getItemrating());

        return reviewrepo.save(newReview);
    }

    @Override
    public Reviews update(Reviews reviews, long id, long restid)
    {
        Restaurant currentRestaurant = restrepo.findById(restid)
                                               .orElseThrow(() -> new EntityNotFoundException(Long.toString(restid)));


        Reviews currentReview = reviewrepo.findById(id)
                                         .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if(reviews.getMenuitemname() != null)
        {
            currentReview.setMenuitemname(reviews.getMenuitemname());
        }

        if(reviews.getCuisinetype() != null)
        {
            currentReview.setCuisinetype(reviews.getCuisinetype());
        }

        if(reviews.getItemrating() != null)
        {
            currentReview.setItemrating(reviews.getItemrating());
        }

        if(reviews.getPhotomenu() != null)
        {
            currentReview.setPhotomenu(reviews.getPhotomenu());
        }

        if(reviews.getItemprice() != null)
        {
            currentReview.setItemprice(reviews.getItemprice());
        }

        if(reviews.getShortreview() != null)
        {
            currentReview.setShortreview(reviews.getShortreview());
        }

        if(reviews.getRestaurant() != null)
        {
            currentReview.setRestaurant(currentRestaurant);
        }


        return reviewrepo.save(currentReview);
    }

//    @Transactional
//    @Override
//    public void saveReviewWithRestaurant(long restid, long reviewid)
//    {
//        reviewrepo.insertRestaurantToReview(restid, reviewid);
//    }
}
