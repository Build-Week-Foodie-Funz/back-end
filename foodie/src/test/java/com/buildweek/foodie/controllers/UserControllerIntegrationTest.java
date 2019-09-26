package com.buildweek.foodie.controllers;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test for UserController so only looking at 100% coverage on UserController
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerIntegrationTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    public static String asJsonString(final Object obj)
    {
        try
        {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setUp() throws Exception
    {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                                 .apply(SecurityMockMvcConfigurers.springSecurity())
                                 .build();
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @WithUserDetails("barnbarn")
    @Test
    public void A_whenMeasuredResponseTime()
    {
        given().when()
               .get("/users/users")
               .then()
               .time(lessThan(8000L));
    }

    @WithUserDetails("admin")
    @Test
    public void B_getAllUsers() throws Exception
    {
        this.mockMvc.perform(get("/users/users"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Bob")));
    }

    @WithUserDetails("admin")
    @Test
    public void C_getUserById() throws Exception
    {
        this.mockMvc.perform(get("/users/user/{userid}", 22))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Bob")));
    }

    @WithUserDetails("admin")
    @Test
    public void CA_getUserByIdNotFound() throws Exception
    {
        this.mockMvc.perform(get("/users/user/{userid}", 100))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().string(containsString("ResourceNotFoundException")));
    }

    @WithUserDetails("admin")
    @Test
    public void D_getUserByName() throws Exception
    {
        this.mockMvc.perform(get("/users/user/name/{userName}", "Bob"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Bob")));
    }

    @WithUserDetails("admin")
    @Test
    public void DA_getUserByNameNotFound() throws Exception
    {
        this.mockMvc.perform(get("/users/user/name/{userName}", "rabbit"))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().string(containsString("ResourceNotFoundException")));
    }

    @WithUserDetails("admin")
    @Test
    public void E_getCurrentUserName() throws Exception
    {
        this.mockMvc.perform(get("/users/getusername"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("admin")));
    }

    @WithUserDetails("admin")
    @Test
    public void F_givenPostAUser() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/user")
                                              .content("{\"username\": \"Ginger\", \"password\": \"EATEATEAT\", \"email\": \"seth.nadu@gmail.com\", \"location\": \"here\"}")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .accept(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isCreated())
               .andExpect(MockMvcResultMatchers.header()
                                               .exists("location"));
    }

    @WithUserDetails("admin")
    @Test
    public void G_deleteUserById() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/user/{id}", 22))
               .andDo(print())
               .andExpect(status().is2xxSuccessful());
    }

    @WithUserDetails("admin")
    @Test
    public void GA_deleteUserByIdNotFound() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/user/{id}", 100))
               .andDo(print())
               .andExpect(status().is4xxClientError());
    }

    @WithUserDetails("admin")
    @Test
    public void H_UpdateUser() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.put("/users/user/{userid}", 3)
                                              .content("{\"password\": \"EATEATEAT\"}")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .accept(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk());
    }

    @WithUserDetails("admin")
    @Test
    public void I_deleteUserRoleByIds() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/user/{userid}/role/{roleid}", 22, 2))
               .andDo(print())
               .andExpect(status().is2xxSuccessful());
    }

    @WithUserDetails("admin")
    @Test
    public void J_postUserRoleByIds() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/user/{userid}/role/{roleid}", 22, 1))
               .andDo(print())
               .andExpect(status().is2xxSuccessful());
    }
}