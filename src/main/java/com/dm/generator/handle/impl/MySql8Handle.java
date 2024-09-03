package com.dm.generator.handle.impl;

import com.dm.generator.handle.TableHandle;
import com.dm.generator.model.RowInfo;
import com.dm.generator.model.TableInfo;
import com.dm.generator.utils.MySql8Utils;
import com.dm.generator.utils.TypeUtils;
import com.dm.generator.utils.Utils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class MySql8Handle implements TableHandle {

    private QueryRunner qr = new QueryRunner();

    @Override
    public TableInfo getTableInfo(String owner, String tableName, Connection conn, String prefix) throws SQLException {
        System.out.println("process tableName : " + tableName);
        TableInfo tableInfo = new TableInfo();
        List<RowInfo> rows = new ArrayList<>();
        List<RowInfo> keys = new ArrayList<>();
        Set<String> importClassSet = new HashSet<>();
        List<RowInfo> noKeys = new ArrayList<>();
        tableInfo.setRows(rows);
        tableInfo.setKeys(keys);
        tableInfo.setNoKeys(noKeys);
        tableInfo.setImportClasss(importClassSet);

        /* 获取表的元数据 */
        String TableRemarks = MySql8Utils.getComment(conn, qr, tableName);
        // 添加表名
        tableInfo.setTableName(tableName);
        // 添加字段名
        tableInfo.setTableClassName(Utils.formatName(Utils.className(tableName, prefix), false));
        // 小写的字段
        tableInfo.setTableNameLower(tableInfo.getTableClassName().toLowerCase());
        // 小写的字段
        tableInfo.setTableNameFirst(Utils.formatName(tableName, true));
        // 获取表的备注
        tableInfo.setTableRemarks(TableRemarks);

        /* 获取表字段数据 */
        addRowInfo(conn, tableName, rows, keys, noKeys);
        rows.forEach(v -> Optional.ofNullable(TypeUtils.getImportClass(v.getJavaType())).ifPresent(importClassSet::add));
        return tableInfo;
    }

    private void addRowInfo(Connection conn, String tableName, List<RowInfo> rows, List<RowInfo> keys,
                            List<RowInfo> noKeys) throws SQLException {
        /* 获取主键元数据 */
        List<Map<String, Object>> columnList = MySql8Utils.getRowMeta(conn, qr, tableName);
        List<String> keyNames = new ArrayList<>();
        keyNames.add("id");

        /* 获取表的字段元数据 */
        for (Map<String, Object> column : columnList) {
            String columnName = Utils.get(column, "Field");
            String type = Utils.get(column, "Type");
            String columnDataType = getType(type);
            String columnRemarks = Utils.get(column, "Comment");
            RowInfo rowInfo = new RowInfo();
            rowInfo.setColumnName(columnName);
            rowInfo.setColumnDataType(columnDataType);
            rowInfo.setColumnTypeName(columnDataType);
            rowInfo.setColumnRemarks(columnRemarks);
            rowInfo.setColumnDef(Utils.get(column, "Default"));
            rowInfo.setIsNull(Objects.equals(Utils.get(column, "Null"), "YES"));
            rowInfo.setIsAuto(Objects.equals(Utils.get(column, "Extra"), "auto_increment"));
            rowInfo.setJavaName(Utils.formatAttributeName(columnName));
            rowInfo.setJavaType(TypeUtils.pgType2JavaType(columnDataType));
            rowInfo.setJavaNameFormat(Utils.formatName(columnName, false));

            if (keyNames.contains(columnName)) {
                keys.add(rowInfo);
            } else {
                noKeys.add(rowInfo);
            }
            rows.add(rowInfo);
        }
    }

    private String getType(String type) {
        int i = type.indexOf("(");
        if (i != -1) {
            return type.substring(0, i);
        }
        return type;
    }

}