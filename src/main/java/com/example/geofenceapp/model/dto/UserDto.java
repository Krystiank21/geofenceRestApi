package com.example.geofenceapp.model.dto;

import com.example.geofenceapp.model.Enums.UserRoleType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UserDto {
    private Long userId;
    private String login;
    private String password;
    private String username;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRoleType userRoleType;
}