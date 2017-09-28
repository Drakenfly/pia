package com.pia.core;

import com.pia.core.properties.CollectionType;
import com.pia.core.properties.ComplexType;
import com.pia.core.properties.DataType;
import com.pia.testing.*;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * DataType methods usually operate on
 * dangerously loose define objects.
 * This test just calls all methods,
 * revealing if any of them throw an
 * exception when they shouldn't.
 */
public class TypeMethodCallTest {
    private BaseTypeTestPlugin baseTypePlugin;
    private ArrayTypeTestPlugin arrayTypePlugin;
    private ListTypeTestPlugin listTypePlugin;
    private SimpleDataStorageTestPlugin simplePlugin;
    private ArrayDataStorageTestPlugin arrayPlugin;

    private Collection<Field> allFields;

    @Before
    public void setup() {
        baseTypePlugin = new BaseTypeTestPlugin();
        arrayTypePlugin = new ArrayTypeTestPlugin();
        listTypePlugin = new ListTypeTestPlugin();
        simplePlugin = new SimpleDataStorageTestPlugin();
        arrayPlugin = new ArrayDataStorageTestPlugin();

        allFields = new ArrayList<>();
        allFields.addAll(baseTypePlugin.getAnnotatedFields());
        allFields.addAll(arrayTypePlugin.getAnnotatedFields());
        allFields.addAll(listTypePlugin.getAnnotatedFields());
        allFields.addAll(simplePlugin.getAnnotatedFields());
        allFields.addAll(arrayPlugin.getAnnotatedFields());
    }

    @Test
    public void getContentType() throws IllegalAccessException {
        allDataTypes().forEach(type -> System.out.println(type.getContentType()));
    }

    @Test
    public void toStringImpl() throws IllegalAccessException {
        allDataTypes().forEach(type -> System.out.println(type.toString()));
    }

    @Test
    public void getValue() throws IllegalAccessException {
        allDataTypes().forEach(type -> System.out.println(type.getValue()));
    }

    @Test
    public void getCollectionChildDataType() throws IllegalAccessException {
        allDataTypes().stream()
                .filter(type -> type instanceof CollectionType)
                .forEach(complex -> System.out.println(
                        ((CollectionType)complex).getChildDataType()));
    }

    private Collection<DataType> allDataTypes() throws IllegalAccessException {
        List<DataType> dataTypes = new ArrayList<>();
        for (Field field : allFields) {
            dataTypes.add(DataType.getDataType(field));
        }
        return dataTypes;
    }
}
