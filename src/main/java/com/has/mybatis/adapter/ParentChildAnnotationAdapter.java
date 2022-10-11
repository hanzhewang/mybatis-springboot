package com.has.mybatis.adapter;

import com.has.mybatis.annotation.ParentChild;
import com.has.mybatis.bo.ParentChildBo;
import com.has.mybatis.enums.DbWriteTypeEnum;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * <p>父节点</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/9/27
 */
public class ParentChildAnnotationAdapter implements AnnotationAdapter<ParentChild, ParentChildBo> {

    public static final AnnotationAdapterFactory FACTORY = new AnnotationAdapterFactory() {
        @Override
        public AnnotationAdapter<ParentChild, ParentChildBo> create(Annotation annotation) {
            return annotation.annotationType() == ParentChild.class ? new ParentChildAnnotationAdapter() : null;
        }
    };

    @Override
    public ParentChildBo read(Field field, ParentChild parentChild, final DbWriteTypeEnum writeType) {
        if (field == null || parentChild == null) {
            return null;
        }
        ParentChildBo bo = new ParentChildBo();
        bo.setIsChild(parentChild.isChild());
        Boolean required = false;
        if (writeType != null) {
            required = Arrays.stream(parentChild.required()).anyMatch(type -> type.equals(writeType));
        }
        bo.setRequired(required);
        return bo;
    }
}
