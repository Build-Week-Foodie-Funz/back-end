package com.buildweek.foodie.services;

import com.buildweek.foodie.StartHereApplication;
import com.buildweek.foodie.models.*;
import com.buildweek.foodie.repository.RestaurantRepository;
import com.buildweek.foodie.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartHereApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestaurantServiceImplUnitTest
{

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restrepos;

    @Autowired
    private UserRepository userrepos;

    @Before
    public void AsetUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void BtearDown() throws Exception
    {
    }

    @Transactional
    @WithUserDetails("Bob")
    @Test
    public void CfindAll()
    {
        assertEquals(3, restaurantService.findAll().getRestaurant().size());
    }

    @Transactional
    @WithUserDetails("Bob")
    @Test
    public void CfindRestaurantById()
    {
        assertEquals("Geno's Steaks", restaurantService.findRestaurantById(17).getRestname());
    }

    @Transactional
    @WithUserDetails("Bob")
    @Test
    public void GdeleteFound()
    {
        restaurantService.delete(17);
        assertEquals(4, restaurantService.findAll().getRestaurant().size());
    }

    @Transactional
    @WithUserDetails("Bob")
    @Test
    public void EAupdate()
    {
        Restaurant r1 = new Restaurant("TESTCHANGE",
                                       null,
                                       null, "ZZ", null, null);
        r1.setRestid(17);

        Restaurant updatedR1 = restaurantService.update(r1, 17);

        assertEquals("TESTCHANGE", updatedR1.getRestname());
    }


    @Test
    @WithUserDetails("Bob")
    public void F_save()
    {
        ArrayList<UserRoles> datas = new ArrayList<>();
        User u2 = new User("tiger", "ILuvMath!", "Philadelphia", "https://files.slack.com/files-pri/T4JUEB3ME-FN9BMEWQJ/nopath.png", "test@test.com", datas);

        ArrayList<User> userlist = new ArrayList<>();
        userlist.add(u2);

        Restaurant r1 = new Restaurant("then", "then", "then", "then", "then", userlist);

        Restaurant saveU2 = restaurantService.save(r1);

        System.out.println("*** DATA ***");
        System.out.println(saveU2);
        System.out.println("*** DATA ***");

        //        assertEquals("tiger@tiger.local", saveU2);
    }

}
