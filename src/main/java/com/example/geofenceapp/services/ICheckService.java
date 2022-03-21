package com.example.geofenceapp.services;

import com.example.geofenceapp.algorithm.AlgorithmPoint;

public interface ICheckService {
    boolean isPointInPolygon(AlgorithmPoint point);
}
