package ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req;

import java.io.Serializable;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
<#list tableInfo.importClasss as ic >
${ic}
</#list>

/**
 * ${tableInfo.tableRemarks?if_exists}-请求对象-删除
 *
 * @author: ${author}
 * @date: ${date}
 */
@Data
@Schema(description = "${tableInfo.tableRemarks?if_exists}-请求对象-删除")
public class ${tableInfo.tableClassName}DeleteDTO implements Serializable {
    /** ID */
    @Schema(description = "id", example = "100001")
    private Long id;

}
