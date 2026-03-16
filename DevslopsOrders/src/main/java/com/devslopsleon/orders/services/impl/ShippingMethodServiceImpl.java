package com.devslopsleon.orders.services.impl;


import com.devslopsleon.orders.core.dto.company.ShippingMethodDTO;
import com.devslopsleon.orders.core.models.company.ShippingMethod;
import com.devslopsleon.orders.core.repository.ShippingMethodRepository;
import com.devslopsleon.orders.mappers.company.ShippingMethodMapper;
import com.devslopsleon.orders.services.IShippingMethodService;
import com.devslopsleon.orders.services.auth.TenantContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingMethodServiceImpl implements IShippingMethodService {

    private final ShippingMethodMapper shippingMethodMapper;
    private final ShippingMethodRepository shippingMethodRepository;


    @Override
    public List<ShippingMethodDTO> listShippingMethods() {
        Long companyId = TenantContext.getCompanyPk();
        if (companyId == null) {
            throw new RuntimeException("Tenant not resolved");
        }

        List<ShippingMethod> shippings = shippingMethodRepository.listShippingMethodsByCompany(companyId);
        return shippingMethodMapper.toDtos(shippings);
    }

    @Override
    public ShippingMethodDTO findShippingMethodById(Long id) {
        return null;
    }

    public ShippingMethodServiceImpl(ShippingMethodMapper shippingMethodMapper, ShippingMethodRepository shippingMethodRepository) {
        this.shippingMethodMapper = shippingMethodMapper;
        this.shippingMethodRepository = shippingMethodRepository;
    }
}
