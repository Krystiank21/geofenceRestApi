package com.example.geofenceapp.services;

import com.example.geofenceapp.model.User;
import com.example.geofenceapp.model.dto.UserDto;

public interface IUserService {
    void addNewUser(UserDto userDto);
    User getUserById(Long id);
    void updateUsername(UserDto userDto);
    void updateEmail(UserDto userDto);
    boolean isUserExistByUsername(String username);
    boolean isUserExistById(Long id);
    void deleteUser(Long id);
}