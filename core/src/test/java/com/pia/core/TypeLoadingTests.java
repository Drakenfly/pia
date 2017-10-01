package com.pia.core;

import com.pia.core.property.CollectionType;
import com.pia.core.property.ComplexType;
import com.pia.core.property.DataType;
import com.pia.core.property.basetype.*;
import com.pia.core.property.basetype.primitive.*;
import com.pia.core.property.collectiontypes.ArrayType;
import com.pia.core.property.collectiontypes.GenericCollectionType;
import com.pia.testing.ArrayTypeTestPlugin;
import com.pia.testing.BaseTypeTestPlugin;
import com.pia.testing.ComplexTypeTestPlugin;
import com.pia.testing.ListTypeTestPlugin;
import org.junit.Before;
import org.junit.Test;

public class TypeLoadingTests {
    BaseTypeTestPlugin baseTypePlugin;
    ArrayTypeTestPlugin arrayTypePlugin;
    ListTypeTestPlugin listTypePlugin;
    ComplexTypeTestPlugin complexTypePlugin;
    
    @Before
    public void setUp() {
        baseTypePlugin = new BaseTypeTestPlugin();
        arrayTypePlugin = new ArrayTypeTestPlugin();
        listTypePlugin = new ListTypeTestPlugin();
        complexTypePlugin = new ComplexTypeTestPlugin();
    }

    @Test
    public void baseTypes() throws NoSuchFieldException, IllegalAccessException {
        //objects
        assert DataType.getDataType(baseTypePlugin.getClass().getField("aBooleanObject")) instanceof BooleanType;
        assert DataType.getDataType(baseTypePlugin.getClass().getField("aByteObject")) instanceof ByteType;
        assert DataType.getDataType(baseTypePlugin.getClass().getField("aCharObject")) instanceof CharacterType;
        assert DataType.getDataType(baseTypePlugin.getClass().getField("aDoubleObject")) instanceof DoubleType;
        assert DataType.getDataType(baseTypePlugin.getClass().getField("aFloatObject")) instanceof FloatType;
        assert DataType.getDataType(baseTypePlugin.getClass().getField("aIntObject")) instanceof IntegerType;
        assert DataType.getDataType(baseTypePlugin.getClass().getField("aLongObject")) instanceof LongType;
        assert DataType.getDataType(baseTypePlugin.getClass().getField("aShortObject")) instanceof ShortType;
        assert DataType.getDataType(baseTypePlugin.getClass().getField("aString")) instanceof StringType;

        //primitives
        assert DataType.getDataType(baseTypePlugin.getClass().getField("aBoolean")) instanceof PrimitiveBooleanType;
        assert DataType.getDataType(baseTypePlugin.getClass().getField("aByte")) instanceof PrimitiveByteType;
        assert DataType.getDataType(baseTypePlugin.getClass().getField("aChar")) instanceof PrimitiveCharacterType;
        assert DataType.getDataType(baseTypePlugin.getClass().getField("aDouble")) instanceof PrimitiveDoubleType;
        assert DataType.getDataType(baseTypePlugin.getClass().getField("aFloat")) instanceof PrimitiveFloatType;
        assert DataType.getDataType(baseTypePlugin.getClass().getField("aInt")) instanceof PrimitiveIntegerType;
        assert DataType.getDataType(baseTypePlugin.getClass().getField("aLong")) instanceof PrimitiveLongType;
        assert DataType.getDataType(baseTypePlugin.getClass().getField("aShort")) instanceof PrimitiveShortType;

    }

    @Test
    public void complexTypes() throws NoSuchFieldException, IllegalAccessException {

    }

    @Test
    public void arrayTypes() throws NoSuchFieldException, IllegalAccessException {
        DataType current;

        //int[]
        current = DataType.getDataType(arrayTypePlugin.getClass().getField("intArray"));
        assert current instanceof ArrayType;
        assert ((ArrayType) current).getChildDataType() instanceof PrimitiveIntegerType;

        //Integer[]
        current = DataType.getDataType(arrayTypePlugin.getClass().getField("intObjectArray"));
        assert current instanceof ArrayType;
        assert ((ArrayType) current).getChildDataType() instanceof IntegerType;

        //User[]
        current = DataType.getDataType(arrayTypePlugin.getClass().getField("userArray"));
        assert current instanceof ArrayType;
        assert ((ArrayType) current).getChildDataType() instanceof ComplexType;

        //Integer[][]
        current = DataType.getDataType(arrayTypePlugin.getClass().getField("intObjectArrayArray"));
        assert current instanceof ArrayType;
        current = ((ArrayType) current).getChildDataType();
        assert current instanceof ArrayType;
        assert ((ArrayType) current).getChildDataType() instanceof IntegerType;

        //User[][]
        current = DataType.getDataType(arrayTypePlugin.getClass().getField("userArrayArray"));
        assert current instanceof ArrayType;
        current = ((ArrayType) current).getChildDataType();
        assert current instanceof ArrayType;
        assert ((ArrayType) current).getChildDataType() instanceof ComplexType;

        //Integer[][][][]
        current = DataType.getDataType(arrayTypePlugin.getClass().getField("intObjectArrayArrayArrayArray"));
        assert current instanceof ArrayType;
        current = ((ArrayType) current).getChildDataType();
        assert current instanceof ArrayType;
        current = ((ArrayType) current).getChildDataType();
        assert current instanceof ArrayType;
        current = ((ArrayType) current).getChildDataType();
        assert current instanceof ArrayType;
        assert ((ArrayType) current).getChildDataType() instanceof IntegerType;
    }

    @Test
    public void listTypes() throws NoSuchFieldException, IllegalAccessException {
        DataType current;

        //LinkedList<Integer>
        current = DataType.getDataType(listTypePlugin.getClass().getField("intObjectList"));
        assert current instanceof GenericCollectionType;
        assert ((GenericCollectionType) current).getChildDataType() instanceof IntegerType;

        //ArrayList<User>
        current = DataType.getDataType(listTypePlugin.getClass().getField("userList"));
        assert current instanceof GenericCollectionType;
        assert ((GenericCollectionType) current).getChildDataType() instanceof ComplexType;

        //HashSet<LinkedList<User>>
        current = DataType.getDataType(listTypePlugin.getClass().getField("userListList"));
        assert current instanceof GenericCollectionType;
        current = ((GenericCollectionType) current).getChildDataType();
        assert current instanceof GenericCollectionType;
        assert ((GenericCollectionType) current).getChildDataType() instanceof ComplexType;

        //LinkedHashSet<ArrayList<LinkedList<String>>>
        current = DataType.getDataType(listTypePlugin.getClass().getField("stringObjectListListList"));
        assert current instanceof GenericCollectionType;
        current = ((GenericCollectionType) current).getChildDataType();
        assert current instanceof GenericCollectionType;
        current = ((GenericCollectionType) current).getChildDataType();
        assert current instanceof GenericCollectionType;
        assert ((GenericCollectionType) current).getChildDataType() instanceof StringType;

        //Li<Li<Li<Li<Li<Li<Li<Li<Li<Li<Li<Li<String>>>>>>>>>>>> stringMayham
        current = DataType.getDataType(listTypePlugin.getClass().getField("stringMayham"));

        assert current instanceof GenericCollectionType; current = ((GenericCollectionType) current).getChildDataType();
        assert current instanceof GenericCollectionType; current = ((GenericCollectionType) current).getChildDataType();
        assert current instanceof GenericCollectionType; current = ((GenericCollectionType) current).getChildDataType();
        assert current instanceof GenericCollectionType; current = ((GenericCollectionType) current).getChildDataType();
        assert current instanceof GenericCollectionType; current = ((GenericCollectionType) current).getChildDataType();
        assert current instanceof GenericCollectionType; current = ((GenericCollectionType) current).getChildDataType();
        assert current instanceof GenericCollectionType; current = ((GenericCollectionType) current).getChildDataType();
        assert current instanceof GenericCollectionType; current = ((GenericCollectionType) current).getChildDataType();
        assert current instanceof GenericCollectionType; current = ((GenericCollectionType) current).getChildDataType();
        assert current instanceof GenericCollectionType; current = ((GenericCollectionType) current).getChildDataType();
        assert current instanceof GenericCollectionType; current = ((GenericCollectionType) current).getChildDataType();
        assert current instanceof GenericCollectionType; current = ((GenericCollectionType) current).getChildDataType();

        assert current instanceof StringType;

        //LinkedList<int[]>
        current = DataType.getDataType(listTypePlugin.getClass().getField("intArrayList"));
        assert current instanceof GenericCollectionType; current = ((GenericCollectionType) current).getChildDataType();
        assert current instanceof ArrayType; current = ((ArrayType) current).getChildDataType();
        assert current instanceof PrimitiveIntegerType;

        //Li<LinkedList<int[]>>
        current = DataType.getDataType(listTypePlugin.getClass().getField("intArrayListList"));
        assert current instanceof GenericCollectionType; current = ((GenericCollectionType) current).getChildDataType();
        assert current instanceof GenericCollectionType; current = ((GenericCollectionType) current).getChildDataType();
        assert current instanceof ArrayType; current = ((ArrayType) current).getChildDataType();
        assert current instanceof PrimitiveIntegerType;

        //LinkedList<User[]>
        current = DataType.getDataType(listTypePlugin.getClass().getField("userArrayList"));
        assert current instanceof GenericCollectionType; current = ((GenericCollectionType) current).getChildDataType();
        assert current instanceof ArrayType; current = ((ArrayType) current).getChildDataType();
        assert current instanceof ComplexType;
    }
}
