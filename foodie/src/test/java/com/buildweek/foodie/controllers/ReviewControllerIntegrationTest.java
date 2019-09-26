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

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ReviewControllerIntegrationTest
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

    @WithUserDetails("Bob")
    @Test
    public void B_getAllReviewsFromRestaurant() throws Exception
    {
        this.mockMvc.perform(get("/user/restaurants/{restid}/reviews", 6))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("cuisinetype")));
    }
    @WithUserDetails("Bob")
    @Test
    public void C_getReviewById() throws Exception
    {
        this.mockMvc.perform(get("/user/reviews/{reviewid}", 9))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("menuitemname")));
    }

    @WithUserDetails("Bob")
    @Test
    public void F_givenPostAReview() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/restaurant/{restid}/reviews/", 6)
                                              .content("{\"cuisinetype\": \"Ginger\", \"menuitemname\": \"EATEATEAT\", \"photomenu\": \"seth.nadu@gmail.com\", \"itemprice\": 10.00, \"itemrating\": \"seth.nadu@gmail.com\", \"shortreview\": \"seth.nadu@gmail.com\"}")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .accept(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isCreated())
               .andExpect(MockMvcResultMatchers.header()
                                               .exists("location"));
    }

    @WithUserDetails("Bob")
    @Test
    public void G_deleteRestaurantById() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/reviews/{reviewid}", 10))
               .andDo(print())
               .andExpect(status().is2xxSuccessful());
    }

    @WithUserDetails("Bob")
    @Test
    public void H_UpdateRestaurant() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.put("/user/restaurant/{restid}/reviews/{reviewid}", 6, 9)
                                              .content("{\"cuisinetype\": \"EATEATEAT\"}")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .accept(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk());
    }
}
