package com.has.mybatis.annotation;

import com.has.mybatis.enums.DbWriteTypeEnum;

import java.lang.annotation.*;

/**
 * <p>版本号</p>
 * 乐观锁
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/9/27
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Version {

    /**
     * 必填场景
     *
     * @return
     */
    DbWriteTypeEnum[] required() default {DbWriteTypeEnum.Insert, DbWriteTypeEnum.Update, DbWriteTypeEnum.UpdateSelective, DbWriteTypeEnum.Delete};

    /**
     * 初始化值
     *
     * @return
     */
    int initialize() default 1;

}
