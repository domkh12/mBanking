package edu.npic.sps.features.parking;

import edu.npic.sps.domain.Parking;
import edu.npic.sps.features.parking.dto.ParkingResponse;
import edu.npic.sps.mapper.ParkingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {

    private final ParkingRepository parkingRepository;
    private final ParkingMapper parkingMapper;


    @Override
    public Page<ParkingResponse> findByName(String name, int pageNo, int pageSize) {
        if (pageNo < 1 || pageSize < 1) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page number or page size must be greater than zero"
            );
        }

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<Parking> parkingNotDeleted = parkingRepository.findByParkingNameContainsIgnoreCaseAndIsDeletedFalse(name, pageRequest);
        return parkingNotDeleted.map(parkingMapper::toParkingResponse);
    }

    @Override
    public void delete(String uuid) {
        Parking parking = parkingRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parking not found")
        );
        parking.setIsDeleted(true);
        parkingRepository.save(parking);
    }

    @Override
    public Page<ParkingResponse> findAll(int pageNo, int pageSize) {
        if (pageNo < 1 || pageSize < 1) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page number or page size must be greater than zero"
            );
        }

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<Parking> parkingNotDeleted = parkingRepository.findByIsDeletedFalse(pageRequest);
        return parkingNotDeleted.map(parkingMapper::toParkingResponse);
    }
}
