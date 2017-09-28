package com.pia.core.properties_old;

import com.pia.core.properties.CollectionType;
import com.pia.core.properties.ComplexType;
import com.pia.core.properties.DataType;
import com.pia.core.properties.basetypes.*;
import com.pia.testing.*;
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
    private PrimitiveObjectTypeTestPlugin primitiveObjectPlugin;
    private CollectionTypeTestPlugin collectionPlugin;
    private ComplexTypeTestPlugin complexPlugin;

    @Before
    public void initPlugins () {
        primitivePlugin = new PrimitiveTypeTestPlugin();
        primitiveObjectPlugin = new PrimitiveObjectTypeTestPlugin();
        collectionPlugin = new CollectionTypeTestPlugin();
        complexPlugin = new ComplexTypeTestPlugin();
    }

    @Test
    public void primitiveTypes () throws IllegalAccessException {
        List<Field> fields = primitivePlugin.getAnnotatedFields();
        assert fields.size() > 0;
        for (Field field : fields) {
            DataType fieldType = DataType.getDataType(field);
            switch (field.getType().getName()) {
                case "boolean":
                    assert fieldType instanceof BooleanType;
                    break;
                case "byte":
                    assert fieldType instanceof ByteType;
                    break;
                case "char":
                    assert fieldType instanceof CharacterType;
                    break;
                case "double":
                    assert fieldType instanceof DoubleType;
                    break;
                case "float":
                    assert fieldType instanceof FloatType;
                    break;
                case "int":
                    assert fieldType instanceof IntegerType;
                    break;
                case "long":
                    assert fieldType instanceof LongType;
                    break;
                case "short":
                    assert fieldType instanceof ShortType;
                    break;
                case "java.lang.String":
                    assert fieldType instanceof StringType;
                    break;
                default:
                    assert false;
            }
        }
    }

    @Test
    public void collectionTypes () throws IllegalAccessException {
        List<Field> fields = collectionPlugin.getAnnotatedFields();
        assert fields.size() > 0;
        for (Field field : fields) {
            DataType dataType = DataType.getDataType(field);
            assert dataType instanceof CollectionType;
        }
    }

    @Test
    public void complexTypes () throws IllegalAccessException {
        List<Field> fields = complexPlugin.getAnnotatedFields();
        assert fields.size() > 0;
        for (Field field : fields) {
            DataType dataType = DataType.getDataType(field);
            assert dataType instanceof ComplexType;
        }
    }
}
