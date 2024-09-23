package edu.npic.sps.features.parking;

import edu.npic.sps.features.parking.dto.ParkingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @GetMapping("/{name}")
    Page<ParkingResponse> findByName(@PathVariable String name,  @RequestParam(required = false, defaultValue = "1") int pageNo, @RequestParam(required = false, defaultValue = "10") int pageSize){
        return  parkingService.findByName(name, pageNo, pageSize);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    Page<ParkingResponse> findAll(@RequestParam(required = false, defaultValue = "1") int pageNo,
                                  @RequestParam(required = false, defaultValue = "20") int pageSize){
        return parkingService.findAll(pageNo, pageSize);

    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    void delete(@PathVariable String uuid){
        parkingService.delete(uuid);
    }
}
