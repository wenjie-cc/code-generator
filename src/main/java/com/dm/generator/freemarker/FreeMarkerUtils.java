package com.dm.generator.freemarker;

import com.dm.generator.Config;
import com.dm.generator.handle.RunLogListener;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author: wenjie
 */
public class FreeMarkerUtils {

    public static RunLogListener runLogListener;

    private static Configuration cfg;

    public static void init(Config config) throws IOException {
        if (cfg != null) {
            cfg = null;
        }
        cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
        cfg.setOutputEncoding(StandardCharsets.UTF_8.name());
        // 指定模板目录
        cfg.setClassForTemplateLoading(FreeMarkerUtils.class, config.getBasePackagePath());
    }

    public static void outTemplateFile(String templateName, Map<String, Object> data, File outFile) {
        outTemplateFile(getTemplate(templateName), data, outFile);
    }

    public static void outTemplateFile(Template template, Map<String, Object> data, File outFile) {
        if (template == null) {
            return;
        }
        /* 将模板和数据模型合并 */
        Writer out = null;
        try {
            File dir = outFile.getParentFile();
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    throw new RuntimeException("create dir failed! dir=" + dir.getAbsolutePath());
                }
            }
            out = new FileWriter(outFile);
            template.process(data, out);
            String msg = outFile.getPath();
            out.flush();
            if (runLogListener != null) {
                runLogListener.out(msg);
            }
            System.out.println("out file ==> " + msg);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    public static Template getTemplate(String templateName) {
        Template template = null;
        try {
            template = cfg.getTemplate(templateName, StandardCharsets.UTF_8.name());
            if (template == null) {
                System.out.println("template not exist! ==> " + templateName);
            }
        } catch (TemplateNotFoundException e) {
            System.out.println("template exception exist! ==> " + templateName);
        } catch (Exception e) {
            throw new RuntimeException("get template error!", e);
        }
        return template;
    }

}
