package com.dm.generator.handle.impl;

import com.dm.generator.handle.TableHandle;
import com.dm.generator.model.RowInfo;
import com.dm.generator.model.TableInfo;
import com.dm.generator.utils.TypeUtils;
import com.dm.generator.utils.Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class PgHandle implements TableHandle {

    private final String TABLE_SQL = "SELECT" + "  relname                                                   AS name,"
            + "  cast(obj_description(relfilenode, 'pg_class') AS VARCHAR) AS comment" + " FROM pg_class"
            + " WHERE relkind = 'r' AND relname NOT LIKE 'pg_%' AND relname NOT LIKE 'sql_%' AND relname = ?";

    private final String COLUMN_SQL = "SELECT" + "  a.attname AS name," + "  t.typname AS type,"
            + "  a.attlen AS length," + "  a.atttypmod AS lengthvar," + "  a.attnotnull AS is_null,"
            + "  col_description(a.attrelid, a.attnum) AS comment"
            + " FROM pg_attribute a LEFT JOIN pg_class c ON a.attrelid = c.oid"
            + "  LEFT JOIN pg_type t ON a.atttypid = t.oid"
            + " WHERE c.relname = ? AND a.attnum > 0 AND c.relkind = 'r'" + " ORDER BY a.attnum";

    private QueryRunner query = new QueryRunner();

    @Override
    public TableInfo getTableInfo(String owner, String tableName, Connection conn, String prefix) throws SQLException {
        System.out.println("proccess tableName : " + tableName);
        TableInfo tableInfo = new TableInfo();
        List<RowInfo> rows = new ArrayList<>();
        List<RowInfo> keys = new ArrayList<>();
        List<RowInfo> noKeys = new ArrayList<>();
        Set<String> importClasss = new HashSet<>();
        tableInfo.setRows(rows);
        tableInfo.setKeys(keys);
        tableInfo.setNoKeys(noKeys);
        tableInfo.setImportClasss(importClasss);
        /* 获取表的元数据 */
        Map<String, Object> data = this.query.query(conn, TABLE_SQL, new MapHandler(), tableName);

        String TableRemarks = Utils.get(data, "comment");
        // 添加表名
        tableInfo.setTableName(tableName);
        // 添加表名
        tableInfo.setTableClassName(Utils.formatName(Utils.className(tableName, prefix), false));
        // 小写的字段
        tableInfo.setTableNameLower(tableInfo.getTableClassName().toLowerCase());
        // 小写的字段
        tableInfo.setTableNameFirst(Utils.formatName(tableName, true));
        // 获取表的备注
        tableInfo.setTableRemarks(TableRemarks);

        /* 获取表字段数据 */
        addRowInfo(conn, tableName, rows, keys, noKeys);
        rows.forEach(v -> Optional.ofNullable(TypeUtils.getImportClass(v.getJavaType())).ifPresent(importClasss::add));
        return tableInfo;
    }

    private void addRowInfo(Connection conn, String tableName, List<RowInfo> rows, List<RowInfo> keys,
                            List<RowInfo> noKeys) throws SQLException {
        /* 获取主键元数据 */
        List<Map<String, Object>> columnList = this.query.query(conn, COLUMN_SQL, new MapListHandler(), tableName);

        List<String> keyNames = new ArrayList<>();
        keyNames.add("id");

        /* 获取表的字段元数据 */
        for (Map<String, Object> column : columnList) {
            String columnName = Utils.get(column, "name");
            if (Objects.equals(columnName, "oid") || Objects.equals(columnName, "create_time")
                    || Objects.equals(columnName, "update_time") || Objects.equals(columnName, "delete_time")
                    || Objects.equals(columnName, "create_by") || Objects.equals(columnName, "update_by")
                    || Objects.equals(columnName, "delete_by") || Objects.equals(columnName, "version")) {
                continue;
            }
            String columnDataType = Utils.get(column, "type");
            String columnTypeName = Utils.get(column, "type");
            String columnRemarks = Utils.get(column, "comment");
            String columnIsNUll = Utils.get(column, "is_null");
            if (columnDataType.split(" ").length > 1) {
                columnDataType = columnDataType.split(" ")[0];
            }
            RowInfo rowInfo = new RowInfo();
            rowInfo.setColumnName(columnName);
            rowInfo.setColumnDataType(columnDataType);
            rowInfo.setColumnRemarks(columnRemarks);
            rowInfo.setColumnIsNull(columnIsNUll);
            rowInfo.setColumnTypeName(columnTypeName);
            rowInfo.setIsNull(false);
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

}