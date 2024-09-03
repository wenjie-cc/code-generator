package ${packageName}<#if moduleName??>.${moduleName}</#if>.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import com.dm.common.mybatis.data.entity.BaseEntity;

<#list tableInfo.importClasss as ic >
    ${ic}
</#list>

/**
 * ${tableInfo.tableRemarks?if_exists}实体层
 *
 * @author: ${author}
 * @date: ${date}
 */
@Setter
@Getter
@TableName("${tableInfo.tableName}")
public class ${tableInfo.tableClassName}Entity extends BaseEntity {
<#list tableInfo.rows as row >
    <#if row.columnName == "id">
    <#elseif row.columnName == "create_by">
    <#elseif row.columnName == "create_time">
    <#elseif row.columnName == "update_by">
    <#elseif row.columnName == "update_time">
    <#elseif row.columnName == "delete_time">
    <#elseif row.columnName == "is_delete">
    <#else >
    /** ${row.columnRemarks?if_exists} */
    @TableField(value = "${row.columnName}")
    private ${row.javaType} ${row.javaName};
    </#if>
</#list>
}
