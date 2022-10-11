package com.has.mybatis.utils;

import com.has.mybatis.bo.BaseAnnotationBo;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * <p>默认验证器</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/10/9
 */
public class EntityValidator implements BaseValidator {

    @Override
    public <T extends BaseAnnotationBo> void validator(List<T> target) {
        if (target == null) {
            return;
        }
        for (T t : target) {
            if (Boolean.TRUE.equals(t.getRequired()) && t.getValue() == null) {
                throw new NullPointerException();
            }
            if (Boolean.TRUE.equals(t.getRequired()) && StringUtils.isBlank(t.getValue().toString())) {
                throw new NullPointerException();
            }
        }
    }
}
