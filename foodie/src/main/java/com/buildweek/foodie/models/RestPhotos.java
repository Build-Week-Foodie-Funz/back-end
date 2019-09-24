package com.buildweek.foodie.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "restphotos")
public class RestPhotos
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long photoid;

    @Column(nullable = false)
    private String photo;

    @ManyToOne
    @JoinColumn(name = "restid")
    @JsonIgnoreProperties("restphotos")
    private Restaurant restaurant;

    public RestPhotos()
    {
    }

    public RestPhotos(Restaurant restaurant, String photo)
    {
        this.photo = photo;
        this.restaurant = restaurant;
    }

    public RestPhotos(String photo)
    {
        this.photo = photo;
    }

    public long getPhotoid()
    {
        return photoid;
    }

    public void setPhotoid(long photoid)
    {
        this.photoid = photoid;
    }

    public String getPhoto()
    {
        return photo;
    }

    public void setPhoto(String photo)
    {
        this.photo = photo;
    }

    public Restaurant getRestaurant()
    {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant)
    {
        this.restaurant = restaurant;
    }
}


