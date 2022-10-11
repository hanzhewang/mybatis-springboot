package com.has.mybatis.adapter;

import com.has.mybatis.annotation.Delete;
import com.has.mybatis.bo.BaseBo;
import com.has.mybatis.bo.DeleteBo;
import com.has.mybatis.enums.DbWriteTypeEnum;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * <p>com.has.mybatis.parser.adapter</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/9/30
 */
public class DeleteAnnotationAdapter implements AnnotationAdapter<Delete, BaseBo> {

    public static final AnnotationAdapterFactory FACTORY = new AnnotationAdapterFactory() {
        @Override
        public AnnotationAdapter<Delete, BaseBo> create(Annotation annotation) {
            return annotation.annotationType() == Delete.class ? new DeleteAnnotationAdapter() : null;
        }
    };

    @Override
    public BaseBo read(Field field, Delete delete, final DbWriteTypeEnum writeType) {
        if (field == null || delete == null) {
            return null;
        }
        BaseBo bo = new BaseBo();
        bo.setDefaultValue(delete.initialize());
        Boolean required = false;
        if (writeType != null) {
            required = Arrays.stream(delete.required()).anyMatch(type -> type.equals(writeType));
        }
        bo.setRequired(required);
        return bo;
    }

}
