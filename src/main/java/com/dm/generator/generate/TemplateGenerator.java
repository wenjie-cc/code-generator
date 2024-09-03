package com.dm.generator.generate;

import com.dm.generator.Config;
import com.dm.generator.freemarker.FreeMarkerUtils;
import com.dm.generator.freemarker.method.CamelConvertMethod;
import com.dm.generator.handle.TableHandle;
import com.dm.generator.model.TableInfo;
import com.dm.generator.utils.MetaDataUtils;
import com.dm.generator.utils.Utils;
import freemarker.template.Template;
import org.apache.commons.collections4.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 模板生成器
 *
 * @author: wenjie
 */
public class TemplateGenerator {

    private Config config;

    private TableHandle handle;

    private Config.Table table;

    public TemplateGenerator(Config config) throws IOException {
        this.config = config;
        this.table = config.getTable();
        TableHandle.TableHandleFactory tableHandleFactory = new TableHandle.TableHandleFactory();
        this.handle = tableHandleFactory.create(config.getDatabase());
        FreeMarkerUtils.init(config);
    }

    public void process() throws SQLException, ClassNotFoundException {
        String prefix = Optional.ofNullable(this.table.getPrefix()).orElse(null);
        String packageName = config.getPackageName();
        List<String> tableNames = Optional.ofNullable(this.table.getTables()).orElse(Collections.emptyList());
        try (Connection conn = MetaDataUtils.getConnection(config.getDatabase())) {
            if (tableNames.isEmpty()) {
                tableNames = handle.getAllTableInfo(conn);
                if (CollectionUtils.isEmpty(tableNames)) {
                    System.out.println("没有找到任何可以生成的表");
                    return;
                }
            }
            for (String tableName : tableNames) {
                // 获取表信息
                TableInfo tableInfo = handle.getTableInfo(config.getDatabase().getOwner(), tableName, conn, prefix);
                tableInfo.setAppName(config.getAppName());
                tableInfo.setFeignServerName(config.getFeignServerName());
                Map<String, Object> data = new HashMap<>(20);
                data.put("basePackageName", config.getBasePackageName());
                data.put("packageName", packageName);
                data.put("AppName", packageName);
                data.put("moduleName", config.getModuleName());
                data.put("prefix", prefix);
                data.put("author", config.getAuthor());
                data.put("date", config.getDate());
                data.put("appName", config.getAppName());
                data.put("camelConvert", new CamelConvertMethod());
                data.put("tableInfo", tableInfo);
                System.out.println("generator table template ==> " + tableName);
                // 输出参数
                outTemplateFiles(data);
            }
        }
    }

    private void outTemplateFiles(Map<String, Object> data) {
        for (GenerateType generateType : GenerateType.values()) {
            try {
                outTemplateFile(generateType, data);
            } catch (Exception e) {
                System.out.println("处理错误 generateType：" + generateType.getTempFile());
                throw e;
            }
        }
    }

    private void outTemplateFile(GenerateType generateType, Map<String, Object> data) {
        Template template = FreeMarkerUtils.getTemplate(generateType.getTempFile());
        if (template == null) {
            return;
        }
        TableInfo tableInfo = (TableInfo) data.get("tableInfo");
        Optional.ofNullable(data.get("moduleName")).ifPresent(v -> tableInfo.setModuleName(v.toString()));
        File dir = new File(
                config.getOutPath() + File.separator + Utils.processObj(generateType.getModelType(), tableInfo));
        File outFile = new File(dir, Utils.processObj(generateType.getSuffix(), tableInfo));

        // 输出
        FreeMarkerUtils.outTemplateFile(template, data, outFile);
    }

}
