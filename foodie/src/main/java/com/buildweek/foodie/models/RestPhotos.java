package com.buildweek.foodie.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "restphotos")
public class RestPhotos
{
    @ApiModelProperty(name = "photoid", value = "primary key for Photo", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long photoid;

    @ApiModelProperty(name = "photo", value = "Photo of Restaurant", required = true, example = "http://www.fnstatic.co.uk/images/content/recipe/three-egg-omelette.jpeg")
    @Column(nullable = false)
    private String photo;

    @ApiModelProperty(name = "restphotos", value = "Photos of Restaurant", example = "http://www.fnstatic.co.uk/images/content/recipe/three-egg-omelette.jpeg, http://www.fnstatic.co.uk/images/content/recipe/three-egg-omelette.jpeg")
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


