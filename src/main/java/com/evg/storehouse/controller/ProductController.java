package com.evg.storehouse.controller;

import com.evg.storehouse.dto.request.ReservationRequest;
import com.evg.storehouse.dto.response.ReservationResponse;
import com.evg.storehouse.service.ProductService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("")
    public ReservationResponse reserve(@RequestBody ReservationRequest request) {

    }

}
