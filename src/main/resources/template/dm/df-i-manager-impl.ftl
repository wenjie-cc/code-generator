package ${packageName}.service<#if moduleName??>.${moduleName}</#if>.manager.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.domain.${tableInfo.tableClassName}Entity;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.mapper.${tableInfo.tableClassName}Mapper;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.manager.${tableInfo.tableClassName}Manager;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.rsp.${tableInfo.tableClassName}ResultDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}QueryDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dm.common.base.error.CommonErrors;
import com.dm.common.mybatis.data.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Objects;

/**
 * ${tableInfo.tableRemarks?if_exists}管理层实现
 *
 * @author: ${author}
 * @date: ${date}
 */
@Service
@RequiredArgsConstructor
public class ${tableInfo.tableClassName}ManagerImpl extends BaseServiceImpl<${tableInfo.tableClassName}Mapper, ${tableInfo.tableClassName}Entity> implements ${tableInfo.tableClassName}Manager {

    /** 自定义分页查询 */
    @Override
    public Page<${tableInfo.tableClassName}ResultDTO> findPage(Page<Object> page, ${tableInfo.tableClassName}QueryDTO query) {
        return this.baseMapper.findPage(page, query);
    }

    /** 自定义列表查询 */
    @Override
    public List<${tableInfo.tableClassName}ResultDTO> findList(${tableInfo.tableClassName}QueryDTO query) {
        return this.baseMapper.findList(query);
    }

    /** 根据id查询 ${tableInfo.tableRemarks?if_exists}数据 */
    @Override
    public ${tableInfo.tableClassName}Entity findById(Long id) {
        CommonErrors.BAD_REQUEST.check(Objects.nonNull(id), "ID不能为空");
        return getById(id);
    }

    /** 根据id更新 ${tableInfo.tableRemarks?if_exists}数据，重写 */
    public boolean updateById(${tableInfo.tableClassName}Entity entity) {
        return SqlHelper.retBool(getBaseMapper().updateById(entity));
    }

    /** 根据id删除 ${tableInfo.tableRemarks?if_exists}数据 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteById(Long id) {
        CommonErrors.BAD_REQUEST.check(Objects.nonNull(id), "ID不能为空");
        return this.removeById(id);
    }

}