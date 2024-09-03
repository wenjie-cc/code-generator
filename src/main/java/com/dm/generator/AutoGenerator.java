package com.dm.generator;

import com.dm.generator.generate.TemplateGenerator;

/**
 * 自动生产代码工具类
 *
 * @author: wenjie
 * @date: 2021/3/27 15:56
 */
public class AutoGenerator {

    public void generate(Config config) {
        try {
            TemplateGenerator generator = new TemplateGenerator(config);
            generator.process();
        } catch (Exception e) {
            throw new RuntimeException("code generator error!", e);
        }
    }

}
