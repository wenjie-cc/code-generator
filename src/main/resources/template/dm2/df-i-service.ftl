package ${packageName}<#if moduleName??>.${moduleName}</#if>.service;

import com.dm.common.base.response.PageInfo;
import com.dm.common.base.response.Param;
import com.dm.common.mybatis.data.service.BaseService;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.domain.${tableInfo.tableClassName}Entity;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.rsp.${tableInfo.tableClassName}ResultDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}CreateDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}UpdateDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}QueryDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}DeleteDTO;

import javax.validation.Valid;
import java.util.List;

/**
 * ${tableInfo.tableRemarks?if_exists}服务层
 *
 * @author: ${author}
 * @date: ${date}
 */
public interface ${tableInfo.tableClassName}Service extends BaseService<${tableInfo.tableClassName}Entity> {
    /** 分页查询 ${tableInfo.tableRemarks?if_exists}数据 */
    PageInfo<${tableInfo.tableClassName}ResultDTO> page(Param pageable, ${tableInfo.tableClassName}QueryDTO query);

    /** 查询 ${tableInfo.tableRemarks?if_exists}数据 */
    List<${tableInfo.tableClassName}ResultDTO> find(${tableInfo.tableClassName}QueryDTO query);

    /** 根据id查询 ${tableInfo.tableRemarks?if_exists}数据 */
    ${tableInfo.tableClassName}ResultDTO findById(Long id);

    /** 新增 ${tableInfo.tableRemarks?if_exists}数据 */
    Long insert(@Valid ${tableInfo.tableClassName}CreateDTO dto);

    /** 根据id修改 ${tableInfo.tableRemarks?if_exists}数据 */
    Boolean updateById(@Valid ${tableInfo.tableClassName}UpdateDTO dto);

    /** 根据id删除 ${tableInfo.tableRemarks?if_exists}数据 */
    Boolean deleteById(Long id);

    /** 根据id删除 ${tableInfo.tableRemarks?if_exists}数据 */
    Boolean deleteById(${tableInfo.tableClassName}DeleteDTO dto);
}