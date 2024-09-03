package com.dm.generator;


import com.dm.generator.generate.TemplateGenerator;
import com.dm.generator.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wenjie
 * @date 2023/1/11
 */
public class AutoDm8GeneratorTest {
    private static final String DRIVER = "dm.jdbc.driver.DmDriver";
    private static final String URL = "jdbc:dm://192.168.2.6:5236/DEV_ZHJZ?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";
    private static final String OWNER = "DEV_ZHJZ";
    private static final String USERNAME = "DEV_ZHJZ";
    private static final String PASSWORD = "DEV_ZHJZ";

    public static void main(String[] args) {
        Config config = new Config();
        // 设置 需要生成的表名
        List<String> tableNames = new ArrayList<>();
        tableNames.add("SYS_DEPART");

        // 表前缀
        String tablePrefix = "sys";
        // 基础包名称
        String basePackage = "org.tdf.supervision";
        // 应用
        String appName = "supervision";
        // 子模块
        String moduleName = "";

        String feignServerName = "SYSTEM";
        String packageName = basePackage + "." + appName;
        Config.Table table = new Config.Table();
        // 设置 表名前缀
        table.setPrefix(tablePrefix);
        table.setTables(tableNames);
        config.setTable(table);

        Config.Database database = new Config.Database();
        // 设置 数据库设置
        database.setDriver(DRIVER);
        database.setUrl(URL);
        database.setUserName(USERNAME);
        database.setPassWord(PASSWORD);
        database.setOwner(OWNER);
        config.setDatabase(database);

        // 设置 基础设置
        config.setPackageName(packageName);
        config.setModuleName(moduleName);
        config.setAppName(appName);
        config.setFeignServerName(feignServerName);
        config.setAuthor("wenjie");
        config.setDate(Utils.toDay());
        config.setOutPath("./out");

        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.generate(config);
    }

    public void generate(Config config) {
        try {
            TemplateGenerator generator = new TemplateGenerator(config);
            generator.process();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}