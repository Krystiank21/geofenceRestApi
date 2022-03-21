package com.example.geofenceapp.model.dto;

import com.example.geofenceapp.model.Enums.TrackedObjType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class TrackedObjDto {
    private Long trackedObjId;
    private String name;
    private String surname;
    @Enumerated(EnumType.STRING)
    private TrackedObjType trackedObjType;
}