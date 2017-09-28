package com.pia.core.properties_old;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * ComplexDataType stores all Objects that could
 * not be instantiated in other DataType implementations.
 * Accessing them is especially difficult since the internal
 * structure of the matching field's class could be
 * complex as well as generic or just contain interfaces.
 *
 * @param <T> TODO TBD if it is actually needed
 */
public class ComplexDataType<T> extends DataType {
    /**
     * Contains the object's member fields and their
     * corresponding DataTypes.
     * TODO TBD if a reference to "Field" is needed, since the field is stored in the DataType anyway
     */
    Map<Field, DataType> members;

    @Deprecated
    protected ComplexDataType (Field ownField, Class contentClass) {
        super(ownField);
    }

    @Override
    public String toString () {
        return "Complex of Type " + ownField.getType().getName();
    }

    protected ComplexDataType (@NotNull Field ownField) {
        this(ownField, ownField.getType());
    }
}
