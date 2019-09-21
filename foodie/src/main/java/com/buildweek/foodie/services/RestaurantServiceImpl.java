package com.buildweek.foodie.services;

import com.buildweek.foodie.exceptions.ResourceNotFoundException;
import com.buildweek.foodie.models.RestPhotos;
import com.buildweek.foodie.models.Restaurant;
import com.buildweek.foodie.models.Reviews;
import com.buildweek.foodie.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "restaurantService")
public class RestaurantServiceImpl implements RestaurantService
{
    @Autowired
    RestaurantRepository restrepo;

    @Override
    public List<Restaurant> findAll()
    {
        List<Restaurant> restaurantsList = new ArrayList<>();
        restrepo.findAll()
                 .iterator()
                 .forEachRemaining(restaurantsList::add);
        return restaurantsList;
    }

    @Override
    public Restaurant findRestaurantById(long id)
    {
        return restrepo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Restaurant id " + id + " not found!"));
    }

    @Override
    public void delete(long id)
    {
        if (restrepo.findById(id).isPresent())
        {
            restrepo.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }

    @Override
    public Restaurant save(Restaurant restaurant)
    {
        Restaurant newRestaurant = new Restaurant();
        newRestaurant.setRestname(restaurant.getRestname());
        newRestaurant.setResthours(restaurant.getResthours());
        newRestaurant.setRestlocation(restaurant.getRestlocation());
        newRestaurant.setRestrating(restaurant.getRestrating());

        for (Reviews r:restaurant.getReviews())
        {
            newRestaurant.getReviews().add(new Reviews(r.getCuisinetype(), r.getMenuitemname(), r.getPhotomenu(), r.getItemprice(), r.getItemrating(), r.getShortreview(), newRestaurant));
        }

        for (RestPhotos rp:restaurant.getRestphotos())
        {
            newRestaurant.getRestphotos().add(new RestPhotos(rp.getPhoto(), newRestaurant));
        }

        return restrepo.save(newRestaurant);
    }

    @Override
    public Restaurant update(Restaurant restaurant, long id)
    {
        Restaurant currentRestaurant = restrepo.findById(id)
                                               .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        if(restaurant.getRestname() != null)
        {
            currentRestaurant.setRestname(restaurant.getRestname());
        }

        if(restaurant.getResthours() != null)
        {
            currentRestaurant.setResthours(restaurant.getResthours());
        }

        if(restaurant.getRestlocation() != null)
        {
            currentRestaurant.setRestlocation(restaurant.getRestlocation());
        }

        if(restaurant.getRestrating() != null)
        {
            currentRestaurant.setRestrating(restaurant.getRestrating());
        }

        if(restaurant.getReviews().size() > 0)
        {
            for (Reviews r:restaurant.getReviews())
            {
                currentRestaurant.getReviews().add(new Reviews(r.getCuisinetype(), r.getMenuitemname(), r.getPhotomenu(), r.getItemprice(), r.getItemrating(), r.getShortreview(), currentRestaurant));
            }
        }

        if(restaurant.getRestphotos().size() > 0)
        {
            for (RestPhotos rp:restaurant.getRestphotos())
            {
                currentRestaurant.getRestphotos().add(new RestPhotos(rp.getPhoto(), currentRestaurant));
            }
        }

        return restrepo.save(currentRestaurant);
    }
}
