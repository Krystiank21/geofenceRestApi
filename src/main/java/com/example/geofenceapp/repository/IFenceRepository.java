package com.example.geofenceapp.repository;

import com.example.geofenceapp.model.Fence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Set;

public interface IFenceRepository extends JpaRepository<Fence, Long> {
    Fence findFenceByFenceId (Long fenceId);
    List<Fence> findFencesByFenceIdIn(Set<Long> fenceId);
    void deleteFenceByFenceId(Long fenceId);
}
