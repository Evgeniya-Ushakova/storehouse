package com.evg.storehouse.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {

    private Long reservationId;
    private Long productId;
    private Long availableForReservationCount;
    private Long reservedCount;
    private Long orderId;
    private String status;

}
