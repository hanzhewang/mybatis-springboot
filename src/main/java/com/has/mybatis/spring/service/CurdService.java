package com.has.mybatis.spring.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.has.mybatis.builder.EntityBuilder;
import com.has.mybatis.builder.QueryBuilder;
import com.has.mybatis.dao.CurdDao;
import com.has.mybatis.enums.DbWriteTypeEnum;
import com.has.mybatis.spring.config.CurdProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>基础服务</p>
 * 基础的增删改查服务
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/2/26 12:11 AM
 * @since 1.8
 */
@Slf4j
public abstract class CurdService<T extends Serializable, Dao extends CurdDao<T>> {

    /**
     * 查询-实体建造者
     */
    @Resource
    private QueryBuilder queryBuilder;
    /**
     * 增删改-实体建造者
     */
    @Resource
    private EntityBuilder entityBuilder;
    /**
     * 配置
     */
    @Resource
    private CurdProperties curdProperties;

    /**
     * 持久层服务
     *
     * @return
     */
    abstract Dao getDao();

    /**
     * 明细查询
     *
     * @param query
     * @return
     */
    public T getByMap(Map<String, Object> query) {
        query = queryBuilder.build(query);
        return this.getDao().getByMap(query);
    }

    /**
     * 列表查询
     *
     * @param query
     * @return
     */
    public List<T> findByMap(Map<String, Object> query) {
        query = queryBuilder.build(query);
        return this.getDao().findByMap(query);
    }


    /**
     * 列表总数
     *
     * @param query
     * @return
     */
    public Long count(Map<String, Object> query) {
        query = queryBuilder.build(query);
        return this.getDao().count(query);
    }

    /**
     * 分页查询
     *
     * @param query
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public PageInfo<T> getPage(Map<String, Object> query, Integer pageIndex, Integer pageSize) {
        query = new QueryBuilder().build(query);
        if (pageIndex == null || pageIndex.intValue() < curdProperties.getPageIndex()) {
            pageIndex = curdProperties.getPageIndex();
        }
        if (pageSize == null || pageSize.intValue() == 0) {
            pageIndex = curdProperties.getPageSize();
        }
        if (pageSize.intValue() > curdProperties.getPageSizeMax()) {
            pageIndex = curdProperties.getPageSizeMax();
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<T> data = this.getDao().findByMap(query);
        PageInfo<T> pageInfo = new PageInfo(data);
        return pageInfo;
    }

    /**
     * 保存
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int save(T entity) {
        if (getPk(entity) == null) {
            return this.insert(entity);
        } else {
            return this.updateByPk(entity);
        }
    }

    /**
     * 插入
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int insert(T entity) {
        entity = entityBuilder.build(entity, DbWriteTypeEnum.Insert);
        return this.getDao().insert(entity);
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateByPk(T entity) {
        entity = entityBuilder.build(entity, DbWriteTypeEnum.Update);
        return this.getDao().updateByPk(entity);
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateSelectiveByPk(T entity) {
        entity = entityBuilder.build(entity, DbWriteTypeEnum.UpdateSelective);
        return this.getDao().updateSelectiveByPk(entity);
    }

    /**
     * 删除
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPk(T entity) {
        entity = entityBuilder.build(entity, DbWriteTypeEnum.Delete);
        return this.getDao().deleteByPk(entity);
    }

    /**
     * 获取主键
     *
     * @param entity
     * @return
     */
    public Map<String, Object> getPk(T entity) {
        Map<String, Object> map = entityBuilder.buildPrimaryKey(entity);
        if (MapUtils.isEmpty(map)) {
            throw new NullPointerException();
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value == null) {
                throw new NullPointerException();
            }
            if (StringUtils.isBlank(value.toString())) {
                throw new NullPointerException();
            }
        }
        return map;
    }
}