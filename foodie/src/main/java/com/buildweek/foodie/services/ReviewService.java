package com.buildweek.foodie.services;

import com.buildweek.foodie.models.Reviews;

import java.util.List;

public interface ReviewService
{
    List<Reviews> findAll();

    Reviews findReviewById(long id);

    //    Restaurant findByName(String name);

    void delete(long id);

    Reviews save(Reviews reviews);

    Reviews update(Reviews reviews, long id);
}
