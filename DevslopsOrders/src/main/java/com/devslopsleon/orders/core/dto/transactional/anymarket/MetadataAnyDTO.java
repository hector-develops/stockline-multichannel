package com.devslopsleon.orders.core.dto.transactional.anymarket;

import java.io.Serializable;

public class MetadataAnyDTO implements Serializable {

    private String system_origin;

    public String getSystem_origin() {
        return system_origin;
    }

    public void setSystem_origin(String system_origin) {
        this.system_origin = system_origin;
    }
}
