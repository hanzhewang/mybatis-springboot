package com.has.mybatis.annotation;

import com.has.mybatis.enums.DbWriteTypeEnum;
import com.has.mybatis.enums.PrimaryKeyTypeEnum;

import java.lang.annotation.*;

/**
 * <p>主键注解</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/9/27
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface PrimaryKey {

    /**
     * 数据库主键类型
     *
     * @return
     */
    PrimaryKeyTypeEnum type() default PrimaryKeyTypeEnum.AUTO;

    /**
     * 必填场景
     *
     * @return
     */
    DbWriteTypeEnum[] required() default {DbWriteTypeEnum.Insert, DbWriteTypeEnum.Update, DbWriteTypeEnum.UpdateSelective, DbWriteTypeEnum.Delete};

    /**
     * 联合主键-排序
     *
     * @return
     */
    int sort() default 1;

}
