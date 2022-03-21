package com.example.geofenceapp.model;

import com.example.geofenceapp.model.Enums.TrackedObjType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "t_tracked_object")
public class TrackedObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trackedObjId;
    private String name;
    private String surname;
    @Enumerated(EnumType.STRING)
    private TrackedObjType trackedObjType;
    private Boolean isTracked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "t_tracked_object_fences",
            joinColumns = @JoinColumn(name = "tracked_obj_id"),
            inverseJoinColumns = @JoinColumn(name = "fence_id"))
    @JsonIgnore
    private Collection<Fence> fences;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TrackedObj trackedObj = (TrackedObj) o;
        return trackedObjId != null && Objects.equals(trackedObjId, trackedObj.trackedObjId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackedObjId);
    }

    public List<Long> getFencesIds() {
        return getFences().stream().map(Fence::getFenceId).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "TrackedObj{" + "trackedObjId=" + trackedObjId + ", name='" + name + '\'' + ", surname='" + surname
                + '\'' + ", trackedObjType=" + trackedObjType + ", isTracked=" + isTracked + ", user=" + user + '}';
    }
}