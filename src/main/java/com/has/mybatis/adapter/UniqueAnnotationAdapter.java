package com.has.mybatis.adapter;

import com.has.mybatis.annotation.Unique;
import com.has.mybatis.bo.UniqueBo;
import com.has.mybatis.enums.DbWriteTypeEnum;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * <p>唯一键注解</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/9/27
 */
public class UniqueAnnotationAdapter implements AnnotationAdapter<Unique, UniqueBo> {

    public static final AnnotationAdapterFactory FACTORY = new AnnotationAdapterFactory() {
        @Override
        public AnnotationAdapter<Unique, UniqueBo> create(Annotation annotation) {
            return annotation.annotationType() == Unique.class ? new UniqueAnnotationAdapter() : null;
        }
    };

    @Override
    public UniqueBo read(Field field, Unique unique, final DbWriteTypeEnum writeType) {
        if (field == null || unique == null) {
            return null;
        }
        UniqueBo bo = new UniqueBo();
        bo.setIndex(unique.sort());
        Boolean required = false;
        if (writeType != null) {
            required = Arrays.stream(unique.required()).anyMatch(type -> type.equals(writeType));
        }
        bo.setRequired(required);
        return bo;
    }
}
