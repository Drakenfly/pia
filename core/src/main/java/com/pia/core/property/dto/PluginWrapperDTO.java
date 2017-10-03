package com.pia.core.property.dto;

import java.util.List;

public class PluginWrapperDTO {
    private String canonicalClassName;
    private List<DataTypeDTO> fieldValues;

    public PluginWrapperDTO (String canonicalClassName, List<DataTypeDTO> fieldValues) {
        this.canonicalClassName = canonicalClassName;
        this.fieldValues = fieldValues;
    }

    public String getCanonicalClassName () {
        return canonicalClassName;
    }

    public void setCanonicalClassName (String canonicalClassName) {
        this.canonicalClassName = canonicalClassName;
    }

    public List<DataTypeDTO> getFieldValues () {
        return fieldValues;
    }

    public void setFieldValues (List<DataTypeDTO> fieldValues) {
        this.fieldValues = fieldValues;
    }
}
