package com.buildweek.foodie.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="restaurants")
public class Restaurant
{
    @ApiModelProperty(name = "restid", value = "primary key for Book", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long restid;

    @ApiModelProperty(name = "restname", value = "Restaurant's Name", required = true, example = "Burger King")
    @Column(nullable = false,
            unique = true)
    private String restname;

    @ApiModelProperty(name = "restlocation", value = "Restaurant's Location", required = true, example = "Philadelphia, PA")
    @Column(nullable = false)
    private String restlocation;

    @ApiModelProperty(name = "resthours", value = "Restaurant's Hours", required = true, example = "8:00AM - 9:00PM")
    @Column(nullable = false)
    private String resthours;

    @ApiModelProperty(name = "restrating", value = "Restaurant's Rating", required = true, example = "9/10")
    @Column(nullable = false)
    private String restrating;

    @ApiModelProperty(name = "recentvisit", value = "Recently Visited", example = "07/15/2019")
    private String recentvisit;

    @ApiModelProperty(name = "restphotos", value = "Photos of Restaurant", example = "http://www.fnstatic.co.uk/images/content/recipe/three-egg-omelette.jpeg, http://www.fnstatic.co.uk/images/content/recipe/three-egg-omelette.jpeg")
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("restaurant")
    private List<RestPhotos> restphotos = new ArrayList<>();

    @ApiModelProperty(name = "reviews", value = "Reviews of Restaurant", example = "Some Review, Some Review,...")
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("restaurant")
    private List<Reviews> reviews = new ArrayList<>();

    @ApiModelProperty(name = "userrest", value = "Restaurant made from User", example = "Some Restaurant, Some Restaurant,...")
    @ManyToMany
    @JoinTable(name = "userrest", joinColumns = {@JoinColumn(name = "restid")}, inverseJoinColumns = {@JoinColumn(name = "userid")})
    @JsonIgnoreProperties("restaurant")
    private List<User> user = new ArrayList<>();

    public Restaurant()
    {
    }


    public Restaurant(String restname, String restlocation, String resthours, String recentvisit, String restrating, List<User> user)
    {
        this.restname = restname;
        this.restlocation = restlocation;
        this.resthours = resthours;
        this.restrating = restrating;
        this.recentvisit = recentvisit;
        this.user = user;
    }

    public long getRestid()
    {
        return restid;
    }

    public void setRestid(long restid)
    {
        this.restid = restid;
    }

    public String getRestname()
    {
        return restname;
    }

    public void setRestname(String restname)
    {
        this.restname = restname;
    }

    public String getRestlocation()
    {
        return restlocation;
    }

    public void setRestlocation(String restlocation)
    {
        this.restlocation = restlocation;
    }

    public String getResthours()
    {
        return resthours;
    }

    public void setResthours(String resthours)
    {
        this.resthours = resthours;
    }

    public String getRestrating()
    {
        return restrating;
    }

    public void setRestrating(String restrating)
    {
        this.restrating = restrating;
    }

    public String getRecentvisit()
    {
        return recentvisit;
    }

    public void setRecentvisit(String recentvisit)
    {
        this.recentvisit = recentvisit;
    }

    public List<RestPhotos> getRestphotos()
    {
        return restphotos;
    }

    public void setRestphotos(List<RestPhotos> restphotos)
    {
        this.restphotos = restphotos;
    }

    public List<Reviews> getReviews()
    {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews)
    {
        this.reviews = reviews;
    }

    public List<User> getUser()
    {
        return user;
    }

    public void setUser(List<User> user)
    {
        this.user = user;
    }
}
