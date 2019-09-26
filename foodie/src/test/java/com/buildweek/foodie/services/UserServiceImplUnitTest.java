package com.buildweek.foodie.services;

import com.buildweek.foodie.StartHereApplication;
import com.buildweek.foodie.exceptions.ResourceFoundException;
import com.buildweek.foodie.exceptions.ResourceNotFoundException;
import com.buildweek.foodie.models.Role;
import com.buildweek.foodie.models.User;
import com.buildweek.foodie.models.UserRoles;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

/**
 * I am testing UserServiceImpl so want 100% in UserServiceImpl
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartHereApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplUnitTest
{
    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void A_loadUserByUsername()
    {
        assertEquals("admin", userService.loadUserByUsername("admin").getUsername());
    }

    @Test (expected = UsernameNotFoundException.class)
    public void AA_loadUserByUsernameNotfound()
    {
        assertEquals("admin", userService.loadUserByUsername("turtle").getUsername());
    }


    @Test
    public void B_findUserById()
    {
        assertEquals("admin", userService.findUserById(4).getUsername());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void BA_findUserByIdNotFound()
    {
        assertEquals("admin", userService.findUserById(10).getUsername());
    }

    @Test
    public void C_findAll()
    {
        assertEquals(3, userService.findAll().size());
    }



    @Test(expected = ResourceNotFoundException.class)
    public void DA_notFoundDelete()
    {
        userService.delete(100);
        assertEquals(4, userService.findAll().size());
    }

    @Test
    public void E_findByUsername()
    {
        assertEquals("admin", userService.findByName("admin").getUsername());
    }

    @Test (expected = ResourceNotFoundException.class)
    public void AA_findByUsernameNotfound()
    {
        assertEquals("admin", userService.findByName("turtle").getUsername());
    }

    @Test
    public void F_save()
    {
        ArrayList<UserRoles> datas = new ArrayList<>();
        User u2 = new User("tiger", "ILuvMath!", "Philadelphia", "https://files.slack.com/files-pri/T4JUEB3ME-FN9BMEWQJ/nopath.png", "test@test.com", datas);


        User saveU2 = userService.save(u2);

        System.out.println("*** DATA ***");
        System.out.println(saveU2);
        System.out.println("*** DATA ***");

//        assertEquals("tiger@tiger.local", saveU2);
    }



    @Transactional
    @WithUserDetails("Bob")
    @Test
    public void G_update()
    {
        ArrayList<UserRoles> datas = new ArrayList<>();
        User u2 = new User("Bob", "password", "Philadelphia", "https://files.slack.com/files-pri/T4JUEB3ME-FN9BMEWQJ/nopath.png", "test@test.com", datas);

        User updatedu2 = userService.update(u2, 22, false);

        System.out.println("*** DATA ***");
        System.out.println(updatedu2);
        System.out.println("*** DATA ***");

    }

    @Transactional
    @WithUserDetails("Bob")
    @Test (expected = ResourceFoundException.class)
    public void GA_updateWithUserRole()
    {
        Role r2 = new Role("user");

        ArrayList<UserRoles> datas = new ArrayList<>();
        User u2 = new User("Bob", "password", "Philadelphia", "https://files.slack.com/files-pri/T4JUEB3ME-FN9BMEWQJ/nopath.png", "test@test.com", datas);
        datas.add(new UserRoles(u2, r2));

        User updatedu2 = userService.update(u2, 22, false);

        System.out.println("*** DATA ***");
        System.out.println(updatedu2);
        System.out.println("*** DATA ***");

    }


    @Test (expected = ResourceNotFoundException.class)
    public void H_deleteUserRoleComboNotFound()
    {
        // testing cinnamon and user roles
        userService.deleteUserRole(11, 2);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void HA_deleteUserRoleRoleNotFound()
    {
        userService.deleteUserRole(7, 50);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void HB_deleteUserRoleUserNotFound()
    {
        userService.deleteUserRole(50, 2);
    }

    @Test(expected = ResourceFoundException.class)
    public void IA_addUserRoleUserRoleFound()
    {
        userService.addUserRole(5, 2);
    }

    @Test
    public void IB_deleteUserRole()
    {
        userService.deleteUserRole(5, 2);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void IC_addUserRoleRoleNotFound()
    {
        userService.addUserRole(7, 50);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void ID_addUserRoleUserNotFound()
    {
        userService.addUserRole(50, 2);
    }

    @Test
    public void IE_addUserRole()
    {
        userService.addUserRole(5, 2);
    }
}