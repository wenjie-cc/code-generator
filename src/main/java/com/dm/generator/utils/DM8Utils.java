package com.dm.generator.utils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author: wenjie
 * @date: 2024/09/03 15:56
 */
public interface DM8Utils {

    /**
     * @param conn
     * @param qr
     * @param tableName
     * @return
     * @throws SQLException
     */
    static String getComment(Connection conn, QueryRunner qr, String tableName, String owner) throws SQLException {
        Map<String, Object> query = qr.query(conn, "SELECT * FROM ALL_TAB_COMMENTS WHERE TABLE_NAME ='" + tableName + "' AND OWNER = '" + owner + "'", new MapHandler());
        return query.get("COMMENTS") == null ? null : query.get("COMMENTS").toString();
    }

    static List<Map<String, Object>> getRowMeta(Connection conn, QueryRunner qr, String tableName, String owner) throws SQLException {
        return qr.query(conn, "SELECT A.COLUMN_NAME columnName," +
                " A.DATA_TYPE type, " +
                " A.DATA_LENGTH, " +
                " A.NULLABLE, " +
                " B.COMMENTS AS COMMENTS" +
                " FROM ALL_TAB_COLUMNS A " +
                " LEFT JOIN " +
                " ALL_COL_COMMENTS B ON A.OWNER = B.OWNER AND A.TABLE_NAME = B.TABLE_NAME AND A.COLUMN_NAME = B.COLUMN_NAME " +
                " WHERE A.TABLE_NAME = '" + tableName + "' AND A.OWNER = '" + owner + "'", new MapListHandler());
    }

}
