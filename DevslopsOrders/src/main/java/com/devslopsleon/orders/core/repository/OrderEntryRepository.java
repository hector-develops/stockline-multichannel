package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.ordersm.OrderEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEntryRepository extends JpaRepository<OrderEntry, Long> {
}
