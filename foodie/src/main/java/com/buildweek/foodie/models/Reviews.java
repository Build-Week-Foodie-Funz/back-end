package com.buildweek.foodie.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name ="reviews")
public class Reviews
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reviewid;


    @Column(nullable = false)
    private String cuisinetype;

    @Column(nullable = false)
    private String menuitemname;

    @Column(nullable = false)
    private String photomenu;

    @Column(nullable = false)
    private Double itemprice;

    @Column(nullable = false)
    private String itemrating;

    @Column(nullable = false, columnDefinition = "Text")
    private String shortreview;

    @ManyToOne
    @JoinColumn(name = "restid")
    @JsonIgnoreProperties("reviews")
    private Restaurant restaurant;

    public Reviews()
    {
    }

    public Reviews( Restaurant restaurant, String cuisinetype, String menuitemname, String photomenu, Double itemprice, String itemrating, String shortreview)
    {
        this.cuisinetype = cuisinetype;
        this.menuitemname = menuitemname;
        this.photomenu = photomenu;
        this.itemprice = itemprice;
        this.itemrating = itemrating;
        this.shortreview = shortreview;
        this.restaurant = restaurant;
    }

    public Reviews(String cuisinetype, String menuitemname, String photomenu, Double itemprice, String itemrating, String shortreview)
    {
        this.cuisinetype = cuisinetype;
        this.menuitemname = menuitemname;
        this.photomenu = photomenu;
        this.itemprice = itemprice;
        this.itemrating = itemrating;
        this.shortreview = shortreview;
    }

    public long getReviewid()
    {
        return reviewid;
    }

    public void setReviewid(long reviewid)
    {
        this.reviewid = reviewid;
    }


    public String getCuisinetype()
    {
        return cuisinetype;
    }

    public void setCuisinetype(String cuisinetype)
    {
        this.cuisinetype = cuisinetype;
    }

    public String getMenuitemname()
    {
        return menuitemname;
    }

    public void setMenuitemname(String menuitemname)
    {
        this.menuitemname = menuitemname;
    }

    public String getPhotomenu()
    {
        return photomenu;
    }

    public void setPhotomenu(String photomenu)
    {
        this.photomenu = photomenu;
    }

    public Double getItemprice()
    {
        return itemprice;
    }

    public void setItemprice(Double itemprice)
    {
        this.itemprice = itemprice;
    }

    public String getItemrating()
    {
        return itemrating;
    }

    public void setItemrating(String itemrating)
    {
        this.itemrating = itemrating;
    }

    public String getShortreview()
    {
        return shortreview;
    }

    public void setShortreview(String shortreview)
    {
        this.shortreview = shortreview;
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
