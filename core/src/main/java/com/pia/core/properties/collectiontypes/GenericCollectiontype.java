package com.pia.core.properties.collectiontypes;

import com.pia.core.properties.CollectionType;
import com.pia.core.properties.DataType;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class GenericCollectiontype<T extends DataType> extends CollectionType<T> {

    public GenericCollectiontype (Field ownField) throws IllegalAccessException {
        super(ownField);
    }

    public GenericCollectiontype (Class ownType) throws IllegalAccessException {
        super(ownType);
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
}
