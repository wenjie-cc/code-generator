package com.dm.generator.utils;

import com.dm.generator.Config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mysql元数据查询 （详细的字段的解释，请查看JDBC中的DatabaseMetaData的API）
 */
public class MetaDataUtils {

    /**
     * 表名（查询表 和 查询主键）
     */
    public static final String TABLE_NAME = "TABLE_NAME";

    /**
     * 表备注（查询表 和 查询主键）
     */
    public static final String TABLE_REMARKS = "REMARKS";

    /**
     * 字段名（查询字段 和 查询主键）
     */
    public static final String COLUMN_NAME = "COLUMN_NAME";

    /**
     * 字段数据类型
     */
    public static final String COLUMN_DATA_TYPE = "DATA_TYPE";

    /**
     * 字段类型名称
     */
    public static final String COLUMN_TYPE_NAME = "TYPE_NAME";

    /**
     * 字段长度
     */
    public static final String COLUMN_SIZE = "COLUMN_SIZE";

    /**
     * 字段小数长度
     */
    public static final String COLUMN_DECIMAL_DIGITS = "DECIMAL_DIGITS";

    /**
     * 字段缺省值
     */
    public static final String COLUMN_DEF = "COLUMN_DEF";

    /**
     * 字段备注
     */
    public static final String COLUMN_REMARKS = "REMARKS";

    /**
     * 字段是否为NULL
     */
    public static final String COLUMN_IS_NULLABLE = "IS_NULLABLE";

    /**
     * 字段是否自增长
     */
    public static final String COLUMN_IS_AUTOINCREMENT = "IS_AUTOINCREMENT";

    /**
     * 索引名
     */
    public static final String INDEX_NAME = "INDEX_NAME";

    /**
     * 索引名称为“PRIMARY”的时候为主键
     */
    public static final String INDEX_NAME_PRIMARY = "PRIMARY";

    /**
     * 索引字段名
     */
    public static final String INDEX_COLUMN_NAME = "COLUMN_NAME";

    /**
     * 索引值是否可以不唯一（0：表示唯一；1：表示不唯一）
     */
    public static final String INDEX_NON_UNIQUE = "NON_UNIQUE";

    /**
     * 索引中的列序列号（多列索引的序号）
     */
    public static final String INDEX_ORDINAL_POSITION = "ORDINAL_POSITION";

    /**
     * mysql驱动类
     */
    public static String MYSQL_DRIVER = "com.mysql.jdbc.Driver";

    /**
     * mariadb驱动类
     */
    public static String MARIADB_DRIVER = "org.mariadb.jdbc.Driver";

    /**
     * 查看ResultSetMetaData明细（详细请查看）
     *
     * @param conn
     * @param tablename
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void MySqlDataType2(Connection conn, String tablename)
            throws SQLException, IOException, ClassNotFoundException {
        System.out.println(tablename);
        System.out.println("------------------------------------ 输入内容 --------------------------------------------");
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from " + tablename + " limit 0");
            ResultSetMetaData rsmd = rs.getMetaData();
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            boolean b = true;
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String name = rsmd.getColumnName(i).toLowerCase(); // 获取字段名称
                int type = rsmd.getColumnType(i); // 获得指定列的数据类型，该类型值对应 java.sql.Types
                // 类中的类型
                String typeName = rsmd.getColumnTypeName(i); // 获取制定咧的数据类型名
                int precision = rsmd.getPrecision(i); // 精确度(类型的长度)
                int scale = rsmd.getScale(i); // 小数点后的位数
                String columnClassName = rsmd.getColumnClassName(i); // 对应数据类型的类
                String lable = rsmd.getColumnLabel(i); // 默认的列的标题
                String tableName = rsmd.getTableName(i); // 获取某列对应的表名
                boolean isAutoInctement = rsmd.isAutoIncrement(i); // 是否自动递增
                int isNullable = rsmd.isNullable(i); // 是否为空
                boolean isReadOnly = rsmd.isReadOnly(i); // 是否只读
                if (b) {
                    sb.append(name);
                    sb2.append("#").append(formatColumnName(name)).append("#");
                    b = false;
                } else {
                    sb.append(", ").append(name);
                    sb2.append(", ").append("#").append(formatColumnName(name)).append("#");
                }
            }
            System.out.println(sb.toString());
            System.out.println(sb2.toString());
            System.out.println("INSERT INTO " + tablename + " (" + sb.toString() + ") VALUES (" + sb2.toString() + ")");
        } finally {
            conn.close();
        }
    }

    /**
     * 获取表索引信息
     *
     * @param conn
     * @param tableName
     * @return
     * @throws SQLException
     */
    public static List<Map<String, String>> getIndexMetaData(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet rs = metaData.getIndexInfo(conn.getCatalog(), null, tableName, false, false);
        // printResultSet(rs);
        return processResultSet(rs, INDEX_NAME, INDEX_COLUMN_NAME, INDEX_NON_UNIQUE, INDEX_ORDINAL_POSITION);
    }

    /**
     * 获取所有表信息
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    public static List<Map<String, String>> getTablesMetaData(Connection conn) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet rs = metaData.getTables(conn.getCatalog(), null, "%", new String[]{"TABLE"});
        // printResultSet(rs);
        return processResultSet(rs, TABLE_NAME, TABLE_REMARKS);
    }

    /**
     * 获取表信息
     *
     * @param conn
     * @param tableName
     * @return
     * @throws SQLException
     */
    public static Map<String, String> getTableMetaData(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet rs = metaData.getTables(conn.getCatalog(), null, tableName, new String[]{"TABLE"});
        return processResultSet(rs, TABLE_NAME, TABLE_REMARKS).get(0);
    }

    /**
     * 获取表的所有列的信息
     *
     * @param conn
     * @param tableName
     * @return
     * @throws SQLException
     */
    public static List<Map<String, String>> getColumnsMetaData(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet rs = metaData.getColumns(conn.getCatalog(), null, tableName, null);
        // printResultSet(rs);
        return processResultSet(rs, COLUMN_NAME, COLUMN_DATA_TYPE, COLUMN_TYPE_NAME, COLUMN_SIZE, COLUMN_DECIMAL_DIGITS,
                COLUMN_DEF, COLUMN_REMARKS, COLUMN_IS_NULLABLE, COLUMN_IS_AUTOINCREMENT);
    }

    /**
     * 获取表的主键信息
     *
     * @param conn
     * @param tableName
     * @return
     * @throws SQLException
     */
    public static List<Map<String, String>> getPrimaryKeysMetaData(Connection conn, String tableName)
            throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet rs = metaData.getPrimaryKeys(conn.getCatalog(), null, tableName);
        // printResultSet(rs);
        return processResultSet(rs, TABLE_NAME, COLUMN_NAME);
    }

    /**
     * 打印查询结果
     *
     * @param rs
     * @param columnNames
     * @return
     * @throws SQLException
     */
    public static List<Map<String, String>> processResultSet(ResultSet rs, String... columnNames) throws SQLException {
        try {
            List<Map<String, String>> list = new ArrayList<>();
            while (rs.next()) {
                Map<String, String> data = new HashMap<>();
                for (String columnName : columnNames) {
                    data.put(columnName, rs.getString(columnName));
                }
                list.add(data);
            }
            return list;
        } finally {
            if (rs != null)
                rs.close();
        }
    }

    /**
     * 获取所有表名
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    public static String[] getTableNames(Connection conn) throws SQLException {
        List<Map<String, String>> tablesMetaData = getTablesMetaData(conn);
        String[] tableNames = new String[tablesMetaData.size()];
        for (int i = 0; i < tableNames.length; i++) {
            tableNames[i] = tablesMetaData.get(i).get(TABLE_NAME).toLowerCase();
        }
        return tableNames;
    }

    /**
     * 格式化表名称
     *
     * @param columnName
     * @return
     */
    public static String formatColumnName(String columnName) {
        columnName = columnName.toLowerCase();
        StringBuilder sb = new StringBuilder();
        int i;
        while ((i = columnName.indexOf("_")) != -1) {
            sb.append(columnName.substring(0, i));
            String sub = columnName.substring(i + 1);
            if (sub.length() == 0) {
                break;
            }
            columnName = sub.substring(0, 1).toUpperCase() + sub.substring(1);
        }
        if (columnName.length() > 0) {
            sb.append(columnName);
        }
        return sb.toString();
    }

    /**
     * 获取Connection
     *
     * @param driver
     * @param url
     * @param user
     * @param pwd
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection(String driver, String url, String user, String pwd)
            throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, user, pwd);
    }

    public static Connection getConnection(Config.Database database) throws SQLException, ClassNotFoundException {
        return getConnection(database.getDriver(), database.getUrl(), database.getUserName(), database.getPassWord());
    }

    /**
     * 通过表名产生insert语句
     *
     * @param conn
     * @param tableName
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static String generateInsertSql(Connection conn, String tableName) throws SQLException {
        return generateInsertSql(conn, tableName, false);
    }

    /**
     * 通过表名产生insert语句
     *
     * @param conn
     * @param tableName
     * @param isFill    是否填充（#字段名#）
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static String generateInsertSql(Connection conn, String tableName, boolean isFill) throws SQLException {
        List<Map<String, String>> maps = getColumnsMetaData(conn, tableName);
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        boolean b = true;
        for (Map<String, String> map : maps) {
            String name = map.get(COLUMN_NAME).toLowerCase();
            if (b) {
                b = false;
            } else {
                sb.append(", ");
                sb2.append(", ");
            }
            sb.append(name);
            if (isFill) {
                sb2.append("#").append(formatColumnName(name)).append("#");
            } else {
                sb2.append("?");
            }
        }
        return "INSERT INTO " + tableName + " (" + sb.toString() + ") VALUES (" + sb2.toString() + ")";
    }

    public static long getMax(Connection conn, String tableName, String fieldName) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select max(" + fieldName + ") from " + tableName);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } finally {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }
        return 0;
    }

    public static long getSeq(Connection conn, String tableName, String fieldName) throws SQLException {
        return getMax(conn, tableName, fieldName) + 1;
    }

    private static void printResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData1 = rs.getMetaData();
        int columnCount = metaData1.getColumnCount();
        while (rs.next()) {
            System.out.println("========================================");
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(i + " == " + metaData1.getColumnLabel(i) + " == " + rs.getString(i));
            }
        }
    }

    public static void executeDDL(Connection remoteConn, String sql) throws SQLException {
        Statement stmt = remoteConn.createStatement();
        // 执行SQL,返回boolean值表示是否包含ResultSet
        boolean hasResultSet = stmt.execute(sql);
        // 如果执行后有ResultSet结果集
        if (hasResultSet) {
            // 获取结果集
            ResultSet rs = stmt.getResultSet();
            // ResultSetMetaData是用于分析结果集的元数据接口
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            // 迭代输出ResultSet对象
            while (rs.next()) {
                // 依次输出每列的值
                for (int i = 0; i < columnCount; i++) {
                    System.out.print(rs.getString(i + 1) + "\t");
                }
                System.out.print("\n");
            }
        } else {
            System.out.println("该SQL语句影响的记录有" + stmt.getUpdateCount() + "条");
        }
    }

    public static void main(String[] args) {
        List<Map<String, String>> tablesMetaData = null;
        try {
            Connection conn = getConnection("org.postgresql.Driver", "jdbc:postgresql://192.168.8.140:5432/fuwu",
                    "fuwu", "kNkq3rFVej2Kgx9a");
            String tableName = "service.bsper_enterprises";
            // Map<String, String> tableMetaData = getTableMetaData(conn, tableName);
            // System.out.println(tableMetaData.toString());
            // tablesMetaData = getTablesMetaData(conn);
            // tablesMetaData = getColumnsMetaData(conn, tableName);
            // getPrimaryKeysMetaData(conn, tableName);

            // generateInsertSql(conn, tableName, false);
            // tablesMetaData = getColumnsMetaData(conn, tableName);
            tablesMetaData = getColumnsMetaData(conn, tableName);
            Map<String, String> tableMetaData = getTableMetaData(conn, tableName);
            System.out.println("===");
            // String[] tableNames = getTableNames(conn);
            // System.out.println(Arrays.toString(tableNames));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (tablesMetaData != null) {
            for (Map<String, String> map : tablesMetaData) {
                System.out.println(map);
            }
        }
    }

}
