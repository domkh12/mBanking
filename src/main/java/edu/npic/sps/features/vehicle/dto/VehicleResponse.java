package edu.npic.sps.features.vehicle.dto;

import java.time.LocalDateTime;

public record VehicleResponse(
        String numberPlate,
        LocalDateTime timeIn,
        LocalDateTime timeOut,
        String vehicleModel,
        String vehicleDescription
) {
}
