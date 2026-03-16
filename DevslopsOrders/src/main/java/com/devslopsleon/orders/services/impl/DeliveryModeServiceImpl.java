package com.devslopsleon.orders.services.impl;


import com.devslopsleon.orders.core.dto.company.DeliveryModeDTO;
import com.devslopsleon.orders.core.dto.company.DeliveryModeRequestDTO;
import com.devslopsleon.orders.core.models.types.mov.DeliveryMode;
import com.devslopsleon.orders.core.repository.DeliveryModeRepository;
import com.devslopsleon.orders.mappers.address.DeliveryModeMapper;
import com.devslopsleon.orders.services.IDeliveryModeService;
import com.devslopsleon.orders.services.auth.TenantContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryModeServiceImpl implements IDeliveryModeService {

    private final DeliveryModeRepository deliveryModeRepository;
    private final DeliveryModeMapper deliveryModeMapper;

    @Override
    public DeliveryModeDTO addDeliveryMode(final DeliveryModeRequestDTO dm) {
        return null;
    }

    @Override
    public List<DeliveryModeDTO> deliveries() {
        Long companyId = TenantContext.getCompanyPk();
        if (companyId == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        List<DeliveryMode> deliveryModeList = deliveryModeRepository.findDeliveriesByCompanyId(companyId);
        List<DeliveryModeDTO> dtos = new ArrayList<>();
        deliveryModeList.forEach(
                x->{
                    dtos.add(deliveryModeMapper.toDTO(x));
                }
        );
        return dtos;
    }

    @Override
    public DeliveryModeDTO findDeliveryModeByCode(String delivery) {
        return null;
    }

    @Override
    public String updateDeliveryMode(final DeliveryModeDTO deliveryModeDTO) {
        return "";
    }

    @Override
    public DeliveryModeDTO findDeliveryModeById(Long id) {
        Long companyId = TenantContext.getCompanyPk();
        if (companyId == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        Optional<DeliveryMode> dm = deliveryModeRepository.findDeliveryModeById(id, companyId);
        if(dm.isEmpty()){
            throw new RuntimeException("Delivery mode not found");
        }
        return deliveryModeMapper.toDTO(dm.get());
    }

    private boolean validateExistDeliveryMode(String dm, Long companyId){
        return true;
    }

    public DeliveryModeServiceImpl(DeliveryModeRepository deliveryModeRepository, DeliveryModeMapper deliveryModeMapper) {
        this.deliveryModeRepository = deliveryModeRepository;
        this.deliveryModeMapper = deliveryModeMapper;
    }
}
