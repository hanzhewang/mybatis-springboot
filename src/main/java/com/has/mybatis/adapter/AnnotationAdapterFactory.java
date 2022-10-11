package com.has.mybatis.adapter;

import com.has.mybatis.bo.BaseAnnotationBo;

import java.lang.annotation.Annotation;

/**
 * <p>注解适配工厂</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/9/29
 */
public interface AnnotationAdapterFactory {

    /**
     * 创建对象
     *
     * @param annotation
     * @param <A>
     * @param <B>
     * @return
     */
    <A extends Annotation, B extends BaseAnnotationBo> AnnotationAdapter<A, B> create(Annotation annotation);

}
