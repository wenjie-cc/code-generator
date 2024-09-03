package ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req;

import java.io.Serializable;
import lombok.Data;
import com.dm.common.base.annotations.ApiQueryParam;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ${tableInfo.tableRemarks?if_exists}-请求对象-查询
 *
 * @author: ${author}
 * @date: ${date}
 */
@Data
@ApiQueryParam
public class ${tableInfo.tableClassName}QueryDTO implements Serializable {
    /**
    * ID
    */
    private Long id;

}
