package edu.npic.sps.mapper;

import edu.npic.sps.domain.Parking;
import edu.npic.sps.features.parking.dto.ParkingResponse;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface ParkingMapper {
    ParkingResponse  toParkingResponse(Parking parking);
}
