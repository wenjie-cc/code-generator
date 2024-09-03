package ${packageName}<#if moduleName??>.${moduleName}</#if>.controller.inner;

import ${packageName}<#if moduleName??>.${moduleName}</#if>.service.${tableInfo.tableClassName}Service;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.rsp.${tableInfo.tableClassName}ResultDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}QueryDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}CreateDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}UpdateDTO;
import com.dm.common.base.current.Current;
import com.dm.common.base.user.Account;
import com.dm.common.base.response.Param;
import com.dm.common.base.response.PageInfo;
import com.dm.common.base.response.R;
io.swagger.v3.oas.annotations.Hidden
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

/**
 * ${tableInfo.tableRemarks?if_exists}内部控制层
 *
 * @author: ${author}
 * @date: ${date}
 */
@Hidden
@RestController
@RequestMapping("/inner/${camelConvert(tableInfo.tableClassName, "-")}")
@RequiredArgsConstructor
public class ${tableInfo.tableClassName}InnerController {
    private final ${tableInfo.tableClassName}Service ${tableInfo.tableClassName?uncap_first}Service;

    /**
     * ${tableInfo.tableRemarks?if_exists}-分页
     */
    @GetMapping("/page")
    public R<PageInfo<${tableInfo.tableClassName}ResultDTO>> page(Param pageable, ${tableInfo.tableClassName}QueryDTO dto) {
        PageInfo<${tableInfo.tableClassName}ResultDTO> page = ${tableInfo.tableClassName?uncap_first}Service.page(pageable, dto);
        return R.data(page);
    }

    /**
     * ${tableInfo.tableRemarks?if_exists}-详情
     */
    @GetMapping("/{id}")
    public R<${tableInfo.tableClassName}ResultDTO> findById(@PathVariable("id") Long id) {
        ${tableInfo.tableClassName}ResultDTO dto = ${tableInfo.tableClassName?uncap_first}Service.findById(id);
        return R.data(dto);
    }
}