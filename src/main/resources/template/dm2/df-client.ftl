package ${packageName}<#if moduleName??>.${moduleName}</#if>.client;

import ${packageName}<#if moduleName??>.${moduleName}</#if>.factory.${tableInfo.tableClassName}FallbackFactory;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.rsp.${tableInfo.tableClassName}ResultDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}QueryDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}CreateDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}UpdateDTO;
import com.dm.common.base.constants.ServiceNameConstants;
org.springframework.cloud.openfeign.SpringQueryMap
import com.dm.common.base.response.PageInfo;
import com.dm.common.base.response.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ${tableInfo.tableRemarks?if_exists}客户端
 *
 * @author: ${author}
 * @date: ${date}
 */
@FeignClient(value = ServiceNameConstants.${tableInfo.feignServerName}, fallbackFactory = ${tableInfo.tableClassName}FallbackFactory.class)
public interface ${tableInfo.tableClassName}Client {

    /** ${tableInfo.tableRemarks?if_exists}-分页 */
    @GetMapping("/inner/${camelConvert(tableInfo.tableClassName, "-")}/page")
    R<PageInfo<${tableInfo.tableClassName}ResultDTO>> page(@SpringQueryMap ${tableInfo.tableClassName}QueryDTO dto);

    /** ${tableInfo.tableRemarks?if_exists}-详情 */
    @GetMapping("/inner/${camelConvert(tableInfo.tableClassName, "-")}/{id}")
    R<${tableInfo.tableClassName}ResultDTO> findById(@PathVariable("id") Long id);

}
