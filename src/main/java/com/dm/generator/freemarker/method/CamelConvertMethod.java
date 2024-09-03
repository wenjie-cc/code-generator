package com.dm.generator.freemarker.method;

import com.dm.generator.utils.Utils;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

/**
 * @author wenjie
 * @date 2022/12/1
 */
public class CamelConvertMethod implements TemplateMethodModelEx {
    @Override
    public Object exec(List list) throws TemplateModelException {
        final int num = list.size();
        if (num == 1) {
            return Utils.camelConvert(list.get(0).toString(), "_");
        } else if (num > 1) {
            return Utils.camelConvert(list.get(0).toString(), list.get(1).toString());
        }
        return null;
    }
}
