package com.buildweek.foodie.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name ="reviews")
public class Reviews
{
    @ApiModelProperty(name = "reviewid", value = "primary key for Reviews", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reviewid;

    @ApiModelProperty(name = "cuisinetype", value = "Type of Cuisine", required = true, example = "American")
    @Column(nullable = false)
    private String cuisinetype;

    @ApiModelProperty(name = "menuitemname", value = "Name of Menu Item", required = true, example = "Three Egg Omelete")
    @Column(nullable = false)
    private String menuitemname;

    @ApiModelProperty(name = "photomenu", value = "Photo of Menu item [url]", example = "http://www.fnstatic.co.uk/images/content/recipe/three-egg-omelette.jpeg")
    private String photomenu;

    @ApiModelProperty(name = "itemprice", value = "Price of Menu item", required = true, example = "10.00")
    @Column(nullable = false)
    private Double itemprice;

    @ApiModelProperty(name = "itemrating", value = "Rating of Menu item", required = true, example = "9/10")
    @Column(nullable = false)
    private String itemrating;

    @ApiModelProperty(name = "shortreview", value = "Short Review of Menu item", required = true, example = "Fantastic Food!")
    @Column(nullable = false, columnDefinition = "Text")
    private String shortreview;

    @ApiModelProperty(name = "reviews", value = "Reviews of Restaurant", example = "Some Review, Some Review,...")
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

//    public Reviews(String cuisinetype, String menuitemname, String photomenu, Double itemprice, String itemrating, String shortreview)
//    {
//        this.cuisinetype = cuisinetype;
//        this.menuitemname = menuitemname;
//        this.photomenu = photomenu;
//        this.itemprice = itemprice;
//        this.itemrating = itemrating;
//        this.shortreview = shortreview;
//    }

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
