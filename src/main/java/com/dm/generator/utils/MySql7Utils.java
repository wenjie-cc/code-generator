package com.dm.generator.utils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author: wenjie
 * @date: 2021/3/27 15:56
 */
public interface MySql7Utils {

    /**
     * @param conn
     * @param qr
     * @param tableName
     * @return
     * @throws SQLException
     */
    static String getComment(Connection conn, QueryRunner qr, String tableName) throws SQLException {
        Map<String, Object> query = qr.query(conn, "SELECT table_comment FROM information_schema.TABLES WHERE table_schema = ? and table_name=?", new MapHandler(), conn.getCatalog(), tableName);
        return query.get("table_comment") == null ? null : query.get("table_comment").toString();
    }
}
