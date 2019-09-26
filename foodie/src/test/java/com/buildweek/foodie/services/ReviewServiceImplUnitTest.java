package com.buildweek.foodie.services;

import com.buildweek.foodie.StartHereApplication;
import com.buildweek.foodie.models.Restaurant;
import com.buildweek.foodie.models.Reviews;
import com.buildweek.foodie.models.User;
import com.buildweek.foodie.models.UserRoles;
import com.buildweek.foodie.repository.RestaurantRepository;
import com.buildweek.foodie.repository.ReviewsRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartHereApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReviewServiceImplUnitTest
{
    @Autowired
    private RestaurantService restaurantService;

    @Autowired ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantRepository restrepos;

    @Autowired
    private ReviewsRepository reviewrepos;

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
        assertEquals(5, reviewService.findAll().size());
    }

    @Transactional
    @WithUserDetails("Bob")
    @Test
    public void CfindReviewById()
    {
        assertEquals("Cheesesteak", reviewService.findReviewById(21).getMenuitemname());
    }

    @Transactional
    @WithUserDetails("Bob")
    @Test
    public void GdeleteFound()
    {
        reviewService.delete(21);
        assertEquals(4, reviewService.findAll().size());
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

        Reviews rev = new Reviews(r1, null, "VeauTEST", null, 27.99, null, "Absolutely delicious! Melt in your mouth veal with fresh vegetables that can not be matched!");

        rev.setReviewid(21);

        Reviews updatedRev = reviewService.update(rev, 21, 17);

        assertEquals("VeauTEST", updatedRev.getMenuitemname());
    }

    @Transactional
    @Test
    @WithUserDetails("Bob")
    public void F_save()
    {
        ArrayList<UserRoles> datas = new ArrayList<>();

        User u2 = new User("tiger", "ILuvMath!", "Philadelphia", "https://files.slack.com/files-pri/T4JUEB3ME-FN9BMEWQJ/nopath.png", "test@test.com", datas);
        userService.save(u2);

        ArrayList<User> userlist = new ArrayList<>();
        userlist.add(u2);

        Restaurant r1 = new Restaurant("then", "then", "then", "then", "then", userlist);
        restrepos.save(r1);
        Reviews rev = new Reviews(r1, "then", "then", "then", 10.00, "then", "then");
        r1.getReviews().add(rev);
        Reviews saveU2 = reviewrepos.save(rev);

        System.out.println("*** DATA ***");
        System.out.println(saveU2);
        System.out.println("*** DATA ***");


    }
}
