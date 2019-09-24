package com.buildweek.foodie.services;

import com.buildweek.foodie.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public interface RestaurantService
{
    ArrayList<Restaurant> findAll();

    Restaurant findRestaurantById(long id);

//    Restaurant findByName(String name);

    void delete(long id);

    Restaurant save(Restaurant restaurant);

    Restaurant update(Restaurant restaurant, long id);
}
