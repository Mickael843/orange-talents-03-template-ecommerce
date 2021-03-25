package com.mikkaeru.ecommerce.dto.out.characteristic;

import com.mikkaeru.ecommerce.model.characteristic.Characteristic;

public class CharacteristicResponse {

    private String name;
    private String description;

    public CharacteristicResponse(Characteristic characteristic) {
        this.name = characteristic.getName();
        this.description = characteristic.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
