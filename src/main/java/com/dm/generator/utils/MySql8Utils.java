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
 * @date: 2021/3/27 15:56
 */
public interface MySql8Utils {

    /**
     * @param conn
     * @param qr
     * @param tableName
     * @return
     * @throws SQLException
     */
    static String getComment(Connection conn, QueryRunner qr, String tableName) throws SQLException {
        Map<String, Object> query = qr.query(conn, "show table status like '" + tableName + "'", new MapHandler());
        return query.get("Comment") == null ? null : query.get("Comment").toString();
    }

    static List<Map<String, Object>> getRowMeta(Connection conn, QueryRunner qr, String tableName) throws SQLException {
        return qr.query(conn, "show full fields from " + tableName, new MapListHandler());
    }

}
