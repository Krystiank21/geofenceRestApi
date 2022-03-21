package com.example.geofenceapp.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "t_fence")
public class Fence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fenceId;
    private String fenceName;
    private Boolean isTracked;

    @ManyToMany(mappedBy = "fences", fetch = FetchType.EAGER)
    private Set<TrackedObj> trackedObjs;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "fence")
    private Set<Point> points;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Fence polygon = (Fence) o;
        return fenceId != null && Objects.equals(fenceId, polygon.fenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fenceId);
    }

    @Override
    public String toString() {
        return "Fence{" + "fenceId=" + fenceId + ", fenceName='" + fenceName + '\'' + ", isTracked=" + isTracked + '}';
    }
}