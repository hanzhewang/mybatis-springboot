package com.has.mybatis.adapter;

import com.has.mybatis.annotation.Router;
import com.has.mybatis.bo.RouterBo;
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
public class RouterAnnotationAdapter implements AnnotationAdapter<Router, RouterBo> {

    public static final AnnotationAdapterFactory FACTORY = new AnnotationAdapterFactory() {
        @Override
        public AnnotationAdapter<Router, RouterBo> create(Annotation annotation) {
            return annotation.annotationType() == Router.class ? new RouterAnnotationAdapter() : null;
        }
    };

    @Override
    public RouterBo read(Field field, Router router, final DbWriteTypeEnum writeType) {
        if (field == null || router == null) {
            return null;
        }
        RouterBo bo = new RouterBo();
        bo.setIndex(router.sort());
        Boolean required = false;
        if (writeType != null) {
            required = Arrays.stream(router.required()).anyMatch(type -> type.equals(writeType));
        }
        bo.setRequired(required);
        return bo;
    }
}
