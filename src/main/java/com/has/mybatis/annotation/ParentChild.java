package com.has.mybatis.annotation;

import com.has.mybatis.enums.DbWriteTypeEnum;

import java.lang.annotation.*;

/**
 * <p>父子节点注解</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/9/27
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ParentChild {

    /**
     * 是否为子节点编号
     *
     * @return
     */
    boolean isChild() default false;

    /**
     * 必填场景
     *
     * @return
     */
    DbWriteTypeEnum[] required() default {DbWriteTypeEnum.Insert, DbWriteTypeEnum.Update};
}
