package com.has.mybatis.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>主键类型</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/9/27
 */
@Getter
@AllArgsConstructor
public enum PrimaryKeyTypeEnum {

    AUTO(1, "自增主键"),
    NON_AUTO(2, "非自增主键"),
    UNION(3, "联合主键"),
    ;
    /**
     * 类型编号
     */
    private Integer code;
    /**
     * 类型描述
     */
    private String name;

    /**
     * 编号转换枚举
     *
     * @param code
     * @return
     */
    public static PrimaryKeyTypeEnum valueOf(Integer code) {
        PrimaryKeyTypeEnum[] enums = PrimaryKeyTypeEnum.values();
        for (PrimaryKeyTypeEnum primaryKeyTypeEnum : enums) {
            if (primaryKeyTypeEnum.getCode().equals(code)) {
                return primaryKeyTypeEnum;
            }
        }
        return null;
    }
}
