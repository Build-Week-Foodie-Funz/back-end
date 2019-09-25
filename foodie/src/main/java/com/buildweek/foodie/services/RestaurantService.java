package com.buildweek.foodie.services;

import com.buildweek.foodie.models.Restaurant;
import com.buildweek.foodie.models.Reviews;
import com.buildweek.foodie.models.User;


public interface RestaurantService
{
    User findAll();

    Restaurant findRestaurantById(long id);

//     User findRestaurantById(long id);

    Reviews findReviewById(long id);

    void delete(long id);

    Restaurant save(Restaurant restaurant);

    Restaurant update(Restaurant restaurant, long id);
}
