package com.pia.core.properties;

import com.pia.core.properties.primitiveArrays.*;
import com.pia.core.properties.primitiveObjectArrays.*;
import com.pia.core.properties.primitiveObjects.*;
import com.pia.core.properties.primitives.*;
import com.pia.testing.PrimitiveArrayTypeTestPlugin;
import com.pia.testing.PrimitiveObjectArrayTypeTestPlugin;
import com.pia.testing.PrimitiveObjectTypeTestPlugin;
import com.pia.testing.PrimitiveTypeTestPlugin;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

/**
 * These tests only test if a plugin's fields 
 * are classified correctly.
 * Meaning that all fields result in their 
 * correct corresponding DataType implementation.
 * For example a boolean variable should 
 * result in a BooleanDataType object.
 */
public class TypeClassificationTests {
    private PrimitiveTypeTestPlugin primitivePlugin;
    private PrimitiveArrayTypeTestPlugin primitiveArrayPlugin;
    private PrimitiveObjectTypeTestPlugin primitiveObjectPlugin;
    private PrimitiveObjectArrayTypeTestPlugin primitiveObjectArrayPlugin;

    @Before
    public void initPlugins() {
        primitivePlugin = new PrimitiveTypeTestPlugin();
        primitiveArrayPlugin = new PrimitiveArrayTypeTestPlugin();
        primitiveObjectPlugin = new PrimitiveObjectTypeTestPlugin();
        primitiveObjectArrayPlugin = new PrimitiveObjectArrayTypeTestPlugin();
    }
    
    @Test
    public void testPrimitiveTypes() throws InvalidArgumentException {
        List<Field> fields = primitivePlugin.getAnnotatedFields();
        assert fields.size() > 0;
        for (Field field : fields) {
            DataType fieldType = DataType.getDataType(field);
            switch (field.getType().getName()) {
                case "boolean" : assert fieldType instanceof BooleanDataType; break;
                case "byte" : assert fieldType instanceof ByteDataType; break;
                case "char" : assert fieldType instanceof CharDataType; break;
                case "double" : assert fieldType instanceof DoubleDataType; break;
                case "float" : assert fieldType instanceof FloatDataType; break;
                case "int" : assert fieldType instanceof IntDataType; break;
                case "long" : assert fieldType instanceof LongDataType; break;
                case "short" : assert fieldType instanceof ShortDataType; break;
                case "java.lang.String" : assert fieldType instanceof StringDataType; break;
                default: assert false;
            }
        }
    }

    @Test
    public void testPrimitiveArrayTypes() throws InvalidArgumentException {
        List<Field> fields = primitiveArrayPlugin.getAnnotatedFields();
        assert fields.size() > 0;
        for (Field field : fields) {
            DataType fieldType = DataType.getDataType(field);
            switch (field.getType().getComponentType().getName()) {
                case "boolean" : assert fieldType instanceof BooleanArrayDataType; break;
                case "byte" : assert fieldType instanceof ByteArrayDataType; break;
                case "char" : assert fieldType instanceof CharArrayDataType; break;
                case "double" : assert fieldType instanceof DoubleArrayDataType; break;
                case "float" : assert fieldType instanceof FloatArrayDataType; break;
                case "int" : assert fieldType instanceof IntArrayDataType; break;
                case "long" : assert fieldType instanceof LongArrayDataType; break;
                case "short" : assert fieldType instanceof ShortArrayDataType; break;
                case "java.lang.String" : assert fieldType instanceof StringArrayDataType; break;
                default: assert false;
            }
        }
    }

    @Test
    public void testPrimitiveObjectTypes() throws InvalidArgumentException {
        List<Field> fields = primitiveObjectPlugin.getAnnotatedFields();
        assert fields.size() > 0;
        for (Field field : fields) {
            DataType fieldType = DataType.getDataType(field);
            switch (field.getType().getName()) {
                case "java.lang.Boolean" : assert fieldType instanceof BooleanObjectDataType; break;
                case "java.lang.Byte" : assert fieldType instanceof ByteObjectDataType; break;
                case "java.lang.Character" : assert fieldType instanceof CharacterObjectDataType; break;
                case "java.lang.Double" : assert fieldType instanceof DoubleObjectDataType; break;
                case "java.lang.Float" : assert fieldType instanceof FloatObjectDataType; break;
                case "java.lang.Integer" : assert fieldType instanceof IntegerObjectDataType; break;
                case "java.lang.Long" : assert fieldType instanceof LongObjectDataType; break;
                case "java.lang.Short" : assert fieldType instanceof ShortObjectDataType; break;
                default: String[] arg = new String[1]; arg[0] = "Field is not primitive object"; throw new InvalidArgumentException(arg);
            }
        }
    }

    @Test
    public void testPrimitiveObjectArrayTypes() throws InvalidArgumentException {
        List<Field> fields = primitiveObjectArrayPlugin.getAnnotatedFields();
        assert fields.size() > 0;
        for (Field field : fields) {
            DataType fieldType = DataType.getDataType(field);
            switch (field.getType().getComponentType().getName()) {
                case "java.lang.Boolean" : assert fieldType instanceof BooleanObjectArrayDataType; break;
                case "java.lang.Byte" : assert fieldType instanceof ByteObjectArrayDataType; break;
                case "java.lang.Character" : assert fieldType instanceof CharacterObjectArrayDataType; break;
                case "java.lang.Double" : assert fieldType instanceof DoubleObjectArrayDataType; break;
                case "java.lang.Float" : assert fieldType instanceof FloatObjectArrayDataType; break;
                case "java.lang.Integer" : assert fieldType instanceof IntegerObjectArrayDataType; break;
                case "java.lang.Long" : assert fieldType instanceof LongObjectArrayDataType; break;
                case "java.lang.Short" : assert fieldType instanceof ShortObjectArrayDataType; break;
                default: String[] arg = new String[1]; arg[0] = "Field is not primitive object"; throw new InvalidArgumentException(arg);
            }
        }
    }
}
