package com.example.geofenceapp.services.Impl;

import com.example.geofenceapp.model.Fence;
import com.example.geofenceapp.model.TrackedObj;
import com.example.geofenceapp.model.User;
import com.example.geofenceapp.model.dto.TrackedObjDto;
import com.example.geofenceapp.model.dto.TrackedObjFenceDto;
import com.example.geofenceapp.repository.IFenceRepository;
import com.example.geofenceapp.repository.ITrackedObjRepository;
import com.example.geofenceapp.services.ITrackedObjService;
import com.example.geofenceapp.services.IUserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@AllArgsConstructor
@Service
public class TrackedObjService implements ITrackedObjService {

    private final IUserService userService;
    private final ITrackedObjRepository trackedObjRepository;
    private final IFenceRepository fenceRepository;
    private final ModelMapper modelMapper;

    @Override
    public void addNewTrackedObj(TrackedObjDto trackedObjDto) {
        TrackedObj newTrackedObj = modelMapper.map(trackedObjDto, TrackedObj.class);
        trackedObjRepository.save(newTrackedObj);
    }

    @Override
    public void updateDetails(TrackedObjDto trackedObjDto) {
        TrackedObj trackedObj = this.trackedObjRepository.findTrackedObjByTrackedObjId(trackedObjDto.getTrackedObjId());
        //TODO
        trackedObjRepository.save(trackedObj);
    }

    @Override
    public void deleteTrackedObj(Long id) {
        this.trackedObjRepository.deleteTrackedObjByTrackedObjId(id);
    }

    @Override
    public void changeTrackingToON(Long trackedObjId) {
        changeTracking(trackedObjId, true);
    }

    @Override
    public void changeTrackingToOFF(Long trackedObjId) {
        changeTracking(trackedObjId, false);
    }

    private void changeTracking(Long trackedObjId, boolean isTracked){
        TrackedObj trackedObjToChange = trackedObjRepository.findTrackedObjByTrackedObjId(trackedObjId);
        trackedObjToChange.setIsTracked(isTracked);
        trackedObjRepository.save(trackedObjToChange);
    }

    @Override
    public void addNewFenceToTrackedObj(TrackedObjFenceDto trackedObjFenceDto) {
        TrackedObj trackedObj = trackedObjRepository.findTrackedObjByTrackedObjId(trackedObjFenceDto.getTrackedObjId());
        Fence fenceToAdd = fenceRepository.findFenceByFenceId(trackedObjFenceDto.getFenceId());

        //TODO przeniesc do encji
        trackedObj.getFences().add(fenceToAdd);
        trackedObjRepository.save(trackedObj);
    }

    @Override
    public void deleteFenceFromTrackedObj(TrackedObjFenceDto trackedObjFenceDto) {
        //TODO
    }

    @Override
    public List<TrackedObj> getAllUserTrackedObjs(Long userId) {
        return trackedObjRepository.getAllByUserUserId(userId);

    }

    @Override
    public List<TrackedObjDto> getAllUserTrackedObjDto(Long userId) {
        List<TrackedObj> userTrackedObjs = trackedObjRepository.getAllByUserUserId(userId);

        return userTrackedObjs.stream()
                .map(trackedObj -> modelMapper.map(trackedObj, TrackedObjDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void changeTrackingToONForAllTrackedObjs(Long userId) {
        User userFromDB = userService.getUserById(userId);
        if (ofNullable(userFromDB).isPresent()) {
            List<TrackedObj> userTrackedObjs = getAllUserTrackedObjs(userId);
            List<TrackedObj> userTrackedObjsToChange =  userTrackedObjs.stream().filter(trackedObj -> !trackedObj.getIsTracked()).collect(Collectors.toList());
            userTrackedObjsToChange.forEach(trackedObj -> trackedObj.setIsTracked(true));
            trackedObjRepository.saveAll(userTrackedObjsToChange);
        }
    }

    @Override
    public void changeTrackingToOFFForAllTrackedObjs(Long userId) {
        User userFromDB = userService.getUserById(userId);
        if (ofNullable(userFromDB).isPresent()) {
            List<TrackedObj> userTrackedObjs = getAllUserTrackedObjs(userId);
            List<TrackedObj> userTrackedObjsToChange =  userTrackedObjs.stream().filter(TrackedObj::getIsTracked).collect(Collectors.toList());
            userTrackedObjsToChange.forEach(trackedObj -> trackedObj.setIsTracked(false));
            trackedObjRepository.saveAll(userTrackedObjsToChange);
        }
    }
}
