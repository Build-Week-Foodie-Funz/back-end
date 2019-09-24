package com.buildweek.foodie.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="restaurants")
public class Restaurant
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long restid;

    @Column(nullable = false,
            unique = true)
    private String restname;

    @Column(nullable = false)
    private String restlocation;

    @Column(nullable = false)
    private String resthours;

    @Column(nullable = false)
    private String restrating;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("restaurant")
    private List<RestPhotos> restphotos = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("restaurant")
    private List<Reviews> reviews = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "userrest", joinColumns = {@JoinColumn(name = "restid")}, inverseJoinColumns = {@JoinColumn(name = "userid")})
    @JsonIgnoreProperties("restaurant")
    private List<User> user = new ArrayList<>();

    public Restaurant()
    {
    }

//    public Restaurant(String restname, String restlocation, String resthours, String restrating)
//    {
//        this.restname = restname;
//        this.restlocation = restlocation;
//        this.resthours = resthours;
//        this.restrating = restrating;
//    }
//
//    public Restaurant(String restname, String restlocation, String resthours, String restrating, List<RestPhotos> restphotos, List<Reviews> reviews)
//    {
//        this.restname = restname;
//        this.restlocation = restlocation;
//        this.resthours = resthours;
//        this.restrating = restrating;
//        this.restphotos = restphotos;
//        this.reviews = reviews;
//    }

    public Restaurant(String restname, String restlocation, String resthours, String restrating, List<User> user)
    {
        this.restname = restname;
        this.restlocation = restlocation;
        this.resthours = resthours;
        this.restrating = restrating;
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
