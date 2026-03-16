package com.devslopsleon.orders.mappers.order;


import com.devslopsleon.orders.core.dto.transactional.order.OrderDTO;
import com.devslopsleon.orders.core.models.ordersm.Order;
import com.devslopsleon.orders.mappers.WarehouseMapper;
import com.devslopsleon.orders.mappers.address.AddressMapper;
import com.devslopsleon.orders.mappers.address.DeliveryAddressMapper;
import com.devslopsleon.orders.mappers.address.DeliveryModeMapper;
import com.devslopsleon.orders.mappers.company.CompanyMapper;
import com.devslopsleon.orders.mappers.user.CustomerMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class, DeliveryModeMapper.class,
        AddressMapper.class, DeliveryAddressMapper.class, WarehouseMapper.class, CustomerMapper.class})
public interface OrderMapper {


    //Order toEntityModel(OrderDTO oDTO);

    @Mapping(source = "pk", target = "id")
    @Mapping(source = "company.uid", target = "company")
    @Mapping(source = "paymentStatus.code", target = "paymentStatus")
    @Mapping(source = "transactionStatus.code", target = "orderStatus")
    @Mapping(source = "createdBy.mail", target = "createdBy")
    @Mapping(source = "uniqueExternalId", target = "uniqueId")
    @Mapping(source = "amount", target = "total")
    OrderDTO toDTO(Order o);
}
