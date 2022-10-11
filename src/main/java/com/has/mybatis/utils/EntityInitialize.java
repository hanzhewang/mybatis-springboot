package com.has.mybatis.utils;

import com.has.mybatis.bo.BaseAnnotationBo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>com.has.mybatis.utils</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/10/9
 */
public class EntityInitialize implements BaseInitialize {

    @Override
    public <T extends Serializable, Bo extends BaseAnnotationBo> void initialize(List<Bo> bo, T entity) {

    }
}
