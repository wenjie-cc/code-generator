package com.dm.generator.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

public class Utils {

    private static Map<Integer, String> javaTypeMap;

    private static Map<Integer, String> typeToName;

    private static Map<String, Integer> nameToType;

    static {
        javaTypeMap = new HashMap<>();
        javaTypeMap.put(Integer.valueOf(2003), Object.class.getName());
        javaTypeMap.put(Integer.valueOf(-5), Long.class.getName());
        javaTypeMap.put(Integer.valueOf(-2), "byte[]");
        javaTypeMap.put(Integer.valueOf(-7), Boolean.class.getName());
        javaTypeMap.put(Integer.valueOf(2004), "byte[]");
        javaTypeMap.put(Integer.valueOf(16), Boolean.class.getName());
        javaTypeMap.put(Integer.valueOf(1), String.class.getName());
        javaTypeMap.put(Integer.valueOf(2005), String.class.getName());
        javaTypeMap.put(Integer.valueOf(70), Object.class.getName());
        javaTypeMap.put(Integer.valueOf(91), Date.class.getName());
        javaTypeMap.put(Integer.valueOf(2001), Object.class.getName());
        javaTypeMap.put(Integer.valueOf(8), Double.class.getName());
        javaTypeMap.put(Integer.valueOf(6), Double.class.getName());
        javaTypeMap.put(Integer.valueOf(4), Integer.class.getName());
        javaTypeMap.put(Integer.valueOf(2000), Object.class.getName());
        javaTypeMap.put(Integer.valueOf(-4), "byte[]");
        javaTypeMap.put(Integer.valueOf(-1), String.class.getName());
        javaTypeMap.put(Integer.valueOf(0), Object.class.getName());
        javaTypeMap.put(Integer.valueOf(1111), Object.class.getName());
        javaTypeMap.put(Integer.valueOf(7), Float.class.getName());
        javaTypeMap.put(Integer.valueOf(2006), Object.class.getName());
        javaTypeMap.put(Integer.valueOf(5), Short.class.getName());
        javaTypeMap.put(Integer.valueOf(2002), Object.class.getName());
        javaTypeMap.put(Integer.valueOf(92), Date.class.getName());
        javaTypeMap.put(Integer.valueOf(93), Date.class.getName());
        javaTypeMap.put(Integer.valueOf(-6), Byte.class.getName());
        javaTypeMap.put(Integer.valueOf(-3), "byte[]");
        javaTypeMap.put(Integer.valueOf(12), String.class.getName());
        javaTypeMap.put(Integer.valueOf(3), Long.class.getName());
        javaTypeMap.put(Integer.valueOf(2), BigDecimal.class.getName());

        typeToName = new HashMap<Integer, String>();
        typeToName.put(Integer.valueOf(2003), "ARRAY");
        typeToName.put(Integer.valueOf(-5), "BIGINT");
        typeToName.put(Integer.valueOf(-2), "BINARY");
        typeToName.put(Integer.valueOf(-7), "BIT");
        typeToName.put(Integer.valueOf(2004), "BLOB");
        typeToName.put(Integer.valueOf(16), "BOOLEAN");
        typeToName.put(Integer.valueOf(1), "CHAR");
        typeToName.put(Integer.valueOf(2005), "CLOB");
        typeToName.put(Integer.valueOf(70), "DATALINK");
        typeToName.put(Integer.valueOf(91), "DATE");
        typeToName.put(Integer.valueOf(3), "NUMERIC");
        typeToName.put(Integer.valueOf(2001), "DISTINCT");
        typeToName.put(Integer.valueOf(8), "DOUBLE");
        typeToName.put(Integer.valueOf(6), "FLOAT");
        typeToName.put(Integer.valueOf(4), "INTEGER");
        typeToName.put(Integer.valueOf(2000), "JAVA_OBJECT");
        typeToName.put(Integer.valueOf(-4), "LONGVARBINARY");
        typeToName.put(Integer.valueOf(-1), "LONGVARCHAR");
        typeToName.put(Integer.valueOf(0), "NULL");
        typeToName.put(Integer.valueOf(2), "NUMERIC");
        typeToName.put(Integer.valueOf(1111), "OTHER");
        typeToName.put(Integer.valueOf(7), "REAL");
        typeToName.put(Integer.valueOf(2006), "REF");
        typeToName.put(Integer.valueOf(5), "SMALLINT");
        typeToName.put(Integer.valueOf(2002), "STRUCT");
        typeToName.put(Integer.valueOf(92), "TIME");
        typeToName.put(Integer.valueOf(93), "TIMESTAMP");
        typeToName.put(Integer.valueOf(-6), "TINYINT");
        typeToName.put(Integer.valueOf(-3), "VARBINARY");
        typeToName.put(Integer.valueOf(12), "VARCHAR");

        nameToType = new HashMap<String, Integer>();
        nameToType.put("ARRAY", Integer.valueOf(2003));
        nameToType.put("BIGINT", Integer.valueOf(-5));
        nameToType.put("BINARY", Integer.valueOf(-2));
        nameToType.put("BIT", Integer.valueOf(-7));
        nameToType.put("BLOB", Integer.valueOf(2004));
        nameToType.put("BOOLEAN", Integer.valueOf(16));
        nameToType.put("CHAR", Integer.valueOf(1));
        nameToType.put("CLOB", Integer.valueOf(2005));
        nameToType.put("DATALINK", Integer.valueOf(70));
        nameToType.put("DATE", Integer.valueOf(91));
        nameToType.put("DECIMAL", Integer.valueOf(3));
        nameToType.put("DISTINCT", Integer.valueOf(2001));
        nameToType.put("DOUBLE", Integer.valueOf(8));
        nameToType.put("FLOAT", Integer.valueOf(6));
        nameToType.put("INTEGER", Integer.valueOf(4));
        nameToType.put("JAVA_OBJECT", Integer.valueOf(2000));
        nameToType.put("LONGVARBINARY", Integer.valueOf(-4));
        nameToType.put("LONGVARCHAR", Integer.valueOf(-1));
        nameToType.put("NULL", Integer.valueOf(0));
        nameToType.put("NUMERIC", Integer.valueOf(2));
        nameToType.put("OTHER", Integer.valueOf(1111));
        nameToType.put("REAL", Integer.valueOf(7));
        nameToType.put("REF", Integer.valueOf(2006));
        nameToType.put("SMALLINT", Integer.valueOf(5));
        nameToType.put("STRUCT", Integer.valueOf(2002));
        nameToType.put("TIME", Integer.valueOf(92));
        nameToType.put("TIMESTAMP", Integer.valueOf(93));
        nameToType.put("TINYINT", Integer.valueOf(-6));
        nameToType.put("VARBINARY", Integer.valueOf(-3));
        nameToType.put("VARCHAR", Integer.valueOf(12));
    }

    public static String getJavaType(int jdbcType) {
        return javaTypeMap.get(jdbcType);
    }

    public static String formatAttributeName(String fieldName) {
        return formatName(fieldName, true);
    }

    /**
     * 格式化字段
     *
     * @param fieldName   字段名称
     * @param isAttribute true:首字母小写；false:首字母大写
     * @return 格式后的字段
     */
    public static String formatName(String fieldName, boolean isAttribute) {
        StringBuilder calssName = new StringBuilder();
        fieldName = fieldName.toLowerCase();
        if (fieldName.contains("_")) {
            String[] strs = fieldName.split("_");
            for (int i = 0; i < strs.length; i++) {
                String s = strs[i];
                if (isAttribute && i == 0) {
                    calssName.append(s);
                } else {
                    calssName.append(firstLetterCapitalized(s));
                }
            }
        } else {
            if (isAttribute) {
                calssName.append(fieldName);
            } else {
                calssName.append(firstLetterCapitalized(fieldName));
            }
        }
        return calssName.toString();
    }

    public static String firstLetterCapitalized(String str) {
        if (!"".equals(str.trim())) {
            if (str.length() > 1) {
                return firstLetter(str) + str.substring(1);
            }
            return str.toUpperCase();
        }
        return "";
    }

    public static String firstLetter(String str) {
        if (!"".equals(str.trim())) {
            return str.substring(0, 1).toUpperCase();
        }
        return "";
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String firstLetterUpper(String str) {
        if (str != null && !"".equals(str) && str.length() > 0) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return str;
    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String firstLetterLower(String str) {
        if (str != null && !"".equals(str) && str.length() > 0) {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        }
        return str;
    }

    public static List<String> tokenizeToStringArray(String str) {
        return tokenizeToStringArray(str, ",; \t\n");
    }

    public static List<String> tokenizeToStringArray(String str, String delimiters) {
        if (str == null) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(str, delimiters);
        List<String> tokens = new ArrayList<String>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            token = token.trim();
            if (token.length() > 0) {
                tokens.add(token);
            }
        }
        return tokens;
    }

    public static String processObj(String str, Object obj) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            int indexStart = str.indexOf("${");
            if (indexStart != -1) {
                int indexEnd = str.indexOf("}", indexStart);
                if (indexEnd != -1) {
                    sb.append(str.substring(0, indexStart));
                    // 添加属性
                    String field = str.substring(indexStart + 2, indexEnd);
                    Object value = getValue(obj, field);
                    if (value != null) {
                        sb.append(value);
                    }
                    str = str.substring(indexEnd + 1);
                }
            } else {
                sb.append(str);
                break;
            }
        }
        return sb.toString();
    }

    public static Object getValue(Object obj, String field) {
        if (obj instanceof Map) {
            return ((Map) obj).get(field);
        } else {
            return getJavaBeanValue(obj, field);
        }
    }

    public static Object getJavaBeanValue(Object obj, String field) {
        Object reObj = null;
        Class<?> aClass = obj.getClass();
        String methodName = "get" + field.substring(0, 1).toUpperCase(Locale.ENGLISH) + field.substring(1);
        try {
            Method method = aClass.getMethod(methodName);
            reObj = method.invoke(obj);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return reObj;
    }

    public static String get(Map<String, Object> map, String key) {
        if (map != null && map.get(key) != null) {
            return map.get(key).toString();
        }
        return null;
    }

    public static String toDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(new Date());
    }

    public static String className(String tableName, String prefix) {
        return prefix != null ? tableName.replace(prefix, "") : tableName;
    }

    public static String camelConvert(String str, String symbol) {
        return camelConvert(str, symbol, true);
    }

    public static String camelConvert(String str, String symbol, boolean firstLower) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        if (firstLower && Character.isUpperCase(chars[0])) {
            sb.append(Character.toLowerCase(chars[0]));
        }
        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isUpperCase(c)) {
                sb.append(symbol);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
