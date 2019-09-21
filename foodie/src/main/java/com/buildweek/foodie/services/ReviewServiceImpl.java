package com.buildweek.foodie.services;

import com.buildweek.foodie.exceptions.ResourceNotFoundException;
import com.buildweek.foodie.models.Reviews;
import com.buildweek.foodie.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "reviewService")
public class ReviewServiceImpl implements ReviewService
{
    @Autowired
    ReviewsRepository reviewrepo;

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

        return reviewrepo.save(newReview);
    }

    @Override
    public Reviews update(Reviews reviews, long id)
    {
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
            currentReview.setRestaurant(reviews.getRestaurant());
        }

        return reviewrepo.save(currentReview);
    }
}
