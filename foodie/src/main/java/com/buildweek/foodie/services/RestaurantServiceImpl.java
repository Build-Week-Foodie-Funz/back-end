package com.buildweek.foodie.services;

import com.buildweek.foodie.exceptions.ResourceNotFoundException;
import com.buildweek.foodie.models.RestPhotos;
import com.buildweek.foodie.models.Restaurant;
import com.buildweek.foodie.models.Reviews;
import com.buildweek.foodie.models.User;
import com.buildweek.foodie.repository.RestaurantRepository;
import com.buildweek.foodie.repository.ReviewsRepository;
import com.buildweek.foodie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "restaurantService")
public class RestaurantServiceImpl implements RestaurantService
{
    @Autowired
    RestaurantRepository restrepo;

    @Autowired
    UserRepository userrepos;

    @Autowired
    ReviewsRepository reviewrepo;



    @Override
    public User findAll()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            User u = userrepos.findByUsername(username);
            System.out.println(u.getUserroles());
            return u;
        } else {
            String username = principal.toString();
            return userrepos.findByUsername(username);
        }
    }

    @Override
    public Restaurant findRestaurantById(long id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());

        if (currentUser.getRestaurant().contains(restrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User can not delete restaurant"))))
        {
            return restrepo.findById(id)
                           .orElseThrow(() -> new ResourceNotFoundException("Restaurant id " + id + " not found!"));
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id) + " Not current user");
        }

    }

    @Override
    public void delete(long id) throws EntityNotFoundException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());

        if (currentUser.getRestaurant().contains(restrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User can not delete restaurant"))))
            {
            if (restrepo.findById(id).isPresent())
            {
                restrepo.deleteById(id);
            } else
            {
                throw new EntityNotFoundException(Long.toString(id));
            }
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id) + " Not current user");
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
        newRestaurant.setRecentvisit(restaurant.getRecentvisit());

//        for (Reviews r:restaurant.getReviews())
//        {
//            newRestaurant.getReviews().add(new Reviews( newRestaurant, r.getCuisinetype(), r.getMenuitemname(), r.getPhotomenu(), r.getItemprice(), r.getItemrating(), r.getShortreview()));
//        }
//
//        for (RestPhotos rp:restaurant.getRestphotos())
//        {
//            newRestaurant.getRestphotos().add(new RestPhotos(newRestaurant, rp.getPhoto()));
//        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            User u = userrepos.findByUsername(username);
            newRestaurant.getUser().add(u);


        } else {
            String username = principal.toString();
            return newRestaurant;
        }

        return restrepo.save(newRestaurant);

    }
    @Override
    public Reviews findReviewById(long id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());

        if (currentUser.getRestaurant().contains(restrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User can not delete restaurant"))))
        {
            return reviewrepo.findById(id)
                             .orElseThrow(() -> new ResourceNotFoundException("Review id " + id + " not found!"));
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id) + " Not current user");
        }

    }


    @Override
    public Restaurant update(Restaurant restaurant, long id)
    {
        Authentication authentication = SecurityContextHolder.getContext()
                                                             .getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());


        Restaurant currentRestaurant = restrepo.findById(id)
                                               .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        if (currentUser != null)
        {
            if (currentUser.getRestaurant()
                           .contains(currentRestaurant))
            {
                if (restaurant.getRestname() != null)
                {
                    currentRestaurant.setRestname(restaurant.getRestname());
                }

                if (restaurant.getResthours() != null)
                {
                    currentRestaurant.setResthours(restaurant.getResthours());
                }

                if (restaurant.getRestlocation() != null)
                {
                    currentRestaurant.setRestlocation(restaurant.getRestlocation());
                }

                if (restaurant.getRestrating() != null)
                {
                    currentRestaurant.setRestrating(restaurant.getRestrating());
                }

                if (restaurant.getRecentvisit() != null)
                {
                    currentRestaurant.setRecentvisit(restaurant.getRecentvisit());
                }

                if (restaurant.getReviews()
                              .size() > 0)
                {
                    for (Reviews r : restaurant.getReviews())
                    {
                        currentRestaurant.getReviews()
                                         .add(new Reviews(currentRestaurant, r.getCuisinetype(), r.getMenuitemname(), r.getPhotomenu(), r.getItemprice(), r.getItemrating(), r.getShortreview()));
                    }
                }

                if (restaurant.getRestphotos()
                              .size() > 0)
                {
                    for (RestPhotos rp : restaurant.getRestphotos())
                    {
                        currentRestaurant.getRestphotos()
                                         .add(new RestPhotos(currentRestaurant, rp.getPhoto()));
                    }
                }

                return restrepo.save(currentRestaurant);
            } else
            {
                throw new ResourceNotFoundException(Long.toString(id) + "Not Saved");
            }
        }
        else
        {
            throw new ResourceNotFoundException(authentication.getName());
        }
    }
}
