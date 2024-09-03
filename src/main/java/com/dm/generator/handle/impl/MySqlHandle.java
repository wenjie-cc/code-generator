package com.dm.generator.handle.impl;

import com.dm.generator.handle.TableHandle;
import com.dm.generator.model.RowInfo;
import com.dm.generator.model.TableInfo;
import com.dm.generator.utils.MetaDataUtils;
import com.dm.generator.utils.MySql7Utils;
import com.dm.generator.utils.TypeUtils;
import com.dm.generator.utils.Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class MySqlHandle implements TableHandle {

    private final String TABLE_INFO_SQL = "SELECT table_name FROM information_schema.tables WHERE table_schema = ?";

    private QueryRunner qr = new QueryRunner();

    @Override
    public List<String> getAllTableInfo(Connection conn) throws SQLException {
        List<Map<String, Object>> data = this.qr.query(conn, TABLE_INFO_SQL, new MapListHandler(),
                conn.getCatalog());
        if (data != null && !data.isEmpty()) {
            return data.stream().map(e -> String.valueOf(e.get("table_name"))).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

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
        Map<String, String> tableMetaData = MetaDataUtils.getTableMetaData(conn, tableName);
        String TableRemarks = MySql7Utils.getComment(conn, qr, tableName);
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
        rows.forEach(v -> Optional.ofNullable(TypeUtils.getImportClass(v.getJavaType())).ifPresent(importClasss::add));
        return tableInfo;
    }

    private void addRowInfo(Connection conn, String tableName, List<RowInfo> rows, List<RowInfo> keys,
                            List<RowInfo> noKeys) throws SQLException {
        /* 获取主键元数据 */
        List<Map<String, String>> keyMetaData = MetaDataUtils.getPrimaryKeysMetaData(conn, tableName);
        List<String> keyNames = new ArrayList<>();
        for (Map<String, String> map : keyMetaData) {
            keyNames.add(map.get(MetaDataUtils.COLUMN_NAME));
        }

        /* 获取表的字段元数据 */
        List<Map<String, String>> columnsMetaData = MetaDataUtils.getColumnsMetaData(conn, tableName);
        for (Map<String, String> column : columnsMetaData) {
            String columnName = column.get(MetaDataUtils.COLUMN_NAME);
            String columnDataType = column.get(MetaDataUtils.COLUMN_DATA_TYPE);
            String columnTypeName = column.get(MetaDataUtils.COLUMN_TYPE_NAME);
            String columnSize = column.get(MetaDataUtils.COLUMN_SIZE);
            String columnDecimal = column.get(MetaDataUtils.COLUMN_DECIMAL_DIGITS);
            String columnRemarks = column.get(MetaDataUtils.COLUMN_REMARKS);
            String columnDef = column.get(MetaDataUtils.COLUMN_DEF);
            String columnIsNUll = column.get(MetaDataUtils.COLUMN_IS_NULLABLE);
            String columnIsAutoincrement = column.get(MetaDataUtils.COLUMN_IS_AUTOINCREMENT);

            RowInfo rowInfo = new RowInfo();
            rowInfo.setColumnName(columnName);
            rowInfo.setColumnDataType(columnDataType);
            rowInfo.setColumnSize(columnSize);
            rowInfo.setColumnDecimal(columnDecimal);
            rowInfo.setColumnRemarks(columnRemarks);
            if (columnDef == null || columnDef.equals("null")) {
                rowInfo.setColumnDef(null);
            } else {
                rowInfo.setColumnDef(columnDef);
            }

            rowInfo.setColumnIsNull(columnIsNUll);
            rowInfo.setColumnTypeName(columnTypeName);

            if (columnIsAutoincrement != null && columnIsAutoincrement.equals("YES")) {
                rowInfo.setIsAuto(true);
            }

            if (columnIsNUll != null && columnIsNUll.equals("YES")) {
                rowInfo.setIsNull(true);
            }

            rowInfo.setJavaName(Utils.formatAttributeName(columnName));
            rowInfo.setJavaType(TypeUtils.getJavaType(columnTypeName));
            rowInfo.setJavaNameFormat(Utils.formatName(columnName, false));
            if (rowInfo.getJavaType() == null) {
                throw new RuntimeException(
                        String.format("java type is null! tableName = %s, columnName = %s, columnTypeName = %s",
                                tableName, columnName, columnTypeName));
            }

            if (keyNames.contains(columnName)) {
                keys.add(rowInfo);
            } else {
                noKeys.add(rowInfo);
            }
            rows.add(rowInfo);
        }

        /* 获取表的字段索引 */
        columnsMetaData = MetaDataUtils.getIndexMetaData(conn, tableName);
        for (Map<String, String> column : columnsMetaData) {
            for (RowInfo row : rows) {
                if (row.getColumnName().equals(column.get("COLUMN_NAME"))) {
                    if (column.get("NON_UNIQUE") != null && column.get("NON_UNIQUE").equals("0")) {
                        row.setIsUnique(true);
                    }
                }
            }
        }
    }

}