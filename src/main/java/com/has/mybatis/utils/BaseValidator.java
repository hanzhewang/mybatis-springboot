package com.has.mybatis.utils;

import com.has.mybatis.bo.BaseAnnotationBo;

import java.util.List;

/**
 * <p>验证器接口</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/10/9
 */
public interface BaseValidator {

    /**
     * 验证
     *
     * @param target
     * @param <Bo>
     */
    <Bo extends BaseAnnotationBo> void validator(List<Bo> target);
}
