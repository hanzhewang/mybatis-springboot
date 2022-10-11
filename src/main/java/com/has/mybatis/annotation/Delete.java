package com.has.mybatis.annotation;

import com.has.mybatis.enums.DbWriteTypeEnum;

/**
 * <p>删除标识</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/9/30
 */
public @interface Delete {

    /**
     * 初始化值
     *
     * @return
     */
    boolean initialize() default false;

    /**
     * 必填场景
     *
     * @return
     */
    DbWriteTypeEnum[] required() default {DbWriteTypeEnum.Insert, DbWriteTypeEnum.Update};

}
