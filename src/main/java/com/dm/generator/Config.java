package com.dm.generator;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 基础配置
 *
 * @author: wenjie
 */
@Data
public class Config {

    /**
     * 基础包名
     */
    private String basePackageName;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 服务名（feign）
     */
    private String feignServerName;

    /**
     * 应用名（服务）
     */
    private String appName;

    /**
     * 输出目录
     */
    private String outPath = "./out";

    /**
     * 作者
     */
    private String author;

    /**
     * 模板扫描路劲
     */
    private String basePackagePath = "/template/dm";

    /**
     * 创作日期
     */
    private String date;

    /**
     * 数据库配置
     */
    private Database database;

    /**
     * 生成表配置
     */
    private Table table;

    @Data
    public static class Database {

        /**
         * JDBC 驱动
         */
        private String driver;

        /**
         * JDBC URL
         */
        private String url;

        /**
         * 拥有者-命名空间
         */
        private String owner;

        /**
         * JDBC 用户
         */
        private String userName;

        /**
         * JDBC 密码
         */
        private String passWord;

        /**
         * 是否 mysql8
         */
        private Boolean isMysql8 = false;

    }

    @Data
    public static class Table {

        /**
         * 表前缀
         */
        private String prefix;

        /**
         * 表列表，需要生成那些表
         */
        private List<String> tables;
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println("1666092271786");
        System.out.println(new Date(1666092271786L));
    }

}
