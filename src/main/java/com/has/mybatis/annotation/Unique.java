package com.has.mybatis.annotation;

import com.has.mybatis.enums.DbWriteTypeEnum;

import java.lang.annotation.*;

/**
 * <p>唯一键注解</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/9/27
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Unique {

    /**
     * 必填场景
     *
     * @return
     */
    DbWriteTypeEnum[] required() default {DbWriteTypeEnum.Insert, DbWriteTypeEnum.Update};

    /**
     * 排序
     * 联合唯一
     *
     * @return
     */
    int sort() default 1;

}
