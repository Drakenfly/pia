package com.pia.core.properties.collectiontypes;

import com.pia.core.properties.CollectionType;
import com.pia.core.properties.ConstructableType;
import com.pia.core.properties.DataType;
import com.pia.core.properties.PiaConstructor;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class GenericCollectionType<T extends DataType> extends CollectionType<T> implements ConstructableType {
    private final List<PiaConstructor> ownConstructors;

    public GenericCollectionType (Field ownField) throws IllegalAccessException {
        super(ownField);
        ownConstructors = PiaConstructor.getAllPiaConstructors(ownField.getType());
    }

    public GenericCollectionType (Class<?> ownClass, Type ownType) throws IllegalAccessException {
        super(ownClass, ownType);
        ownConstructors = PiaConstructor.getAllPiaConstructors(ownClass);
    }

    @Override
    public String getContentType () {
        return "Collection<" + childDataType.getContentType() + ">";
    }

    @Override
    public Collection<Object> getValue () {
        List<Object> valueList = new LinkedList<>();
        for (DataType child : children) {
            valueList.add(child.getValue());
        }
        return valueList;
    }

    @Override
    public List<PiaConstructor> getConstructors () {
        return ownConstructors;
    }
}
