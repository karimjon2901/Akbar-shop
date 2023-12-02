package com.handiwork.service.impl;

import com.handiwork.dto.OrdersDto;
import com.handiwork.dto.OrdersInputDto;
import com.handiwork.dto.ProductOrderDto;
import com.handiwork.dto.ResponseDto;
import com.handiwork.entity.Orders;
import com.handiwork.entity.Product;
import com.handiwork.repository.OrdersRepository;
import com.handiwork.repository.ProductOrderRepository;
import com.handiwork.repository.ProductRepository;
import com.handiwork.service.AssessmentsService;
import com.handiwork.service.IdGenerator;
import com.handiwork.service.OrdersService;
import com.handiwork.service.mapper.OrdersMapper;
import com.handiwork.service.mapper.ProductOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.handiwork.status.AppStatusMessage.*;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
    public final OrdersMapper ordersMapper;
    private final ProductRepository productRepository;
    private final AssessmentsService assessmentsService;
    private final IdGenerator idGenerator;
    private final ProductOrderRepository productOrderRepository;
    private final ProductOrderMapper productOrderMapper;

    @Override
    public ResponseDto<OrdersDto> add(OrdersInputDto ordersInputDto) {
        try {
            OrdersDto ordersDto = new OrdersDto();
            double total=0D;
            for (ProductOrderDto product : ordersInputDto.getProducts()) {
                Product pr = productRepository.findById(product.getProductId()).get();
                pr.setAmount(pr.getAmount()-product.getCount());
                total+=(pr.getPrice() * product.getCount());
                Integer ass = assessmentsService.calculate(product.getProductId(), product.getAssessments());
                pr.setAssessments(ass);
                product.setId(idGenerator.generate());
                product.setAssessments(ass);
                productOrderRepository.save(productOrderMapper.toEntity(product));
            }
            ordersDto.setProducts(ordersInputDto.getProducts());
            ordersDto.setTotalPrice(total);
            ordersDto.setId(idGenerator.generate());
            ordersRepository.save(ordersMapper.toEntity(ordersDto));
            return ResponseDto.<OrdersDto>builder()
                    .message(OK)
                    .data(ordersDto)
                    .success(true)
                    .build();
        }catch (Exception e){
            return ResponseDto.<OrdersDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<OrdersDto> getById(String id) {
        if (id == null){
            return ResponseDto.<OrdersDto>builder()
                    .message(NULL_ID)
                    .build();
        }

        try {
            Optional<Orders> byId = ordersRepository.findById(id);
            if (byId.isEmpty()){
                return ResponseDto.<OrdersDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }
            return ResponseDto.<OrdersDto>builder()
                    .message(OK)
                    .success(true)
                    .data(ordersMapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<OrdersDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<Page<OrdersDto>> getAll(Integer size, Integer page) {
        Long count = ordersRepository.count();

        PageRequest pageRequest = PageRequest.of(
                (count / size) <= page ?
                        (count % size == 0 ? (int) (count / size) - 1
                                : (int) (count / size))
                        : page,
                size
        );

        try {
            org.springframework.data.domain.Page<OrdersDto> all = ordersRepository.findAll(pageRequest).map(ordersMapper::toDto);

            return ResponseDto.<Page<OrdersDto>>builder()
                    .success(true)
                    .message(OK)
                    .data(all)
                    .build();
        } catch (Exception e){
            return ResponseDto.<Page<OrdersDto>>builder()
                    .message(DATABASE_ERROR + " -> " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<OrdersDto> update(OrdersInputDto ordersInputDto) {
        if (ordersInputDto.getId() == null){
            return ResponseDto.<OrdersDto>builder()
                    .message(NULL_ID)
                    .build();
        }

        try {
            Optional<Orders> byId = ordersRepository.findById(ordersInputDto.getId());
            if (byId.isEmpty()){
                return ResponseDto.<OrdersDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            OrdersDto ordersDto = ordersMapper.toDto(byId.get());
            double total=0D;
            for (ProductOrderDto product : ordersInputDto.getProducts()) {
                Product pr = productRepository.findById(product.getId()).get();
                pr.setAmount(pr.getAmount()-product.getCount());
                total+=(pr.getPrice() * product.getCount());
                Integer ass = assessmentsService.calculate(product.getId(), product.getAssessments());
                pr.setAssessments(ass);
                product.setAssessments(ass);
            }
            ordersDto.setProducts(ordersInputDto.getProducts());
            ordersDto.setTotalPrice(total);
            ordersRepository.save(ordersMapper.toEntity(ordersDto));
            return ResponseDto.<OrdersDto>builder()
                    .message(OK)
                    .data(ordersDto)
                    .success(true)
                    .build();
        } catch (Exception e){
            return ResponseDto.<OrdersDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<OrdersDto> delete(String id) {
        if (id == null){
            return ResponseDto.<OrdersDto>builder()
                    .message(NULL_ID)
                    .build();
        }

        try {
            Optional<Orders> byId = ordersRepository.findById(id);
            if (byId.isEmpty()){
                return ResponseDto.<OrdersDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            ordersRepository.deleteById(id);
            return ResponseDto.<OrdersDto>builder()
                    .message(OK)
                    .success(true)
                    .data(ordersMapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<OrdersDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }
}
