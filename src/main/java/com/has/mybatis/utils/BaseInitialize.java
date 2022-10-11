package com.has.mybatis.utils;

import com.has.mybatis.bo.BaseAnnotationBo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>初始数据</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/10/9
 */
public interface BaseInitialize {

    /**
     * 属性初始化
     *
     * @param bo
     * @param entity
     * @param <T>
     * @param <Bo>
     */
    <T extends Serializable, Bo extends BaseAnnotationBo> void initialize(List<Bo> bo, T entity);

}
