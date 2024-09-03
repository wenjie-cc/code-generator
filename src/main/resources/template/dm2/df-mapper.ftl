package ${packageName}<#if moduleName??>.${moduleName}</#if>.mapper;

import ${packageName}<#if moduleName??>.${moduleName}</#if>.domain.${tableInfo.tableClassName}Entity;
import com.dm.common.mybatis.data.repo.SuperMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ${tableInfo.tableRemarks?if_exists}数据层
 *
 * @author: ${author}
 * @date: ${date}
 */
@Mapper
public interface ${tableInfo.tableClassName}Mapper extends SuperMapper<${tableInfo.tableClassName}Entity> {
}