package com.dm.generator.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TypeUtils {

    private static Map<String, String> mysql6Type;

    private static Map<String, String> pgType;

    private static Map<String, String> dm8Type;

    private static Map<String, String> importClass;

    static {
        mysql6Type = new HashMap<>();
        mysql6Type.put("ARRAY", Object.class.getSimpleName());
        mysql6Type.put("BIGINT", Long.class.getSimpleName());
        mysql6Type.put("BINARY", "byte[]");
        mysql6Type.put("BIT", Integer.class.getSimpleName());
        mysql6Type.put("BLOB", "byte[]");
        mysql6Type.put("BOOLEAN", Boolean.class.getSimpleName());
        mysql6Type.put("CHAR", String.class.getSimpleName());
        mysql6Type.put("CLOB", String.class.getSimpleName());
        mysql6Type.put("DATALINK", Object.class.getSimpleName());
        mysql6Type.put("DECIMAL", BigDecimal.class.getSimpleName());
        mysql6Type.put("DISTINCT", Object.class.getSimpleName());
        mysql6Type.put("DOUBLE", BigDecimal.class.getSimpleName());
        mysql6Type.put("FLOAT", BigDecimal.class.getSimpleName());
        mysql6Type.put("FLOAT UNSIGNED", BigDecimal.class.getSimpleName());
        mysql6Type.put("INTEGER", Integer.class.getSimpleName());
        mysql6Type.put("JAVA_OBJECT", Object.class.getSimpleName());
        mysql6Type.put("LONGVARBINARY", "byte[]");
        mysql6Type.put("LONGVARCHAR", String.class.getSimpleName());
        mysql6Type.put("NULL", Object.class.getSimpleName());
        mysql6Type.put("NUMERIC", BigDecimal.class.getSimpleName());
        mysql6Type.put("OTHER", Object.class.getSimpleName());
        mysql6Type.put("REAL", Float.class.getSimpleName());
        mysql6Type.put("REF", Object.class.getSimpleName());
        mysql6Type.put("SMALLINT", Short.class.getSimpleName());
        mysql6Type.put("STRUCT", Object.class.getSimpleName());
        mysql6Type.put("DATE", LocalDate.class.getSimpleName());
        mysql6Type.put("TIME", LocalTime.class.getSimpleName());
        mysql6Type.put("DATETIME", LocalDateTime.class.getSimpleName());
        mysql6Type.put("TIMESTAMP", LocalDateTime.class.getSimpleName());
        mysql6Type.put("TINYINT", Integer.class.getSimpleName());
        mysql6Type.put("INT", Integer.class.getSimpleName());
        mysql6Type.put("VARBINARY", "byte[]");
        mysql6Type.put("VARCHAR", String.class.getSimpleName());

        mysql6Type.put("TINYINT UNSIGNED", Integer.class.getSimpleName());
        mysql6Type.put("MEDIUMINT UNSIGNED", Long.class.getSimpleName());
        mysql6Type.put("INT UNSIGNED", Integer.class.getSimpleName());
        mysql6Type.put("BIGINT UNSIGNED", Long.class.getSimpleName());
        mysql6Type.put("JSON", String.class.getSimpleName());
        mysql6Type.put("TEXT", String.class.getSimpleName());
        mysql6Type.put("TINYTEXT", String.class.getSimpleName());
        mysql6Type.put("LONGTEXT", String.class.getSimpleName());

        pgType = new HashMap<>();
        pgType.put("int8", "Long");
        pgType.put("bigint", "Long");
        pgType.put("_int8", "long[]");
        pgType.put("numeric", "BigDecimal");
        pgType.put("decimal", "BigDecimal");
        pgType.put("jsonb", "String");
        pgType.put("text", "String");
        pgType.put("tinyint", "Integer");
        pgType.put("int4", "Integer");
        pgType.put("int2", "Integer");
        pgType.put("int", "Integer");
        pgType.put("timestamp", "Date");
        pgType.put("datetime", "Date");
        pgType.put("date", "Date");
        pgType.put("time", "String");
        pgType.put("bool", "Boolean");
        pgType.put("varchar", "String");
        pgType.put("tinytext", "String");
        pgType.put("mediumtext", "String");
        pgType.put("point", "com.vividsolutions.jts.geom.Point");

        importClass = new HashMap<>();
        importClass.put("BigDecimal", "import java.math.BigDecimal;");
        importClass.put("LocalDateTime", "import java.time.LocalDateTime;");
        importClass.put("LocalDate", "import java.time.LocalDate;");
        importClass.put("LocalTime", "import java.time.LocalTime;");
        importClass.put("Date", "import java.util.Date;");

        dm8Type = new HashMap<>();
        dm8Type.put("ARRAY", Object.class.getSimpleName());
        dm8Type.put("BIGINT", Long.class.getSimpleName());
        dm8Type.put("BINARY", "byte[]");
        dm8Type.put("BIT", Integer.class.getSimpleName());
        dm8Type.put("BLOB", "byte[]");
        dm8Type.put("BOOLEAN", Boolean.class.getSimpleName());
        dm8Type.put("CHAR", String.class.getSimpleName());
        dm8Type.put("CLOB", String.class.getSimpleName());
        dm8Type.put("DATALINK", Object.class.getSimpleName());
        dm8Type.put("DECIMAL", BigDecimal.class.getSimpleName());
        dm8Type.put("DISTINCT", Object.class.getSimpleName());
        dm8Type.put("NUMBER", BigDecimal.class.getSimpleName());
        dm8Type.put("DOUBLE", BigDecimal.class.getSimpleName());
        dm8Type.put("FLOAT", BigDecimal.class.getSimpleName());
        dm8Type.put("FLOAT UNSIGNED", BigDecimal.class.getSimpleName());
        dm8Type.put("INTEGER", Integer.class.getSimpleName());
        dm8Type.put("JAVA_OBJECT", Object.class.getSimpleName());
        dm8Type.put("LONGVARBINARY", "byte[]");
        dm8Type.put("LONGVARCHAR", String.class.getSimpleName());
        dm8Type.put("NULL", Object.class.getSimpleName());
        dm8Type.put("NUMERIC", BigDecimal.class.getSimpleName());
        dm8Type.put("OTHER", Object.class.getSimpleName());
        dm8Type.put("REAL", Float.class.getSimpleName());
        dm8Type.put("REF", Object.class.getSimpleName());
        dm8Type.put("SMALLINT", Short.class.getSimpleName());
        dm8Type.put("STRUCT", Object.class.getSimpleName());
        dm8Type.put("DATE", LocalDate.class.getSimpleName());
        dm8Type.put("TIME", LocalTime.class.getSimpleName());
        dm8Type.put("DATETIME", LocalDateTime.class.getSimpleName());
        dm8Type.put("TIMESTAMP", LocalDateTime.class.getSimpleName());
        dm8Type.put("TINYINT", Integer.class.getSimpleName());
        dm8Type.put("INT", Integer.class.getSimpleName());
        dm8Type.put("VARBINARY", "byte[]");
        dm8Type.put("VARCHAR", String.class.getSimpleName());
        dm8Type.put("VARCHAR2", String.class.getSimpleName());
        dm8Type.put("NVARCHAR", String.class.getSimpleName());
        dm8Type.put("NVARCHAR2", String.class.getSimpleName());

        dm8Type.put("TINYINT UNSIGNED", Integer.class.getSimpleName());
        dm8Type.put("MEDIUMINT UNSIGNED", Long.class.getSimpleName());
        dm8Type.put("INT UNSIGNED", Integer.class.getSimpleName());
        dm8Type.put("BIGINT UNSIGNED", Long.class.getSimpleName());
        dm8Type.put("JSON", String.class.getSimpleName());
        dm8Type.put("TEXT", String.class.getSimpleName());
        dm8Type.put("TINYTEXT", String.class.getSimpleName());
        dm8Type.put("LONGTEXT", String.class.getSimpleName());
    }

    public static String getJavaType(String jdbcType) {
        return mysql6Type.get(jdbcType);
    }

    public static String pgType2JavaType(String jdbcType) {
        String s = pgType.get(jdbcType);
        Objects.requireNonNull(s);
        return s;
    }

    public static String dmTypeJavaType(String jdbcType) {
        String s = dm8Type.get(jdbcType);
        if (StringUtils.isEmpty(s)) {
            System.out.println("jdbcType=>" + jdbcType);
        }
        Objects.requireNonNull(s);
        return s;
    }

    public static String getImportClass(String javaType) {
        return importClass.get(javaType);
    }

}
