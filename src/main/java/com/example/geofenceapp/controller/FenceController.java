package com.example.geofenceapp.controller;

import com.example.geofenceapp.model.dto.FenceDto;
import com.example.geofenceapp.model.dto.UserFencesDto;
import com.example.geofenceapp.services.IFenceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/fence")
public class FenceController {

    private final IFenceService fenceService;

    /**
     * Add new fence
     */
    @PostMapping("/add-fence")
    public ResponseEntity<String> addNewFence(@RequestBody FenceDto fenceDto) {
        try {
            fenceService.addNewFence(fenceDto);
            return new ResponseEntity<>("Fence has been created!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("The Fence could not be created!");
        }
        return new ResponseEntity<>("The Fence could not be created!", HttpStatus.NOT_FOUND);
    }

    /**
     * Edit fence details
     */
    @PatchMapping("/edit-details/{fenceId}")
    public ResponseEntity<String> editFenceDetails(@RequestBody FenceDto fenceDto, @PathVariable Long fenceId) {
        fenceDto.setFenceId(fenceId);
        try {
            fenceService.updateDetails(fenceDto);
            return new ResponseEntity<>("Fence details has been modified!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("The details could not be modified!");
        }
        return new ResponseEntity<>("The details could not be modified!", HttpStatus.NOT_FOUND);
    }

    /**
     * Turn ON tracking on fence
     */
    @PatchMapping("/tracking-on/{fenceId}")
    public ResponseEntity<String> turnOnFenceTracking(@PathVariable Long fenceId) {
        try {
            fenceService.changeTrackingToON(fenceId);
            return new ResponseEntity<>("Fence details has been modified!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("The details could not be modified!");
        }
        return new ResponseEntity<>("The details could not be modified!", HttpStatus.NOT_FOUND);
    }

    /**
     * Turn OFF tracking on fence
     */
    @PatchMapping("/tracking-off/{fenceId}")
    public ResponseEntity<String> turnOffFenceTracking(@PathVariable Long fenceId) {
        try {
            fenceService.changeTrackingToOFF(fenceId);
            return new ResponseEntity<>("Fence details has been modified!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("The details could not be modified!");
        }
        return new ResponseEntity<>("The details could not be modified!", HttpStatus.NOT_FOUND);
    }

    /**
     * Delete fence
     */
    @DeleteMapping("/delete/{fenceId}")
    public ResponseEntity<String> deleteFence(@PathVariable Long fenceId) {
        try {
            this.fenceService.deleteFence(fenceId);
            return new ResponseEntity<>("Fence has been deleted!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Failed to delete the Fence!");
        }
        return new ResponseEntity<>("Failed to delete the Fence!", HttpStatus.NOT_FOUND);
    }

    /**
     * Get all user's fences
     */
    @GetMapping("/all-user-fences/{userId}")
    public List<UserFencesDto> getAllUserFences(@PathVariable Long userId) {
        try {
            return fenceService.getAllUserFences(userId);
        } catch (Exception e) {
            System.out.println("Error during Get all user's fences");
        }
        return Collections.emptyList();
    }
}
