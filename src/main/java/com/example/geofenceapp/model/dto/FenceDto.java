package com.example.geofenceapp.model.dto;

import com.example.geofenceapp.model.Point;
import com.example.geofenceapp.model.TrackedObj;
import lombok.Data;

import java.util.Set;

@Data
public class FenceDto {
    private Long fenceId;
    private String fenceName;
    private Boolean isTracked;
    private Set<TrackedObj> trackedObjs;
    private Set<Point> points;
}