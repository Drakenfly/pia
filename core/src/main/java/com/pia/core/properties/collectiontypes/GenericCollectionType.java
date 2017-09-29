package com.pia.core.properties.collectiontypes;

import com.pia.core.properties.CollectionType;
import com.pia.core.properties.ConstructableType;
import com.pia.core.properties.DataType;
import com.pia.core.properties.PiaConstructor;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.crypto.Data;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
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
    public Collection<Object> getValue () throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Collection valueList;

        if (isInterfaceOrAbstract()) {
            if (ownClass.isAssignableFrom(LinkedList.class)) {
            /* note that the order of "isAssignableFrom" is
            different that what is used in the method isCollection.
             * "Collection.class.isAssignableFrom(clazz)" is the
             * equivalent of "clazz instanceof Collection".
             * But here we want to know if
             * "LinkedList instanceof ownClass" meaning
             * "Is it OK to assign LinkedList to our class". */

                valueList = new LinkedList();
            }
            else if (ownClass.isAssignableFrom(HashSet.class)) {
                valueList = new HashSet();
            }
            else {
                throw new IllegalArgumentException("Collection cannot be instantiated. No implementing class is known for interface " + ownClass.getSimpleName());
            }
        }
        else if (chosenConstructor == null || chosenArguments == null) {
            throw new IllegalStateException("You must choose a constructor and arguments first, before instantiating an instance");
        }
        else {
            DataType[] args = new DataType[chosenArguments.size()];
            args = chosenArguments.toArray(args);
            valueList = (Collection<?>) chosenConstructor.invoke(args);
        }

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
