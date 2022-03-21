package com.example.geofenceapp.controller;

import com.example.geofenceapp.model.dto.UserDto;
import com.example.geofenceapp.services.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    /**
     * Add new user
     */
    @PostMapping("/add-user")
    public ResponseEntity<String> addNewUser(@RequestBody UserDto userDto) {
        try {
            // isUserExistByUsername!
            userService.addNewUser(userDto);
            return new ResponseEntity<>("User has been created!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("The user could not be created!");
        }
        return new ResponseEntity<>("The user could not be created!", HttpStatus.NOT_FOUND);
    }

    /**
     * Edit user's details
     */
    @PatchMapping("/edit-details/{userId}")
    public ResponseEntity<String> editUserDetails(@RequestBody UserDto userDto, @PathVariable Long userId) {
        userDto.setUserId(userId);
        try {
            if (userService.isUserExistById(userDto.getUserId())) {
                userService.updateUsername(userDto);
                userService.updateEmail(userDto);
                return new ResponseEntity<>("User details has been modified!", HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println("The details could not be modified!");
        }
        return new ResponseEntity<>("The details could not be modified!", HttpStatus.NOT_FOUND);
    }

    /**
     * Change user's password
     */
    @PatchMapping("/change-password/{userId}")
    public ResponseEntity<String> changeUserPassword(@RequestBody UserDto userDto, @PathVariable Long userId) {
        userDto.setUserId(userId);
        try {
            if (userService.isUserExistById(userDto.getUserId())) {
                return new ResponseEntity<>("User details has been modified!", HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println("The details could not be modified!");
        }
        return new ResponseEntity<>("The details could not be modified!", HttpStatus.NOT_FOUND);
    }

    /**
     * Delete user
     * */
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteTrackedObject(@PathVariable Long userId) {
        try {
            this.userService.deleteUser(userId);
            return new ResponseEntity<>("User deleted!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Failed to delete the User!");
        }
        return new ResponseEntity<>("Failed to delete the User!", HttpStatus.NOT_FOUND);
    }
}