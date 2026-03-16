package com.devslopsleon.orders.controllers;


import com.devslopsleon.orders.core.dto.transactional.anymarket.OrderAnyDTO;
import com.devslopsleon.orders.core.dto.transactional.order.OrderDTO;
import com.devslopsleon.orders.core.dto.transactional.order.OrderOptionsDTO;
import com.devslopsleon.orders.core.dto.transactional.order.OrderRequestDTO;
import com.devslopsleon.orders.core.dto.transactional.producteca.OrderPtecaDTO;
import com.devslopsleon.orders.services.IOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {

    private final IOrderService orderService;

    @RequestMapping(value = "/{pk}", method = RequestMethod.GET)
    public ResponseEntity<?> findOrderByID(){

        OrderDTO order = orderService.findOrderById(10L);

        Map<String, String> jsonResponse = new HashMap<>();
        jsonResponse.put("Status", "created");
        return new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);

    }

    //Create order from StockLine APP
    @RequestMapping(value = "/{warehouseCode}", method = RequestMethod.POST)
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO orderDTO,
                                         @PathVariable final String warehouseCode){

        Map<String, String> jsonResponse = new HashMap<>();
        OrderDTO order = orderService.createOrder(orderDTO, warehouseCode);
        jsonResponse.put("Status", "created");
        return new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.PUT)
    public ResponseEntity<?>cancelOrder(@RequestBody OrderOptionsDTO order,
                                        @PathVariable final Long orderId){

        Map<String, String> jsonResponse = new HashMap<>();
        orderService.cancelOrder(order, orderId);
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

    // ✅ List paginado + filtros (tenant-safe en service)
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT', 'SUPERADMIN')")
    @GetMapping
    public Page<OrderDTO> listOrders(
            @RequestParam(value = "from", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,

            @RequestParam(value = "to", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,

            @RequestParam(value = "id", required = false) Long orderId,

            @PageableDefault(size = 20, sort = "modifiedTime", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return orderService.listOrdersPageable(from, to, orderId, pageable);
    }



    @RequestMapping(value = "/producteca", method = RequestMethod.POST)
    public ResponseEntity<?> createOrderFromIntegration(@RequestBody OrderPtecaDTO orderDTO){

        //TODO case producteca
        //Company c = companyFacade.addCompanyByModel(company);

        return null;
        //return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/anymarket", method = RequestMethod.POST)
    public ResponseEntity<?> createOrderFromIntegrationAny(@RequestBody OrderAnyDTO orderDTO
                                                           ){

        //TODO case anymarket
        return null;
        //return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    public OrdersController(IOrderService orderService) {
        this.orderService = orderService;
    }
}
