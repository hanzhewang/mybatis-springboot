package com.has.mybatis.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.has.mybatis.adapter.AnnotationAdapter;
import com.has.mybatis.adapter.AnnotationAdapterFactory;
import com.has.mybatis.bo.BaseAnnotationBo;
import com.has.mybatis.enums.DbWriteTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * <p>实体解析</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/10/8
 */
@Slf4j
public class EntityParser {

    /**
     * 注解适配器工厂
     */
    private List<AnnotationAdapterFactory> factories = Lists.newCopyOnWriteArrayList();

    /**
     * 工厂缓存
     */
    private Map<Annotation, AnnotationAdapter<Annotation, BaseAnnotationBo>> annotationAdapterCache = Maps.newConcurrentMap();

    /**
     * 缓存本地 - 类-Field
     */
    private Map<Class<?>, Field[]> classFieldCache = Maps.newConcurrentMap();

    /**
     * 缓存本地 - 类-Field
     */
    private Map<Class<?>, List<? extends BaseAnnotationBo>> classBoMap = Maps.newConcurrentMap();

    /**
     * @param factory
     */
    public void addAdapterFactory(AnnotationAdapterFactory factory) {
        this.factories.add(factory);
    }

    /**
     * @param factories
     */
    public void addAdapterFactoryAll(List<AnnotationAdapterFactory> factories) {
        this.factories.addAll(factories);
    }

    /**
     * 解析实体
     *
     * @param entity
     * @param writeType
     * @param <T>
     * @param <Bo>
     * @return
     */
    public <T extends Serializable, Bo extends BaseAnnotationBo> List<Bo> parser(T entity, DbWriteTypeEnum writeType) {
        if (entity == null) {
            return null;
        }
        Class<?> raw = entity.getClass();
        List<? extends BaseAnnotationBo> boList = classBoMap.get(raw);
        if (CollectionUtils.isEmpty(boList)) {
            boList = this.parser(entity, writeType, raw);
        } else {
            // 本地缓存 - 反射静态属性
            boList = this.parserRelyCache(entity, boList);
        }
        return (List<Bo>) boList;
    }

    /**
     * 解析实体
     *
     * @param entity
     * @param writeType
     * @param raw
     * @param <T>
     * @param <Bo>
     * @return
     */
    private <T extends Serializable, Bo extends BaseAnnotationBo> List<Bo> parser(T entity, DbWriteTypeEnum writeType, Class<?> raw) {
        Field[] fields = classFieldCache.get(raw);
        if (fields == null) {
            fields = raw.getDeclaredFields();
            classFieldCache.put(raw, fields);
        }
        List<Bo> boList = Lists.newArrayList();
        for (Field field : fields) {
            List<Bo> baseAnnotationBo = parser(field, entity, writeType);
            if (baseAnnotationBo != null) {
                boList.addAll(baseAnnotationBo);
            }
        }
        return boList;
    }

    /**
     * 解析实体
     * 反射静态属性 - 本地缓存
     *
     * @param entity
     * @param boList
     * @param <T>
     * @param <Bo>
     * @return
     */
    private <T extends Serializable, Bo extends BaseAnnotationBo> List<Bo> parserRelyCache(T entity, List<Bo> boList) {
        for (Bo bo : boList) {
            Object fieldValue = this.getFieldValue(bo.getField(), entity);
            bo.setValue(fieldValue);
        }
        return boList;
    }


    private <T extends Serializable, R extends BaseAnnotationBo> List<R> parser(Field field, T entity, DbWriteTypeEnum writeType) {
        Annotation[] annotations = field.getDeclaredAnnotations();
        List<R> list = Lists.newArrayList();
        R annotationBo = null;
        for (Annotation annotation : annotations) {
            AnnotationAdapter<Annotation, BaseAnnotationBo> cached = this.getAdapter(annotation);
            if (cached == null) {
                continue;
            }
            annotationBo = (R) cached.read(field, annotation, writeType);
            if (annotationBo == null) {
                continue;
            }
            this.configure(annotationBo, field, entity);
            list.add(annotationBo);
        }
        return list;
    }

    /**
     * 注解适配器
     *
     * @param annotation
     * @return
     */
    public AnnotationAdapter<Annotation, BaseAnnotationBo> getAdapter(Annotation annotation) {
        if (annotation == null) {
            return null;
        }
        AnnotationAdapter<Annotation, BaseAnnotationBo> cached = this.annotationAdapterCache.get(annotation);
        if (cached != null) {
            return cached;
        }
        AnnotationAdapter<Annotation, BaseAnnotationBo> candidate = null;
        for (AnnotationAdapterFactory factory : this.factories) {
            candidate = factory.create(annotation);
            if (candidate != null) {
                this.annotationAdapterCache.put(annotation, candidate);
                break;
            }
        }
        return candidate;
    }

    private <Bo extends BaseAnnotationBo, T extends Serializable> void configure(Bo bo, Field field, T entity) {
        bo.setField(field);
        bo.setName(field.getName());
        bo.setType(field.getType());
        Object fieldValue = this.getFieldValue(field, entity);
        bo.setValue(fieldValue);
    }

    /**
     * @param field
     * @param entity
     * @param <T>
     * @return
     */
    protected <T extends Serializable> Object getFieldValue(Field field, T entity) {
        try {
            field.setAccessible(true);
            return field.get(entity);
        } catch (Exception ex) {
            // todo
            throw new RuntimeException();
        }
    }

}

