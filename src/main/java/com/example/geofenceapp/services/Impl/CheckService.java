package com.example.geofenceapp.services.Impl;

import com.example.geofenceapp.algorithm.AlgorithmPoint;
import com.example.geofenceapp.services.ICheckService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CheckService implements ICheckService {

    final RayCastingAlgorithmService rayCastingAlgorithmService;

    @Override
    public boolean isPointInPolygon(AlgorithmPoint algorithmPoint) {
       if(rayCastingAlgorithmService.isPointInPolygon(algorithmPoint)){
           System.out.println(true);
           return true;
       } else {
           System.out.println(false);
           return false;
       }
    }
}