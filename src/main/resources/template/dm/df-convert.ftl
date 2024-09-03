package ${packageName}.service<#if moduleName??>.${moduleName}</#if>.convert;

import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.rsp.${tableInfo.tableClassName}ResultDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}CreateDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}UpdateDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.domain.${tableInfo.tableClassName}Entity;
import com.dm.common.mapstruct.CommonConvertConfig;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * ${tableInfo.tableRemarks?if_exists}转换
 *
 * @author: ${author}
 * @date: ${date}
 */
@Mapper(config = CommonConvertConfig.class)
public abstract class ${tableInfo.tableClassName}Convert {
    public abstract ${tableInfo.tableClassName}Entity create(${tableInfo.tableClassName}CreateDTO s);

    public abstract ${tableInfo.tableClassName}Entity update(${tableInfo.tableClassName}UpdateDTO s, @MappingTarget ${tableInfo.tableClassName}Entity t);

    public abstract ${tableInfo.tableClassName}Entity update(${tableInfo.tableClassName}UpdateDTO s);

    public abstract ${tableInfo.tableClassName}ResultDTO convert(${tableInfo.tableClassName}Entity s);
}
