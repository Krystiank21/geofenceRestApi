package com.example.geofenceapp.services;

import com.example.geofenceapp.model.dto.FenceDto;
import com.example.geofenceapp.model.dto.UserFencesDto;

import java.util.List;

public interface IFenceService {
    void addNewFence(FenceDto fenceDto);
    void updateDetails(FenceDto fenceDto);
    void deleteFence(Long id);
    void changeTrackingToON(Long fenceId);
    void changeTrackingToOFF(Long fenceId);
    List<UserFencesDto> getAllUserFences(Long id);
}