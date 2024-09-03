package ${packageName}<#if moduleName??>.${moduleName}</#if>.controller.app;

import ${packageName}<#if moduleName??>.${moduleName}</#if>.service.${tableInfo.tableClassName}Service;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.rsp.${tableInfo.tableClassName}ResultDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}QueryDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}CreateDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}UpdateDTO;
import cn.dev33.satoken.annotation.SaCheckLogin;
import com.dm.common.base.current.Current;
import com.dm.common.base.user.Account;
import com.dm.common.base.response.Param;
import com.dm.common.base.response.PageInfo;
import com.dm.common.base.response.R;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

/**
 * ${tableInfo.tableRemarks?if_exists}APP控制层
 *
 * @author: ${author}
 * @date: ${date}
 */
@Tag(name = "${camelConvert(tableInfo.tableClassName, "-")}-App", description = "${tableInfo.tableRemarks?if_exists}App接口")
@RestController
@RequestMapping("/app/${camelConvert(tableInfo.tableClassName, "-")}")
@RequiredArgsConstructor
public class ${tableInfo.tableClassName}AppController {
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

    /**
     * 新增${tableInfo.tableRemarks?if_exists}
     */
    @SaCheckLogin
    @PostMapping
    public R<Long> insert(@RequestBody ${tableInfo.tableClassName}CreateDTO dto) {
        return R.data(${tableInfo.tableClassName?uncap_first}Service.insert(dto));
    }

    /**
     * 修改${tableInfo.tableRemarks?if_exists}
     */
    @SaCheckLogin
    @PutMapping("/{id}")
    public R<Boolean> update(@PathVariable("id") Long id, @RequestBody ${tableInfo.tableClassName}UpdateDTO dto) {
        dto.setId(id);
        return R.data(${tableInfo.tableClassName?uncap_first}Service.updateById(dto));
    }

    /**
     * 删除${tableInfo.tableRemarks?if_exists}
     */
    @SaCheckLogin
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable("id") Long id) {
        return R.data(${tableInfo.tableClassName?uncap_first}Service.deleteById(id));
    }

}
