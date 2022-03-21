package com.example.geofenceapp.controller;

import com.example.geofenceapp.model.dto.TrackedObjDto;
import com.example.geofenceapp.model.dto.TrackedObjFenceDto;
import com.example.geofenceapp.services.ITrackedObjService;
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
@RequestMapping("/trackedObject")
public class TrackedObjController {

    private ITrackedObjService trackedObjService;

    /**
     * Add new trackedObject
     * */
    @PostMapping("/add-new-TrackedObj")
    public ResponseEntity<String> addNewTrackedObj(@RequestBody TrackedObjDto trackedObjDto) {
        try {
            trackedObjService.addNewTrackedObj(trackedObjDto);
            return new ResponseEntity<>("Tracked object has been created!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("The tracked object could not be created!");
        }
        return new ResponseEntity<>("The tracked object could not be created!", HttpStatus.NOT_FOUND);
    }

    /**
     * Add fence to trackedObj
     * */
    @PostMapping("/add-fence-to-trackedObj")
    public ResponseEntity<String> addFenceToTrackedObj(@RequestBody TrackedObjFenceDto trackedObjFenceDto) {
        try {
            trackedObjService.addNewFenceToTrackedObj(trackedObjFenceDto);
            return new ResponseEntity<>("Tracked object has been created!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("The tracked object could not be created!");
        }
        return new ResponseEntity<>("The tracked object could not be created!", HttpStatus.NOT_FOUND);
    }

    /**
     * Edit trackedObject's details
     * */
    @PatchMapping("/edit-details/{trackedObjId}")
    public ResponseEntity<String> editTrackedObjectDetails(@RequestBody TrackedObjDto trackedObjDto, @PathVariable Long trackedObjId) {
        trackedObjDto.setTrackedObjId(trackedObjId);
        try {
            trackedObjService.updateDetails(trackedObjDto);
            return new ResponseEntity<>("Tracked object details has been modified!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("The details could not be modified!");
        }
        return new ResponseEntity<>("The details could not be modified!", HttpStatus.NOT_FOUND);
    }

    /**
     * Turn ON tracking on trackedObject
     */
    @PatchMapping("/tracking-on/{trackedObjId}")
    public ResponseEntity<String> turnOnTracking( @PathVariable Long trackedObjId) {
        try {
            trackedObjService.changeTrackingToON(trackedObjId);
            return new ResponseEntity<>("Tracked object details has been modified!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("The details could not be modified!");
        }
        return new ResponseEntity<>("The details could not be modified!", HttpStatus.NOT_FOUND);
    }

    /**
     * Turn ON tracking on all trackedObjects
     */
    @PatchMapping("/all-tracking-on/{userId}")
    public ResponseEntity<String> turnOnTrackingForAllTrackedObjs(@PathVariable Long userId) {
        try {
            trackedObjService.changeTrackingToONForAllTrackedObjs(userId);
            return new ResponseEntity<>("Tracked object details has been modified!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("The details could not be modified!");
        }
        return new ResponseEntity<>("The details could not be modified!", HttpStatus.NOT_FOUND);
    }

    /**
     * Turn OFF tracking on trackedObject
     */
    @PatchMapping("/tracking-off/{trackedObjId}")
    public ResponseEntity<String> turnOffTracking(@PathVariable Long trackedObjId) {
        try {
            trackedObjService.changeTrackingToOFF(trackedObjId);
            return new ResponseEntity<>("Tracked object details has been modified!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("The details could not be modified!");
        }
        return new ResponseEntity<>("The details could not be modified!", HttpStatus.NOT_FOUND);
    }

    /**
     * Turn OFF tracking on all trackedObjects
     */
    @PatchMapping("/all-tracking-off/{userId}")
    public ResponseEntity<String> turnOFFTrackingForAllTrackedObjs(@PathVariable Long userId) {
        try {
            trackedObjService.changeTrackingToOFFForAllTrackedObjs(userId);
            return new ResponseEntity<>("Tracked object details has been modified!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("The details could not be modified!");
        }
        return new ResponseEntity<>("The details could not be modified!", HttpStatus.NOT_FOUND);
    }

    /**
     * Delete trackedObject
     * */
    @DeleteMapping("/delete/{trackedObjId}")
    public ResponseEntity<String> deleteTrackedObject(@PathVariable Long trackedObjId) {
        try {
                this.trackedObjService.deleteTrackedObj(trackedObjId);
                return new ResponseEntity<>("Tracked object deleted!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error during deleting the tracked object!");
        }
        return new ResponseEntity<>("Failed to delete the tracked object!", HttpStatus.NOT_FOUND);
    }

    /**
     * Delete fence from trackedObject
     * */
    @DeleteMapping("/delete-fence-from-trackedObj")
    public ResponseEntity<String> deleteFenceFromTrackedObj(@RequestBody TrackedObjFenceDto trackedObjFenceDto) {
        try {
            this.trackedObjService.deleteFenceFromTrackedObj(trackedObjFenceDto);
            return new ResponseEntity<>("The fence deleted from Tracked Obj!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error during deleting fence from Tracked Obj");
        }
        return new ResponseEntity<>("Failed to remove fence from Tracked Obj", HttpStatus.NOT_FOUND);
    }

    /**
     * Get all user's Tracked Objects
     */
    @GetMapping("/all-user-trackedObjs/{userId}")
    public List<TrackedObjDto> getAllUserFences(@PathVariable Long userId) {
        try {
            return trackedObjService.getAllUserTrackedObjDto(userId);
        } catch (Exception e) {
            System.out.println("Error during get all user's Tracked Objects!");
        }
        return Collections.emptyList();
    }
}