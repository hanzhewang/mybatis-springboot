package com.has.mybatis.enums;

import lombok.AllArgsConstructor;

/**
 * <p>操作类型</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/2/25 11:50 PM
 * @since 1.8
 */
@AllArgsConstructor
public enum DbWriteTypeEnum {
    /**
     * 插入
     */
    Insert,
    /**
     * 更新
     */
    Update,
    /**
     * 更新
     */
    UpdateSelective,
    /**
     * 删除
     */
    Delete,
}
