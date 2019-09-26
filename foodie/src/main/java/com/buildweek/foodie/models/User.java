package com.buildweek.foodie.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// User is considered the parent entity

@Entity
@Table(name = "users")
public class User extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @Column(nullable = false,
            unique = true)
    private String username;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String email;

    private String photo;

    @OneToMany(mappedBy = "user",
               cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<UserRoles> userroles = new ArrayList<>();

    @ApiModelProperty(name = "userrest", value = "Restaurant made from User", example = "Some Restaurant, Some Restaurant,...")
    @ManyToMany
    @JoinTable(name = "userrest", joinColumns = {@JoinColumn(name = "userid")}, inverseJoinColumns = {@JoinColumn(name = "restid")})
    @JsonIgnoreProperties("user")
    private List<Restaurant> restaurant = new ArrayList<>();

    public User()
    {
    }

    public User(String username, String password, String location, String photo, String email, List<UserRoles> userRoles)
    {
        setUsername(username);
        setPassword(password);
        setLocation(location);
        setPhoto(photo);
        setEmail(email);
        for (UserRoles ur : userRoles)
        {
            ur.setUser(this);
        }
        this.userroles = userRoles;
    }


    public long getUserid()
    {
        return userid;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public void setPasswordNoEncrypt(String password)
    {
        this.password = password;
    }

    public List<UserRoles> getUserroles()
    {
        return userroles;
    }

    public void setUserroles(List<UserRoles> userroles)
    {
        this.userroles = userroles;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getPhoto()
    {
        return photo;
    }

    public void setPhoto(String photo)
    {
        this.photo = photo;
    }

    public List<Restaurant> getRestaurant()
    {
        return restaurant;
    }

    public void setRestaurant(List<Restaurant> restaurant)
    {
        this.restaurant = restaurant;
    }

    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        for (UserRoles r : this.userroles)
        {
            String myRole = "ROLE_" + r.getRole()
                                       .getName()
                                       .toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }

        return rtnList;
    }

    @Override
    public String toString()
    {
        return "User{" + "userid=" + userid + ", username='" + username + '\'' + ", password='" + password + '\'' + ", userRoles=" + userroles + ", email=" + email + ", photo=" + photo +'}';
    }
}
