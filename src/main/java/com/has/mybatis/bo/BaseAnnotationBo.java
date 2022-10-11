package com.has.mybatis.bo;

import java.lang.reflect.Field;

/**
 * <p>属性注解基础信息</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/9/30
 */
public interface BaseAnnotationBo {

    /**
     * 获取反射属性
     *
     * @return
     */
    Field getField();

    /**
     * 设置反射属性
     *
     * @param field
     */
    void setField(Field field);

    /**
     * 获取属性名
     *
     * @return
     */
    String getName();

    /**
     * 设置属性值
     *
     * @return
     */
    void setName(String name);

    /**
     * 获取属性值
     *
     * @return
     */
    Object getValue();

    /**
     * 设置属性值
     *
     * @return
     */
    void setValue(Object value);

    /**
     * 获取属性类型
     *
     * @return
     */
    Class<?> getType();

    /**
     * 设置属性值
     *
     * @return
     */
    void setType(Class<?> type);

    /**
     * 设置必填项
     *
     * @return
     */
    Boolean getRequired();

    /**
     * 设置必填项
     *
     * @return
     */
    void setRequired(Boolean required);


    /**
     * 获取属性默认值
     *
     * @return
     */
    Object getDefaultValue();

    /**
     * 设置属性默认值
     *
     * @return
     */
    void setDefaultValue(Object defaultValue);
}
