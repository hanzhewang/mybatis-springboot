package com.has.mybatis.adapter;

import com.has.mybatis.annotation.PrimaryKey;
import com.has.mybatis.bo.PrimaryKeyBo;
import com.has.mybatis.enums.DbWriteTypeEnum;
import com.has.mybatis.enums.PrimaryKeyTypeEnum;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * <p>数据库主键注解</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/9/27
 */
public class PrimaryKeyAnnotationAdapter implements AnnotationAdapter<PrimaryKey, PrimaryKeyBo> {

    public static final AnnotationAdapterFactory FACTORY = new AnnotationAdapterFactory() {
        @Override
        public AnnotationAdapter<PrimaryKey, PrimaryKeyBo> create(Annotation annotation) {
            return annotation.annotationType() == PrimaryKey.class ? new PrimaryKeyAnnotationAdapter() : null;
        }
    };

    @Override
    public PrimaryKeyBo read(Field field, PrimaryKey primaryKey, final DbWriteTypeEnum writeType) {
        if (field == null || primaryKey == null) {
            return null;
        }
        PrimaryKeyBo bo = new PrimaryKeyBo();
        bo.setPrimaryKeyType(primaryKey.type());
        bo.setIndex(primaryKey.sort());
        Boolean required = false;
        if (writeType != null) {
            required = Arrays.stream(primaryKey.required()).anyMatch(type -> type.equals(writeType));
        }
        if (DbWriteTypeEnum.Insert.equals(writeType) && PrimaryKeyTypeEnum.AUTO.equals(writeType)) {
            required = Boolean.FALSE;
        }
        bo.setRequired(required);
        return bo;
    }
}
