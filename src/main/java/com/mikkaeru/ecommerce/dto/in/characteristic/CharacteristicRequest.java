package com.mikkaeru.ecommerce.dto.in.characteristic;

import javax.validation.constraints.NotBlank;

public class CharacteristicRequest {

    private final @NotBlank String name;
    private final @NotBlank String description;

    public CharacteristicRequest(@NotBlank String name, @NotBlank String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
