package com.pia.core.properties.collectiontypes;

import com.pia.core.properties.CollectionType;
import com.pia.core.properties.ConstructableType;
import com.pia.core.properties.DataType;
import com.pia.core.properties.PiaConstructor;

import javax.xml.crypto.Data;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class GenericCollectionType<T extends DataType> extends CollectionType<T> implements ConstructableType {
    private final List<PiaConstructor> ownConstructors;
    private PiaConstructor chosenConstructor;
    private List<DataType> chosenArguments;

    public GenericCollectionType (Field ownField) throws IllegalAccessException {
        super(ownField);
        ownConstructors = PiaConstructor.getAllPiaConstructors(ownField.getType());
        findDefaultConstructor();
    }

    public GenericCollectionType (Class<?> ownClass, Type ownType) throws IllegalAccessException {
        super(ownClass, ownType);
        ownConstructors = PiaConstructor.getAllPiaConstructors(ownClass);
        findDefaultConstructor();
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

    @Override
    public void setChosenConstructor (PiaConstructor constructor) {
        chosenConstructor = constructor;
    }

    @Override
    public PiaConstructor getChosenConstructor () {
        return chosenConstructor;
    }

    @Override
    public void setChosenArgumens (List<DataType> arguments) {
        chosenArguments = arguments;
    }

    @Override
    public List<DataType> getChosenArgumens () {
        return chosenArguments;
    }

    private void findDefaultConstructor() {
        for (PiaConstructor constructor : ownConstructors) {
            if (constructor.isEmptyConstructor()) {
                chosenConstructor = constructor;
                chosenArguments = new LinkedList<>();
                break;
            }
        }
    }
}
