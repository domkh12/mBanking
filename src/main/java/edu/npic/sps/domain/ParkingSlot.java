package edu.npic.sps.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "parking-slots")
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true, length = 20)
    private String slotName;
    @Column(nullable = false)
    private Boolean isAvailable;
    private Boolean isDeleted;

    @OneToOne
    private Vehicle vehicle;

    @ManyToOne
    private Parking parking;
}
