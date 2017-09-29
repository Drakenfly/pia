package com.pia.core.properties;

import java.util.List;

public interface ConstructableType {
    List<PiaConstructor> getConstructors();
    void setChosenConstructor (PiaConstructor constructor);
    PiaConstructor getChosenConstructor() throws IllegalAccessException;
    void setChosenArgumens(List<DataType> arguments);
    List<DataType> getChosenArgumens();
}
