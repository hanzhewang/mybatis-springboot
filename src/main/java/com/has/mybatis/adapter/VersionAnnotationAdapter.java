package com.has.mybatis.adapter;

import com.has.mybatis.annotation.Version;
import com.has.mybatis.bo.BaseBo;
import com.has.mybatis.enums.DbWriteTypeEnum;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * <p>com.has.common.persistence.anno</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/9/27
 */
public class VersionAnnotationAdapter implements AnnotationAdapter<Version, BaseBo> {

    public static final AnnotationAdapterFactory FACTORY = new AnnotationAdapterFactory() {
        @Override
        public AnnotationAdapter<Version, BaseBo> create(Annotation annotation) {
            return annotation.annotationType() == Version.class ? new VersionAnnotationAdapter() : null;
        }
    };

    @Override
    public BaseBo read(Field field, Version version, final DbWriteTypeEnum writeType) {
        if (field == null || version == null) {
            return null;
        }
        BaseBo bo = new BaseBo();
        bo.setDefaultValue(version.initialize());
        Boolean required = false;
        if (writeType != null) {
            required = Arrays.stream(version.required()).anyMatch(type -> type.equals(writeType));
        }
        bo.setRequired(required);
        return bo;
    }

}
