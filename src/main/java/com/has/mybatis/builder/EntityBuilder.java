package com.has.mybatis.builder;

import com.google.common.collect.Lists;
import com.has.mybatis.adapter.AnnotationAdapterFactory;
import com.has.mybatis.annotation.PrimaryKey;
import com.has.mybatis.annotation.Unique;
import com.has.mybatis.bo.BaseAnnotationBo;
import com.has.mybatis.enums.DbWriteTypeEnum;
import com.has.mybatis.utils.BaseValidator;
import com.has.mybatis.utils.EntityInitialize;
import com.has.mybatis.utils.EntityParser;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>增删改实体建造者</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/9/30
 */
public class EntityBuilder {

    /**
     * 注解适配器工厂
     */
    private final List<AnnotationAdapterFactory> factories;

    /**
     * 实体解析器
     */
    private EntityParser entityParser;

    /**
     * 初始化
     */
    private EntityInitialize entityInitialize;

    /**
     * 实体验证器
     */
    private BaseValidator entityValidator;

    /**
     * 默认构造
     */
    public EntityBuilder() {
        this.factories = Lists.newArrayList();
    }

    /**
     * 注册实体解析器
     *
     * @param entityParser
     * @return
     */
    public EntityBuilder registerEntityParser(EntityParser entityParser) {
        this.entityParser = entityParser;
        if (CollectionUtils.isNotEmpty(this.factories)) {
            this.entityParser.addAdapterFactoryAll(this.factories);
        }
        return this;
    }

    /**
     * 注册适配工厂
     */
    public EntityBuilder registerAdapterFactory(AnnotationAdapterFactory factory) {
        this.factories.add(factory);
        if (entityParser != null) {
            this.entityParser.addAdapterFactory(factory);
        }
        return this;
    }

    /**
     * 注册实体验证器
     *
     * @return
     */
    public EntityBuilder registerEntityValidator(BaseValidator entityValidator) {
        this.entityValidator = entityValidator;
        return this;
    }

    /**
     * 注册实体初始化
     *
     * @return
     */
    public EntityBuilder registerEntityInitialize(EntityInitialize entityInitialize) {
        this.entityInitialize = entityInitialize;
        return this;
    }


    /**
     * 创建实体对象
     *
     * @param entity
     * @param writeType
     * @param <T>
     * @return
     */
    public <T extends Serializable> T build(T entity, DbWriteTypeEnum writeType) {
        List<BaseAnnotationBo> boList = entityParser.parser(entity, writeType);
        if (entityInitialize != null) {
            entityInitialize.initialize(boList, entity);
        }
        if (entityValidator != null) {
            entityValidator.validator(boList);
        }
        return entity;
    }

    /**
     * 主键信息
     *
     * @param entity
     * @param <T>
     * @return
     */
    public <T extends Serializable> Map<String, Object> buildPrimaryKey(T entity) {
        List<BaseAnnotationBo> boList = entityParser.parser(entity, null);
        if (CollectionUtils.isEmpty(boList)) {
            throw new NullPointerException();
        }
        return boList.stream().filter(bo -> bo != null && bo instanceof PrimaryKey).collect(Collectors.toMap(bo -> bo.getName(), bo -> bo.getValue()));
    }

    /**
     * 唯一信息
     *
     * @param entity
     * @param <T>
     * @return
     */
    public <T extends Serializable> Map<String, Object> buildUniqueKey(T entity) {
        List<BaseAnnotationBo> boList = entityParser.parser(entity, null);
        if (CollectionUtils.isEmpty(boList)) {
            throw new NullPointerException();
        }
        return boList.stream().filter(bo -> bo != null && bo instanceof Unique).collect(Collectors.toMap(bo -> bo.getName(), bo -> bo.getValue()));
    }


}
