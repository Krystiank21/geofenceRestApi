package com.example.geofenceapp.repository;

import com.example.geofenceapp.model.TrackedObj;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITrackedObjRepository extends JpaRepository<TrackedObj, Long> {
    TrackedObj findTrackedObjByTrackedObjId(Long trackedObjId);
    void deleteTrackedObjByTrackedObjId(Long trackedObjId);
    List<TrackedObj> getAllByUserUserId(Long userId);
}
