package com.evg.storehouse.dto.response;

import com.evg.storehouse.dto.BaseResponse;
import com.evg.storehouse.dto.ReservationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse extends BaseResponse {

    private List<ReservationDto> reservationProducts;

}
