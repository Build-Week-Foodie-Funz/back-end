package com.buildweek.foodie.controllers;

import com.buildweek.foodie.models.ErrorDetail;
import com.buildweek.foodie.models.User;
import com.buildweek.foodie.models.UserRoles;
import com.buildweek.foodie.services.RoleService;
import com.buildweek.foodie.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

// in order to return an auth token
// client goes to this end point
// client then goes to login end point

@RestController
public class OpenController
{
    private static final Logger logger = LoggerFactory.getLogger(OpenController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    //  Get localhost:2019/createnewuser
    @ApiOperation(value = "Add User", responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User Added", response = void.class),
            @ApiResponse(code = 500, message = "Error Adding User", response = ErrorDetail.class)})
    @PostMapping(value = "/createnewuser",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    public ResponseEntity<?> addNewUser(HttpServletRequest request, @Valid
    @RequestBody
            User newuser) throws URISyntaxException
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        ArrayList<UserRoles> newRoles = new ArrayList<>();
        newRoles.add(new UserRoles(newuser, roleService.findByName("user")));
        newuser.setUserroles(newRoles);

        newuser = userService.save(newuser);

        // set the location header for the newly created resource - to another controller!
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRestaurantURI = ServletUriComponentsBuilder.fromUriString(request.getServerName() + ":" + request.getLocalPort() + "/users/user/{userId}")
                                                          .buildAndExpand(newuser.getUserid())
                                                          .toUri();
        responseHeaders.setLocation(newRestaurantURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

}

