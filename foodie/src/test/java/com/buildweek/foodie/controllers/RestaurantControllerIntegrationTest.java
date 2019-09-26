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
public class RestaurantControllerIntegrationTest
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
    public void B_getAllRestaurant() throws Exception
    {
        this.mockMvc.perform(get("/user/restaurants"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("restaurant")));
    }
    @WithUserDetails("Bob")
    @Test
    public void C_getRestaurantById() throws Exception
    {
        this.mockMvc.perform(get("/user/restaurant/{restid}", 6))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Bridgeport Family Restaurant")));
    }

    @WithUserDetails("Bob")
    @Test
    public void F_givenPostARestaurant() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/restaurant")
                                              .content("{\"restname\": \"Ginger\", \"restlocation\": \"EATEATEAT\", \"resthours\": \"seth.nadu@gmail.com\", \"restrating\": \"here\", \"recentvisit\": \"seth.nadu@gmail.com\", \"user\": null}")
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
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/restaurant/{id}", 17))
               .andDo(print())
               .andExpect(status().is2xxSuccessful());
    }

    @WithUserDetails("Bob")
    @Test
    public void H_UpdateRestaurant() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.put("/user/restaurant/{restid}", 11)
                                              .content("{\"resthours\": \"EATEATEAT\"}")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .accept(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk());
    }

}
