package edu.npic.sps.features.parking;

import edu.npic.sps.domain.Parking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Integer> {
//    boolean existsByParkingNameContainsIgnoreCase(String parkingName);
    Optional<Parking> findByUuid(String uuid);

    Page<Parking> findByParkingNameContainsIgnoreCaseAndIsDeletedFalse(String parkingName, Pageable pageable);


    @Query("select p from Parking p where p.isDeleted = false")
    Page<Parking> findByIsDeletedFalse(Pageable pageable);


}
