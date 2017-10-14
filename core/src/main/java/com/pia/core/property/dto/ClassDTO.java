package com.pia.core.property.dto;

import java.util.List;

public class ClassDTO<T> {
    private String canonicalClassname;
    private List<DataTypeDTO> constructorArguments;
    private List<DataTypeDTO> publicFields;

    public String getCanonicalClassname () {
        return canonicalClassname;
    }

    public void setCanonicalClassname (String canonicalClassname) {
        this.canonicalClassname = canonicalClassname;
    }

    public List<DataTypeDTO> getConstructorArguments () {
        return constructorArguments;
    }

    public void setConstructorArguments (List<DataTypeDTO> constructorArguments) {
        this.constructorArguments = constructorArguments;
    }

    public List<DataTypeDTO> getPublicFields () {
        return publicFields;
    }

    public void setPublicFields (List<DataTypeDTO> publicFields) {
        this.publicFields = publicFields;
    }
}
