package com.example.geofenceapp.services;

import com.example.geofenceapp.model.TrackedObj;
import com.example.geofenceapp.model.dto.TrackedObjDto;
import com.example.geofenceapp.model.dto.TrackedObjFenceDto;

import java.util.List;

public interface ITrackedObjService {
    void addNewTrackedObj(TrackedObjDto TrackedObjDto);
    void updateDetails(TrackedObjDto trackedObjDto);
    void deleteTrackedObj(Long id);
    void changeTrackingToON(Long trackedObjId);
    void changeTrackingToOFF(Long trackedObjId);
    void addNewFenceToTrackedObj(TrackedObjFenceDto trackedObjFenceDto);
    void deleteFenceFromTrackedObj(TrackedObjFenceDto trackedObjFenceDto);
    List<TrackedObj> getAllUserTrackedObjs(Long userId);
    List<TrackedObjDto> getAllUserTrackedObjDto(Long userId);
    void changeTrackingToONForAllTrackedObjs(Long userId);
    void changeTrackingToOFFForAllTrackedObjs(Long userId);
}
