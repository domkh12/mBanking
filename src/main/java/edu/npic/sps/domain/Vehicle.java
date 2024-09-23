package edu.npic.sps.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, length = 50)
    private String numberPlate;
    @Column(nullable = false, length = 100)
    private LocalDateTime timeIn;
    @Column(length = 100)
    private LocalDateTime timeOut;

    // optional
    private String color;
    private String vehicleModel;
    private String vehicleDescription;

    private Boolean isDeleted;

    // relationship
    @OneToOne
    private ParkingSlot parkingSlot;

    @ManyToOne
    private VehicleType vehicleType;
}
