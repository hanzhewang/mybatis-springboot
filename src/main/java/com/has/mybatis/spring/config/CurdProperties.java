package com.has.mybatis.spring.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * <p>属性配置</p>
 *
 * @author wanghanzhe
 * @version 1.0.0
 * @date 2022/9/29
 */
@Slf4j
@Data
@ConfigurationProperties(
        prefix = "spring.mybatis.curd"
)
public class CurdProperties {

    /**
     * 分页信息-默认行数
     */
    private Integer pageIndex;
    /**
     * 分页信息-默认行数
     */
    private Integer pageSize;
    /**
     * 分页信息-最大行数
     */
    private Integer pageSizeMax;
    /**
     * 分页信息-最大行数
     */
    private Boolean switchInitialize;
    /**
     * 分页信息-最大行数
     */
    private Boolean switchValidator;

    public Integer getPageIndex() {
        if (this.pageIndex == null) {
            return CurdConstant.DEFAULT_PAGE_INDEX;
        }
        return this.pageIndex;
    }

    public Integer getPageSize() {
        if (this.pageSize == null) {
            return CurdConstant.DEFAULT_PAGE_SIZE;
        }
        return this.pageSize;
    }

    public Integer getPageSizeMax() {
        if (this.pageSizeMax == null) {
            return CurdConstant.DEFAULT_PAGE_SIZE_MAX;
        }
        return this.pageSizeMax;
    }


    public Boolean getSwitchInitialize() {
        if (this.switchInitialize == null) {
            return Boolean.TRUE;
        }
        return this.switchInitialize;
    }

    public Boolean getSwitchValidator() {
        if (this.switchValidator == null) {
            return Boolean.TRUE;
        }
        return switchValidator;
    }

}
