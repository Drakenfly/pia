package com.pia.core;

import com.pia.core.properties.DataType;
import com.pia.core.properties.basetypes.BooleanType;
import com.pia.core.properties.basetypes.CharacterType;
import com.pia.core.properties.basetypes.IntegerType;
import com.pia.core.properties.basetypes.StringType;
import com.pia.core.properties.basetypes.primitives.PrimitiveBooleanType;
import com.pia.core.properties.basetypes.primitives.PrimitiveCharacterType;
import com.pia.core.properties.basetypes.primitives.PrimitiveDoubleType;
import com.pia.core.properties.basetypes.primitives.PrimitiveIntegerType;
import com.pia.core.properties.collectiontypes.ArrayType;
import com.pia.testing.ArrayDataStorageTestPlugin;
import com.pia.testing.SimpleDataStorageTestPlugin;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

public class DataWriteThroughTest {
    private SimpleDataStorageTestPlugin simplePlugin;
    private ArrayDataStorageTestPlugin arrayPlugin;

    @Before
    public void setup() {
        simplePlugin = new SimpleDataStorageTestPlugin();
        arrayPlugin = new ArrayDataStorageTestPlugin();
    }

    @Test
    public void integerStorageTest() throws NoSuchFieldException, IllegalAccessException {
        Integer result;

        // Object
        Field integerField = simplePlugin.getClass().getField("aIntObject");
        IntegerType integerType = (IntegerType) DataType.getDataType(integerField);
        integerType.setValue(1);
        integerType.writeValueBackToObject(simplePlugin);
        result = (Integer) integerField.get(simplePlugin);
        assert result == 1;

        integerType.parseValue("2");
        integerType.writeValueBackToObject(simplePlugin);
        result = (Integer) integerField.get(simplePlugin);
        assert result == 2;

        // Primitive
        Field intField = simplePlugin.getClass().getField("aInt");
        PrimitiveIntegerType primitiveIntegerType = (PrimitiveIntegerType) DataType.getDataType(intField);
        primitiveIntegerType.setValue(3);
        primitiveIntegerType.writeValueBackToObject(simplePlugin);
        result = (Integer) intField.get(simplePlugin);
        assert result == 3;

        primitiveIntegerType.parseValue("4");
        primitiveIntegerType.writeValueBackToObject(simplePlugin);
        result = (Integer) intField.get(simplePlugin);
        assert result == 4;
    }

    @Test
    public void booleanStorageTest() throws NoSuchFieldException, IllegalAccessException {
        Boolean result;

        // Object
        Field booleanObjectField = simplePlugin.getClass().getField("aBooleanObject");
        BooleanType booleanType = (BooleanType) DataType.getDataType(booleanObjectField);
        booleanType.setValue(true);
        booleanType.writeValueBackToObject(simplePlugin);
        result = (Boolean) booleanObjectField.get(simplePlugin);
        assert result == true;

        booleanType.parseValue("false");
        booleanType.writeValueBackToObject(simplePlugin);
        result = (Boolean) booleanObjectField.get(simplePlugin);
        assert result == false;

        // Primitive
        Field booleanField = simplePlugin.getClass().getField("aBoolean");
        PrimitiveBooleanType primitiveBooleanType = (PrimitiveBooleanType) DataType.getDataType(booleanField);
        primitiveBooleanType.setValue(false);
        primitiveBooleanType.writeValueBackToObject(simplePlugin);
        result = (Boolean) booleanField.get(simplePlugin);
        assert result == false;

        primitiveBooleanType.parseValue("true");
        primitiveBooleanType.writeValueBackToObject(simplePlugin);
        result = (Boolean) booleanField.get(simplePlugin);
        assert result == true;
    }
    
    @Test
    public void stringStorageTest() throws NoSuchFieldException, IllegalAccessException {
        String result;

        String[] tests = {"A simple, yet effective test string", null, "", "a long string \n with line breaks\n"};

        // Object
        Field stringObjectField = simplePlugin.getClass().getField("aString");
        StringType stringType = (StringType) DataType.getDataType(stringObjectField);

        for (String test : tests) {
            stringType.setValue(test);
            stringType.writeValueBackToObject(simplePlugin);
            result = (String) stringObjectField.get(simplePlugin);
            assert result == null ? test == null : result.equals(test);
        }

    }

    @Test
    public void characterStorageTest() throws NoSuchFieldException, IllegalAccessException {
        Character result;

        // Object
        Field characterField = simplePlugin.getClass().getField("aCharObject");
        CharacterType characterType = (CharacterType) DataType.getDataType(characterField);
        characterType.setValue('A');
        characterType.writeValueBackToObject(simplePlugin);
        result = (Character) characterField.get(simplePlugin);
        assert result == 'A';

        characterType.parseValue("2");
        characterType.writeValueBackToObject(simplePlugin);
        result = (Character) characterField.get(simplePlugin);
        assert result == '2';

        // Primitive
        Field charField = simplePlugin.getClass().getField("aChar");
        PrimitiveCharacterType primitiveCharacterType = (PrimitiveCharacterType) DataType.getDataType(charField);
        primitiveCharacterType.setValue('\t');
        primitiveCharacterType.writeValueBackToObject(simplePlugin);
        result = (Character) charField.get(simplePlugin);
        assert result == '\t';

        primitiveCharacterType.parseValue("D");
        primitiveCharacterType.writeValueBackToObject(simplePlugin);
        result = (Character) charField.get(simplePlugin);
        assert result == 'D';
    }

    @Test
    public void doubleStorageTest() throws NoSuchFieldException, IllegalAccessException {
        double result;

        // Primitive
        Field doubleField = simplePlugin.getClass().getField("aDouble");
        PrimitiveDoubleType primitiveDoubleType = (PrimitiveDoubleType) DataType.getDataType(doubleField);
        primitiveDoubleType.setValue(3.);
        primitiveDoubleType.writeValueBackToObject(simplePlugin);
        result = (Double) doubleField.get(simplePlugin);
        assert result == 3;

        primitiveDoubleType.parseValue("4");
        primitiveDoubleType.writeValueBackToObject(simplePlugin);
        result = (Double) doubleField.get(simplePlugin);
        assert result == 4;


        primitiveDoubleType.parseValue("12.5");
        primitiveDoubleType.writeValueBackToObject(simplePlugin);
        result = (Double) doubleField.get(simplePlugin);
        assert result == 12.5;


        primitiveDoubleType.parseValue("-4");
        primitiveDoubleType.writeValueBackToObject(simplePlugin);
        result = (Double) doubleField.get(simplePlugin);
        assert result == -4;

        /* this decimal notation was added by me */
        primitiveDoubleType.parseValue("5,5");
        primitiveDoubleType.writeValueBackToObject(simplePlugin);
        result = (Double) doubleField.get(simplePlugin);
        assert result == 5.5;
    }

    @Test
    public void booleanArrayStorageTest () throws NoSuchFieldException, IllegalAccessException {
        BooleanType booleanTypeComp1 = (BooleanType) DataType.getDataType(Boolean.class, Boolean.class);
        booleanTypeComp1.setValue(true);

        BooleanType booleanTypeComp2 = (BooleanType) DataType.getDataType(Boolean.class, Boolean.class);
        booleanTypeComp2.setValue(false);

        Boolean[] boolArrComp = {true, false};

        Field booleanObjectField = arrayPlugin.getClass().getField("aBooleanObjectArray");
        ArrayType booleanType = (ArrayType) DataType.getDataType(booleanObjectField);
        booleanType.add(booleanTypeComp1);
        booleanType.add(booleanTypeComp2);
        booleanType.writeValueBackToObject(arrayPlugin);
        assert Arrays.equals((Object[]) booleanObjectField.get(arrayPlugin), boolArrComp);
    }

    @Test
    public void stringArrayStorageTest () throws NoSuchFieldException, IllegalAccessException {
        String[] stringArrComp = {"test \nor not to test", null, ""};

        StringType stringTypeComp1 = (StringType) DataType.getDataType(String.class, String.class);
        stringTypeComp1.setValue(stringArrComp[0]);

        StringType stringTypeComp2 = (StringType) DataType.getDataType(String.class, String.class);
        stringTypeComp2.setValue(stringArrComp[1]);

        StringType stringTypeComp3 = (StringType) DataType.getDataType(String.class, String.class);
        stringTypeComp3.setValue(stringArrComp[2]);

        Field stringObjectField = arrayPlugin.getClass().getField("aStringArray");
        ArrayType stringType = (ArrayType) DataType.getDataType(stringObjectField);
        stringType.add(stringTypeComp1);
        stringType.add(stringTypeComp2);
        stringType.add(stringTypeComp3);
        stringType.writeValueBackToObject(arrayPlugin);
        assert Arrays.equals((Object[]) stringObjectField.get(arrayPlugin), stringArrComp);
    }

    @Test
    public void intArrayArrayStorageTest () throws NoSuchFieldException, IllegalAccessException {
        int[][] integerArrComp = {{1,2,3}, {4}};

        // set up first array
        PrimitiveIntegerType integerTypeComp01 = (PrimitiveIntegerType) DataType.getDataType(Integer.TYPE, Integer.TYPE);
        integerTypeComp01.setValue(integerArrComp[0][0]);

        PrimitiveIntegerType integerTypeComp02 = (PrimitiveIntegerType) DataType.getDataType(Integer.TYPE, Integer.TYPE);
        integerTypeComp02.setValue(integerArrComp[0][1]);

        PrimitiveIntegerType integerTypeComp03 = (PrimitiveIntegerType) DataType.getDataType(Integer.TYPE, Integer.TYPE);
        integerTypeComp03.setValue(integerArrComp[0][2]);
        
        ArrayType<PrimitiveIntegerType> integerArray1 = new ArrayType<>(int[].class, int[].class);
        integerArray1.add(integerTypeComp01);
        integerArray1.add(integerTypeComp02);
        integerArray1.add(integerTypeComp03);

        // set up second array
        PrimitiveIntegerType integerTypeComp11 = (PrimitiveIntegerType) DataType.getDataType(Integer.TYPE, Integer.TYPE);
        integerTypeComp11.setValue(integerArrComp[1][0]);
        ArrayType<PrimitiveIntegerType> integerArray2 = new ArrayType<>(int[].class, int[].class);
        integerArray2.add(integerTypeComp11);


        Field intArrayArrayField = arrayPlugin.getClass().getField("aIntArrayArray");
        ArrayType<ArrayType> integerArrayArray = (ArrayType<ArrayType>) DataType.getDataType(intArrayArrayField);
        integerArrayArray.add(integerArray1);
        integerArrayArray.add(integerArray2);
        integerArrayArray.writeValueBackToObject(arrayPlugin);

        int[][] result = (int[][]) intArrayArrayField.get(arrayPlugin);
        System.out.println("From field: " + Arrays.deepToString(result) + " vs. test array: " + Arrays.deepToString(integerArrComp));
        for (int i = 0; i < result.length; i++) {
            assert Arrays.equals(result[i], integerArrComp[i]);
        }
    }
}
