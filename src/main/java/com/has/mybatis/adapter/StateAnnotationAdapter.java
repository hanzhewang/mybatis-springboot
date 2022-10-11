package com.has.mybatis.adapter;

import com.has.mybatis.annotation.State;
import com.has.mybatis.bo.BaseBo;
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
public class StateAnnotationAdapter implements AnnotationAdapter<State, BaseBo> {

    public static final AnnotationAdapterFactory FACTORY = new AnnotationAdapterFactory() {
        @Override
        public AnnotationAdapter<State, BaseBo> create(Annotation annotation) {
            return annotation.annotationType() == State.class ? new StateAnnotationAdapter() : null;
        }
    };

    @Override
    public BaseBo read(Field field, State state, final DbWriteTypeEnum writeType) {
        if (field == null || state == null) {
            return null;
        }
        BaseBo bo = new BaseBo();
        Boolean required = false;
        if (writeType != null) {
            required = Arrays.stream(state.required()).anyMatch(type -> type.equals(writeType));
        }
        bo.setRequired(required);
        return bo;
    }
}
