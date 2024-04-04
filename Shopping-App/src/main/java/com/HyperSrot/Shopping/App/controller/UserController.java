package com.HyperSrot.Shopping.App.controller;

import com.HyperSrot.Shopping.App.exception.UserNotFoundException;
import com.HyperSrot.Shopping.App.model.User;
import com.HyperSrot.Shopping.App.service.serviceInterface.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "User Management",description = "Managing User Registration And Retrieve User By userId")
public class UserController {
    @Autowired
    IUserService userService;
    @PostMapping("/register")
    @Operation(summary = "Allows new users to create an account by providing their username and email.")
    @ApiResponse(responseCode = "200", description = "User Register Successfull")
    @ApiResponse(responseCode = "500", description = " Internal Server Error!!")
    public ResponseEntity<String>registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @GetMapping
    @Operation(summary = "Retrieve User From Database Using User Id")
    @ApiResponse(responseCode = "200",description = "User Retrieve Successfully")
    @ApiResponse(responseCode = "500",description = "User Not Found Exception")
    public ResponseEntity<?>getUserById(@RequestParam Long userId) throws UserNotFoundException {
        return userService.getUserById(userId);
    }
}
