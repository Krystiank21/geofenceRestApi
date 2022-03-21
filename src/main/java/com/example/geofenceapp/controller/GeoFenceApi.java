package com.example.geofenceapp.controller;

import com.example.geofenceapp.algorithm.AlgorithmPoint;
import com.example.geofenceapp.services.ICheckService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class GeoFenceApi {

    private final ICheckService checkService;

    public GeoFenceApi(ICheckService checkService) {
        this.checkService = checkService;
    }

    @PostMapping("/check")
    public boolean isPointInPolygon(@RequestBody AlgorithmPoint point) { //TODO DTOPOINT
        return checkService.isPointInPolygon(point);
    }
}