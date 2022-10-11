package com.has.mybatis.bo;

import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * <p>com.has.mybatis.dto</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/9/30
 */
@Data
public class RouterBo implements BaseAnnotationBo, Serializable {

    /**
     * 反射属性
     */
    private Field field;

    /**
     * 字段名
     */
    private String name;

    /**
     * 字段值
     */
    private Object value;

    /**
     * 字段类型
     */
    private Class<?> type;

    /**
     * 必填项
     */
    private Boolean required;

    /**
     * 默认值
     */
    private Object defaultValue;

    private Integer index;

}
