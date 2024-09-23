package edu.npic.sps.features.parking.dto;

public record ParkingResponse(
        String uuid,
        String parkingName,
        Integer slotQty,
        String latitude,
        String longitude,
        String lastupdate
) {
}
