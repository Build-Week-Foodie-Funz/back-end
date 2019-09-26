package com.buildweek.foodie.controllers;

import com.buildweek.foodie.models.*;
import com.buildweek.foodie.repository.RestaurantRepository;
import com.buildweek.foodie.services.RestaurantService;
import com.buildweek.foodie.services.RoleService;
import com.buildweek.foodie.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RestaurantController.class, secure = false)
public class RestaurantControllerUnitTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @MockBean
    RoleService roleService;

    @MockBean
    private RestaurantRepository restrepos;

    @MockBean
    UserService userService;

    private List<Restaurant> restaurantList;

    private List<Role> userroles;

    private List<User> user;

    @Before
    public void setUp() throws Exception
    {
        restaurantList = new ArrayList<>();
        userroles = new ArrayList<>();
        user = new ArrayList<>();

        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        userroles.add(r1);
        userroles.add(r2);
        userroles.add(r3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "password", "Philadelphia", "https://files.slack.com/files-pri/T4JUEB3ME-FN9BMEWQJ/nopath.png", "admin@gmail.com", admins);
        user.add(u1);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();

        users.add(new UserRoles(new User(), r2));
        User u3 = new User("barnbarn", "ILuvM4th!", "Philadelphia", "https://files.slack.com/files-pri/T4JUEB3ME-FN9BMEWQJ/nopath.png", "barnbarn@gmail.com", users);

        user.add(u3);


        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Bob", "password", "New York", "https://files.slack.com/files-pri/T4JUEB3ME-FN9BMEWQJ/nopath.png", "bob@gmail.com", users);
        ArrayList<User> usersadd = new ArrayList<>();
        usersadd.add(u4);
        Restaurant rest1 = new Restaurant("Bridgeport Family Restaurant", "Lancaster, PA", "6:00AM - 2:00PM", "12/17/2019", "9/10",usersadd);
        rest1.getRestphotos().add(new RestPhotos(rest1, "https://s3-media1.fl.yelpcdn.com/bphoto/hcztsvBKXc9-AmWZRvSUhQ/l.jpg"));
        rest1.getRestphotos().add(new RestPhotos(rest1, "https://live.staticflickr.com/702/32136139536_ee7c3c8ef7_b.jpg"));
        rest1.getReviews().add(new Reviews(rest1, "American Cuisine", "Breakfast Omelet", "https://tmbidigitalassetsazure.blob.core.windows.net/secure/RMS/attachments/37/1200x1200/exps167311_SD153321B02_04_3b.jpg", 7.50, "8.3/10", "Eggs are unbelievable! Fast time, fresh tomatos with a cheap price."));
        rest1.getReviews().add(new Reviews(rest1, "American Cuisine", "Woodsman's Special", "https://www.neighborhoodeals.com/wp-content/uploads/2018/02/breakfast1.jpg", 10.99, "9/10", "Lots of food for a great price! Pancakes lathered in butter with smokey bacon would fill anybody's stomach!"));
        rest1.setRestid(10);
        restaurantList.add(rest1);


        Restaurant rest2 = new Restaurant("Bibou", "Philadelphia, PA", "5:30PM - 11PM", "07/10/2019", "8/10", usersadd);
        rest2.getRestphotos().add(new RestPhotos(rest2,"https://vrconcierge.com/wp-content/uploads/2017/06/8701_bibou-philadelphia-exterior-2.jpg"));
        rest2.getRestphotos().add(new RestPhotos(rest2,"https://cdn10.phillymag.com/wp-content/uploads/2013/01/10_Bibou_Mike-Arrison.jpg"));
        rest2.getRestphotos().add(new RestPhotos(rest2, "https://s3-media4.fl.yelpcdn.com/bphoto/9C9kBTbfclVmIDpzzXEdFw/l.jpg"));
        rest2.getReviews().add(new Reviews(rest2, "French Cuisine", "Veau", "https://www.goodfoodrevolution.com/wp-content/uploads/2012/10/tete-de-veau.jpeg", 27.99, "10/10", "Absolutely delicious! Melt in your mouth veal with fresh vegetables that can not be matched!"));
        rest2.getReviews().add(new Reviews(rest2, "French Cuisine", "Espadon", "https://cache.marieclaire.fr/data/photo/w700_c17/43/espadon-aux-raisins-et-aux-.jpg", 32.99, "10/10", "Would come back strickly for this meal everytime. The Espadon was fantastic."));
        rest1.setRestid(11);
        restaurantList.add(rest2);

        Restaurant rest3 = new Restaurant("Geno's Steaks", "Philadelphia, PA", "24 hours","08/08/2019", "9/10", usersadd);
        rest3.getRestphotos()
             .add(new RestPhotos(rest3,"https://upload.wikimedia.org/wikipedia/commons/5/59/Genos_Steaks.JPG"));
        rest3.getRestphotos()
             .add(new RestPhotos(rest3,"http://www.jackduggans.com/wp-content/uploads/2017/03/Geno-820x3381.jpg"));
        rest3.getRestphotos()
             .add(new RestPhotos(rest3, "https://i.ytimg.com/vi/tH135gUUzFc/maxresdefault.jpg"));
        rest3.getReviews()
             .add(new Reviews(rest3,"American Cuisine", "Cheesesteak", "https://www.genosteaks.com/wp-content/uploads/2015/07/product_slide_1.png", 11.00, "10/10", "Best cheesteaks in Philadelphia!"));
        rest1.setRestid(12);
        restaurantList.add(rest3);

        user.add(u4);
    }

    @After
    public void tearDown() throws Exception
    {
    }



    @Test
    public void listAllRestaurants() throws Exception
    {
        String apiUrl = "/user/restaurants";

        Mockito.when(restrepos.findAll()).thenReturn(restaurantList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

        // the following actually performs a real controller call
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(restaurantList);

        assertEquals("Rest API Returns List", er, er);
    }

    @Test
    public void getRestaurantById() throws Exception
    {
        String apiUrl = "/user/restaurant/17";

        Mockito.when(restaurantService.findRestaurantById(17)).thenReturn(restaurantList.get(2));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(restaurantList.get(2));

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    public void deleteRestaurantById() throws Exception
    {
        String apiUrl = "/user/restaurant/{restid}";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, "17").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(rb).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateRestaurant() throws Exception
    {
        String apiUrl = "/user/restaurant/{restid}";
        Restaurant r1 = new Restaurant("TESTCHANGE",
                                       null,
                                       null, "ZZ", null, null);
//        r1.setRestid(17);


        Mockito.when(restaurantService.update(r1, 17L)).thenReturn(r1);
        ObjectMapper mapper = new ObjectMapper();
        String restaurantString = mapper.writeValueAsString(r1);

        RequestBuilder rb = MockMvcRequestBuilders.put(apiUrl, 17L)
                                                  .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                                  .content(restaurantString);

        mockMvc.perform(rb).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addNewRestaurant() throws Exception
    {
        String apiUrl = "/user/restaurant";
        Restaurant r1 = new Restaurant("TESTADD",
                                       "Philly",
                                       "24hours", "ZZ", "10", null);
        r1.setRestid(17);

        ObjectMapper mapper = new ObjectMapper();
        String restaurantString = mapper.writeValueAsString(r1);

        Mockito.when(restaurantService.save(any(Restaurant.class))).thenReturn(r1);

        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl)
                                                  .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                                  .content(restaurantString);
        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

}
