package com.dm.generator.model;

import lombok.Data;

/**
 * 列对象信息
 *
 * @author: wenjie
 * @date: 2021/3/27 15:56
 */
@Data
public class RowInfo {

    private String columnName;

    private String columnDataType;

    private String columnTypeName;

    private String columnSize;

    private String columnDecimal;

    private String columnRemarks;

    private String columnDef;

    private String columnIsNull;

    private Boolean isAuto = false;

    private Boolean isUnique = false;

    private Boolean isNull = false;

    private String javaName;

    private String javaType;

    private String javaNameFormat;

}
