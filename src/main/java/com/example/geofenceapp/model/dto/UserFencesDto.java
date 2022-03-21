package com.example.geofenceapp.model.dto;

import com.example.geofenceapp.model.Point;
import lombok.Data;

import java.util.Set;

@Data
public class UserFencesDto {
    private Long fenceId;
    private String fenceName;
    private Boolean isTracked;
    private Set<Point> points;
}
