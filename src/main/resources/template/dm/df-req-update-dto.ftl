package ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req;

import java.io.Serializable;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
<#list tableInfo.importClasss as ic >
${ic}
</#list>

/**
 * ${tableInfo.tableRemarks?if_exists}-请求对象-修改
 *
 * @author: ${author}
 * @date: ${date}
 */
@Data
@Schema(description = "${tableInfo.tableRemarks?if_exists}-请求对象-修改")
public class ${tableInfo.tableClassName}UpdateDTO implements Serializable {
<#list tableInfo.rows as row >
    <#if row.columnName == "delete_time">
    <#elseif row.columnName == "create_by">
    <#elseif row.columnName == "create_time">
    <#elseif row.columnName == "update_by">
    <#elseif row.columnName == "update_time">
    <#elseif row.columnName == "is_delete">
    <#else>
    /** ${row.columnRemarks?if_exists} */
    @Schema(description = "${row.columnRemarks?if_exists}", example = "")
    private ${row.javaType} ${row.javaName};
    </#if>
</#list>
}
