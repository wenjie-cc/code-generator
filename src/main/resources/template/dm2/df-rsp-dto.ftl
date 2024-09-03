package ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.rsp;

import java.io.Serializable;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
<#list tableInfo.importClasss as ic >
${ic}
</#list>

/**
 * ${tableInfo.tableRemarks?if_exists}-响应对象
 *
 * @author: ${author}
 * @date: ${date}
 */
@Data
public class ${tableInfo.tableClassName}ResultDTO implements Serializable {
<#list tableInfo.rows as row >
    <#if row.columnName == "delete_time">
    <#elseif row.columnName == "update_by">
    <#elseif row.columnName == "update_time">
    <#elseif row.columnName == "is_delete">
    <#else>
    /** ${row.columnRemarks?if_exists} */
    private ${row.javaType} ${row.javaName};
    </#if>
</#list>

}