package com.example.geofenceapp.services.Impl;

import com.example.geofenceapp.model.Fence;
import com.example.geofenceapp.model.TrackedObj;
import com.example.geofenceapp.model.User;
import com.example.geofenceapp.model.dto.FenceDto;
import com.example.geofenceapp.model.dto.UserFencesDto;
import com.example.geofenceapp.repository.IFenceRepository;
import com.example.geofenceapp.services.IFenceService;
import com.example.geofenceapp.services.ITrackedObjService;
import com.example.geofenceapp.services.IUserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@AllArgsConstructor
@Service
public class FenceService implements IFenceService {

    private final IFenceRepository fenceRepository;
    private final IUserService userService;
    private final ITrackedObjService trackedObjService;
    private final ModelMapper modelMapper;

    @Override
    public void addNewFence(FenceDto fenceDto) {
        Fence newFence = modelMapper.map(fenceDto, Fence.class);
        fenceRepository.save(newFence);
    }

    @Override
    public void updateDetails(FenceDto fenceDto) {
        Fence fenceToUpdate = this.fenceRepository.findFenceByFenceId(fenceDto.getFenceId());
        //TODO
        fenceRepository.save(fenceToUpdate);
    }

    @Override
    public void deleteFence(Long id) {
        this.fenceRepository.deleteFenceByFenceId(id);
    }

    @Override
    public void changeTrackingToON(Long fenceId) {
        changeTracking(fenceId, true);
    }

    @Override
    public void changeTrackingToOFF(Long fenceId) {
        changeTracking(fenceId, false);
    }

    private void changeTracking(Long fenceId, boolean isTracked){
        Fence fenceToChangeTracking = fenceRepository.findFenceByFenceId(fenceId);
        fenceToChangeTracking.setIsTracked(isTracked);
        fenceRepository.save(fenceToChangeTracking);
    }

    @Override
    public List<UserFencesDto> getAllUserFences(Long id) {
        List<Fence> userFences = new ArrayList<>();
        Set<Long> userFencesIds = new HashSet<>();
        User userFromDB = userService.getUserById(id);

        //TODO inefficient, change to SQL query
        if (ofNullable(userFromDB).isPresent()) {
            List<TrackedObj> userTrackedObjs = trackedObjService.getAllUserTrackedObjs(userFromDB.getUserId());
            for(TrackedObj t : userTrackedObjs) {
                userFencesIds.addAll(t.getFencesIds());
            }
           userFences = fenceRepository.findFencesByFenceIdIn(userFencesIds);
        }

        return userFences.stream()
                .map(fence -> modelMapper.map(fence, UserFencesDto.class))
                .collect(Collectors.toList());
    }
}
