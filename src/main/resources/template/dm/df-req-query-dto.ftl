package ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req;

import java.io.Serializable;
import lombok.Data;
import com.dm.common.base.response.Param;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * ${tableInfo.tableRemarks?if_exists}-请求对象-查询
 *
 * @author: ${author}
 * @date: ${date}
 */
@Data
public class ${tableInfo.tableClassName}QueryDTO extends Param {
    /** ID */
    @Parameter(description = "id", example = "100001")
    private Long id;
}
