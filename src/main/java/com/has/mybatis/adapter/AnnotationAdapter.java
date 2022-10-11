package com.has.mybatis.adapter;

import com.has.mybatis.bo.BaseAnnotationBo;
import com.has.mybatis.enums.DbWriteTypeEnum;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * <p>适配器</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/9/29
 */
public interface AnnotationAdapter<A extends Annotation, B extends BaseAnnotationBo> {

    /**
     * 读取注解的属性
     *
     * @param field
     * @param writeType
     * @return
     */
    B read(Field field, A annotation, final DbWriteTypeEnum writeType);

}
