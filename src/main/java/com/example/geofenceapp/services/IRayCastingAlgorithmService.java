package com.example.geofenceapp.services;

import com.example.geofenceapp.algorithm.AlgorithmPoint;

public interface IRayCastingAlgorithmService {
    boolean isPointInPolygon(AlgorithmPoint point);
}
