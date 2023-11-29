package com.evg.storehouse.service;

import com.evg.storehouse.dto.request.ReservationRequest;
import com.evg.storehouse.dto.response.ReservationResponse;

public interface ProductService {

    ReservationResponse reserve(ReservationRequest request);

    ReservationResponse cancelReserved(ReservationRequest request);

}
