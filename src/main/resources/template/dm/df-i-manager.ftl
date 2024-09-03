package ${packageName}<#if moduleName??>.${moduleName}</#if>.manager;

import ${packageName}<#if moduleName??>.${moduleName}</#if>.domain.${tableInfo.tableClassName}Entity;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.rsp.${tableInfo.tableClassName}ResultDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}QueryDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dm.common.mybatis.data.service.BaseService;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * ${tableInfo.tableRemarks?if_exists}管理层
 *
 * @author: ${author}
 * @date: ${date}
 */
public interface ${tableInfo.tableClassName}Manager extends BaseService<${tableInfo.tableClassName}Entity> {

    /** 自定义分页查询 */
    Page<${tableInfo.tableClassName}ResultDTO> findPage(Page<Object> page, ${tableInfo.tableClassName}QueryDTO query);

    /** 自定义列表查询 */
    List<${tableInfo.tableClassName}ResultDTO> findList(${tableInfo.tableClassName}QueryDTO query);

    /** 根据id查询 ${tableInfo.tableRemarks?if_exists}数据 */
    ${tableInfo.tableClassName}Entity findById(Long id);

    /** 根据id删除 ${tableInfo.tableRemarks?if_exists}数据 */
    @Transactional(rollbackFor = Exception.class)
    boolean deleteById(Long id);
}