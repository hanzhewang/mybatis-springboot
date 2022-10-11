package com.has.mybatis.annotation;

import com.has.mybatis.enums.DbWriteTypeEnum;

import java.lang.annotation.*;

/**
 * <p>路由键</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/9/30
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Router {

    /**
     * 必填场景
     *
     * @return
     */
    DbWriteTypeEnum[] required() default {DbWriteTypeEnum.Insert, DbWriteTypeEnum.Update, DbWriteTypeEnum.UpdateSelective, DbWriteTypeEnum.Delete};

    int sort() default 1;
}
