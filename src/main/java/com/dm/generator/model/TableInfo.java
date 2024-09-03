package com.dm.generator.model;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * 表对象信息
 *
 * @author: wenjie
 * @date: 2021/3/27 15:56
 */
@Data
public class TableInfo {

    private String appName;

    private String feignServerName;

    private String tableName;

    private String tableRemarks;

    private String tableClassName;

    private String tableNameLower;

    private String tableNameFirst;

    private String moduleName;

    private List<RowInfo> rows;

    private List<RowInfo> keys;

    private List<RowInfo> noKeys;

    private Set<String> importClasss;

}
