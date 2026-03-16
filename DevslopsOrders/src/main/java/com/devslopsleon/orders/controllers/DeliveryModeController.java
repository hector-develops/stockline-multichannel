package com.devslopsleon.orders.controllers;


import com.devslopsleon.orders.core.dto.company.DeliveryModeDTO;
import com.devslopsleon.orders.services.IDeliveryModeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deliverymode")
public class DeliveryModeController {

    private final IDeliveryModeService deliveryModeService;

    // ✅ Listar paginado (ADMINISTRATOR, MANAGEMENT, AGENT)
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT', 'SUPERADMIN')")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<?>> getDeliveriesModes(){
        List<DeliveryModeDTO> deliveries = deliveryModeService.deliveries();
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    // ✅ Listar paginado (ADMINISTRATOR, MANAGEMENT, AGENT)
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT', 'SUPERADMIN')")
    @RequestMapping(value = "/{dmId}", method = RequestMethod.GET)
    public ResponseEntity<?> getDeliveryModeById(@PathVariable Long dmId){
        DeliveryModeDTO dm = deliveryModeService.findDeliveryModeById(dmId);
        return new ResponseEntity<>(dm, HttpStatus.OK);
    }


    public DeliveryModeController(IDeliveryModeService deliveryModeService) {
        this.deliveryModeService = deliveryModeService;
    }
}
