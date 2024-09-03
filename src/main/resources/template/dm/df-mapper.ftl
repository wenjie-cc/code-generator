package ${packageName}<#if moduleName??>.${moduleName}</#if>.mapper;

import ${packageName}<#if moduleName??>.${moduleName}</#if>.domain.${tableInfo.tableClassName}Entity;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.rsp.${tableInfo.tableClassName}ResultDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}QueryDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dm.common.mybatis.data.repo.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * ${tableInfo.tableRemarks?if_exists}数据层
 *
 * @author: ${author}
 * @date: ${date}
 */
@Mapper
public interface ${tableInfo.tableClassName}Mapper extends SuperMapper<${tableInfo.tableClassName}Entity> {
    Page<${tableInfo.tableClassName}ResultDTO> findPage(Page<Object> page, @Param("dto") ${tableInfo.tableClassName}QueryDTO query);

    List<${tableInfo.tableClassName}ResultDTO> findList(@Param("dto") ${tableInfo.tableClassName}QueryDTO query);
}