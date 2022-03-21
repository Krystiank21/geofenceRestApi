package com.example.geofenceapp.services.Impl;

import com.example.geofenceapp.algorithm.AlgorithmPoint;
import com.example.geofenceapp.algorithm.AlgorithmPolygon;
import com.example.geofenceapp.algorithm.RayCastingAlgorithm;
import com.example.geofenceapp.services.IRayCastingAlgorithmService;
import org.springframework.stereotype.Service;

@Service
public class RayCastingAlgorithmService implements IRayCastingAlgorithmService {

    @Override
    public boolean isPointInPolygon(AlgorithmPoint algorithmPoint) {
        RayCastingAlgorithm algorithm = new RayCastingAlgorithm(algorithmPoint);
//TODO
        final AlgorithmPolygon algorithmPolygon = new AlgorithmPolygon(
                new AlgorithmPoint(1.0, 1.0),
                new AlgorithmPoint(2.0, 1.0),
                new AlgorithmPoint(2.0, 2.0),
                new AlgorithmPoint(1.0, 2.0));

        return algorithm.contains(algorithmPoint, algorithmPolygon);
    }
}
