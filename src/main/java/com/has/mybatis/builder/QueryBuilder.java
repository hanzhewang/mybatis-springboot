package com.has.mybatis.builder;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <p>com.has.mybatis.builder</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/9/29
 */
public class QueryBuilder {

    public QueryBuilder() {

    }

    public Map<String, Object> build(Map<String, Object> query) {
        if (query == null) {
            query = Maps.newHashMap();
        }
        return query;
    }

}
