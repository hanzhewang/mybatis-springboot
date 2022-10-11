package com.has.mybatis.spring.config;

import com.has.mybatis.adapter.*;
import com.has.mybatis.builder.EntityBuilder;
import com.has.mybatis.builder.QueryBuilder;
import com.has.mybatis.utils.EntityInitialize;
import com.has.mybatis.utils.EntityParser;
import com.has.mybatis.utils.EntityValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * <p>自动装配</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/9/30
 */
@Slf4j
@Configuration
public class CurdConfigurer {

    @Resource
    private CurdProperties curdProperties;

    @Bean
    @ConditionalOnMissingBean
    QueryBuilder getQueryBuilder() {
        log.debug("register build query");
        return new QueryBuilder();
    }

    @Bean
    @ConditionalOnMissingBean
    public EntityBuilder getEntityBuilder() {
        log.debug("register build entity");
        EntityBuilder builder = new EntityBuilder()
                .registerEntityParser(new EntityParser())
                .registerAdapterFactory(PrimaryKeyAnnotationAdapter.FACTORY)
                .registerAdapterFactory(RouterAnnotationAdapter.FACTORY)
                .registerAdapterFactory(VersionAnnotationAdapter.FACTORY)
                .registerAdapterFactory(UniqueAnnotationAdapter.FACTORY)
                .registerAdapterFactory(ParentChildAnnotationAdapter.FACTORY)
                .registerAdapterFactory(DeleteAnnotationAdapter.FACTORY)
                .registerAdapterFactory(StateAnnotationAdapter.FACTORY);
        if (curdProperties.getSwitchInitialize()) {
            builder.registerEntityInitialize(new EntityInitialize());
        }
        if (curdProperties.getSwitchValidator()) {
            builder.registerEntityValidator(new EntityValidator());
        }
        return builder;
    }
}
