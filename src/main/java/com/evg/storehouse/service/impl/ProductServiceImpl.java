package com.evg.storehouse.service.impl;

import com.evg.storehouse.dto.ProductDto;
import com.evg.storehouse.dto.ReservationDto;
import com.evg.storehouse.dto.request.ReservationRequest;
import com.evg.storehouse.dto.response.ReservationResponse;
import com.evg.storehouse.entity.Product;
import com.evg.storehouse.entity.ProductReservation;
import com.evg.storehouse.entity.ReservationStatus;
import com.evg.storehouse.enums.ErrorMessageCode;
import com.evg.storehouse.exception.BadRequestException;
import com.evg.storehouse.repository.ProductRepository;
import com.evg.storehouse.repository.ProductReservationRepository;
import com.evg.storehouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j(topic = "PRODUCT_SERVICE")
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductReservationRepository reservationRepository;

    @Override
    @Transactional
    public ReservationResponse reserve(ReservationRequest request) {
        return new ReservationResponse(request.getProducts().stream()
                .map(this::createReservation)
                .toList());
    }

    @Override
    @Transactional
    public ReservationResponse cancelReserved(ReservationRequest request) {

        List<ReservationDto> reservationDtos = request.getProducts().stream()
                .map(productDto -> {

                    ProductReservation reservation = reservationRepository.findByProductIdAndOrderIdOrElseThrow(productDto.getProductId(), productDto.getOrderId());
                    reservation.setStatus(ReservationStatus.CANCELLED);

                    reservationRepository.save(reservation);

                    Product product = productRepository.findByIdOrElseThrow(productDto.getProductId());
                    product.setCount(product.getCount() + productDto.getCount());

                    productRepository.save(product);

                    ReservationDto reservationDto = new ReservationDto();
                    reservationDto.setReservationId(reservation.getId());
                    reservationDto.setReservedCount(0L);
                    reservationDto.setProductId(productDto.getProductId());
                    reservationDto.setOrderId(productDto.getOrderId());
                    reservationDto.setAvailableForReservationCount(product.getCount());
                    reservationDto.setStatus(reservation.getStatus().name());
                    return reservationDto;

                })
                .toList();
        return new ReservationResponse(reservationDtos);
    }

    private ReservationDto createReservation(ProductDto productDto) {
        Product product = productRepository.findByIdOrElseThrow(productDto.getProductId());

        validReservationAvailable(product.getCount(), productDto.getCount());
        ProductReservation productReservation = createReservation(product, productDto);

        product.setCount(product.getCount() - productDto.getCount());
        productRepository.save(product);

        return buildReservationDto(productReservation, product.getCount());
    }

    private void validReservationAvailable(Long currentProductCount, Long reservationCount) {
        if (reservationCount > currentProductCount) {
            throw new BadRequestException(ErrorMessageCode.BAD_REQUEST.getCode(),
                    String.format("Product count in request more than count on the storehouse. Request count: %s, storehouse count = %s",
                            reservationCount, currentProductCount));
        }
    }

    private ProductReservation createReservation(Product product, ProductDto productDto) {
        ProductReservation productReservation = new ProductReservation();
        productReservation.setProduct(product);
        productReservation.setCount(productDto.getCount());
        productReservation.setOrderId(productDto.getOrderId());
        productReservation.setStatus(ReservationStatus.RESERVED);
        return reservationRepository.save(productReservation);
    }

    private ReservationDto buildReservationDto(ProductReservation productReservation, Long currentProductCount) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setProductId(reservationDto.getProductId());
        reservationDto.setReservationId(productReservation.getId());
        reservationDto.setReservedCount(productReservation.getCount());
        reservationDto.setAvailableForReservationCount(currentProductCount);
        reservationDto.setOrderId(productReservation.getOrderId());
        return reservationDto;
    }



}
