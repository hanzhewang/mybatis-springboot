package com.has.mybatis.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>CurdDao</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/2/22 10:57 PM
 * @since 1.8
 */
public interface CurdDao<T extends Serializable> {

    /**
     * 明细查询
     *
     * @param param
     * @return
     */
    T getByMap(Map<String, Object> param);

    /**
     * 列表查询
     *
     * @param param
     * @return
     */
    List<T> findByMap(Map<String, Object> param);

    /**
     * 列表查询
     *
     * @param param
     * @return
     */
    Long count(Map<String, Object> param);

    /**
     * 插入
     *
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 批量插入
     *
     * @param entity
     * @return
     */
    int batchInsert(T entity);

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    int updateByPk(T entity);

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    int updateSelectiveByPk(T entity);

    /**
     * 删除
     *
     * @param entity
     * @return
     */
    int deleteByPk(T entity);

}
