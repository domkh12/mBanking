package edu.npic.sps.features.parking;

import edu.npic.sps.features.parking.dto.ParkingResponse;
import org.springframework.data.domain.Page;

public interface ParkingService {
    Page<ParkingResponse> findByName (String name, int pageNo, int pageSize);

    void delete(String uuid);

    Page<ParkingResponse> findAll(int pageNo, int pageSize);
}
