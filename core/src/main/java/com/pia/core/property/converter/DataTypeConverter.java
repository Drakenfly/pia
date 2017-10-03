package com.pia.core.property.converter;

import com.pia.core.PluginWrapper;
import com.pia.core.plugin.Plugin;
import com.pia.core.property.*;
import com.pia.core.property.basetype.*;
import com.pia.core.property.dto.ConstructorDTO;
import com.pia.core.property.dto.DataTypeDTO;
import com.pia.core.property.dto.DataTypeType;
import com.pia.core.property.dto.PluginWrapperDTO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class DataTypeConverter {
    public static DataTypeDTO dataTypeToDTO(DataType dataType) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        DataTypeDTO dto = new DataTypeDTO();
        dto.setType(getDTOType(dataType));

        if (dataType.getField() != null) {
            dto.setFieldName(dataType.getField().getName());

        }

        if (dataType instanceof BaseType) {
            dto.setValue((BaseType) dataType.getValue());
        }
        else if (dataType instanceof CollectionType) {
            dto.setChildren(getDTOChildren((CollectionType<?>) dataType));
        }

        if (dataType instanceof ConstructableType) {
            setDTOConstructors(dto, (ConstructableType) dataType);
        }

        if (dataType instanceof NullableType) {
            NullableType nullable = (NullableType) dataType;
            dto.setNull(nullable.getValueIsNull());
            dto.setRequired(nullable.isRequired());
        }

        return dto;
    }

    public static PluginWrapper dtoToDataType(PluginWrapperDTO wrapperDto) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        PluginWrapper wrapper = new PluginWrapper();
        wrapper.setPluginClass((Class<? extends Plugin>) Class.forName(wrapperDto.getCanonicalClassName()));

        List<DataType> dataTypeList = new LinkedList<>();
        for (DataTypeDTO dto : wrapperDto.getFieldValues()) {
            Field field = wrapper.getPluginClass().getField(dto.getFieldName());
            DataType dataType = DataType.getDataType(field);
            switch (dto.getType()) {
                case COLLECTION: {
                    for (DataTypeDTO dtoChild : dto.getChildren()) {
                        DataType child = ((CollectionType) dataType).getChildDataType();
                        dtoToDataType(child, dtoChild);
                        ((CollectionType) dataType).add(child);
                    }
                    if (dto.getConstructors().size() == 0) {
                        break;
                    }
                }
                case COMPLEX: {
                    if (dto.getChosenConstructor() != null) {
                        //TODO
                    }
                    break;
                }
                default:
                    ((BaseType) dataType).setValue(dto.getValue());
            }
            if (dataType instanceof NullableType) {
                ((NullableType) dataType).setValueIsNull(dto.isNull());
            }
            dataTypeList.add(dataType);
        }

        wrapper.setFieldValues(dataTypeList);
        return wrapper;
    }

    private static void dtoToDataType(DataType dataType, DataTypeDTO dto) {

    }

    /* This will cause infinite recursion - TODO TBD how to handle this */
    private static void setDTOConstructors (DataTypeDTO dto, ConstructableType dataType) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        List<ConstructorDTO> constructorDTOS = new LinkedList<>();
        List<PiaConstructor> constructors = dataType.getConstructors();
        for (PiaConstructor constructor : constructors) {
            ConstructorDTO constructorDTO = new ConstructorDTO();
            List<DataTypeDTO> args = new LinkedList<>();
            for (DataType arg : constructor.getArgumentTypes()) {
                args.add(dataTypeToDTO(arg));
            }
            constructorDTO.setArguments(args);
            constructorDTOS.add(constructorDTO);
        }
        dto.setConstructors(constructorDTOS);
        if (dataType.getChosenConstructor() != null) {
            ConstructorDTO constructorDTO = new ConstructorDTO();
            List<DataTypeDTO> args = new LinkedList<>();
            for (DataType arg : dataType.getChosenConstructor().getArgumentTypes()) {
                args.add(dataTypeToDTO(arg));
            }
            constructorDTO.setArguments(args);
            dto.setChosenConstructor(constructorDTO);
        }
    }

    private static List<DataTypeDTO> getDTOChildren (CollectionType<?> dataType) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        List<DataTypeDTO> children = new LinkedList<>();
        for (DataType child : dataType.getChildren()) {
            children.add(dataTypeToDTO(child));
        }
        return children;
    }

    private static DataTypeType getDTOType (DataType dataType) {
        if (dataType instanceof CollectionType) {
            return DataTypeType.COLLECTION;
        }
        else if (dataType instanceof ComplexType) {
            return DataTypeType.COMPLEX;
        }
        else if (dataType instanceof BaseType) {
            if (dataType instanceof BooleanType) {
                return DataTypeType.BOOLEAN;
            }
            else if (dataType instanceof ByteType) {
                return DataTypeType.BYTE;
            }
            else if (dataType instanceof CharacterType) {
                return DataTypeType.CHARACTER;
            }
            else if (dataType instanceof DoubleType) {
                return DataTypeType.DOUBLE;
            }
            else if (dataType instanceof FloatType) {
                return DataTypeType.FLOAT;
            }
            else if (dataType instanceof IntegerType) {
                return DataTypeType.INTEGER;
            }
            else if (dataType instanceof LongType) {
                return DataTypeType.LONG;
            }
            else if (dataType instanceof ShortType) {
                return DataTypeType.SHORT;
            }
            else if (dataType instanceof StringType) {
                return DataTypeType.STRING;
            }
        }
        return null;
    }
}
