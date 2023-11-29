package com.evg.storehouse.dto.request;

import com.evg.storehouse.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {

    private List<ProductDto> products;

}
