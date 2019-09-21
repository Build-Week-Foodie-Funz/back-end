package com.buildweek.foodie.repository;

import com.buildweek.foodie.models.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long>
{
}
