package com.devslopsleon.orders.services;

import com.devslopsleon.orders.core.dto.transactional.anymarket.OrderAnyDTO;
import com.devslopsleon.orders.core.dto.transactional.order.OrderDTO;
import com.devslopsleon.orders.core.dto.transactional.order.OrderOptionsDTO;
import com.devslopsleon.orders.core.dto.transactional.order.OrderRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface IOrderService {

    OrderDTO createOrder(final OrderRequestDTO order, final String warehouse);

    void cancelOrder(final OrderOptionsDTO order, final Long orderId);

    Page<OrderDTO> listOrdersPageable(LocalDate from, LocalDate to, Long orderId, Pageable pageable);

    List<OrderDTO> listOrdersByCompany();

    OrderDTO findOrderById(Long id);

    String createOrderAnymarket(final OrderAnyDTO orderAnyDTO);

    boolean deleteOrder(Long id);

}
