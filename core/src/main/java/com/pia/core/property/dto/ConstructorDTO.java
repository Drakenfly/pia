package com.pia.core.property.dto;

import java.util.List;

public class ConstructorDTO {
    private List<DataTypeDTO> arguments;

    public List<DataTypeDTO> getArguments () {
        return arguments;
    }

    public void setArguments (List<DataTypeDTO> arguments) {
        this.arguments = arguments;
    }
}
