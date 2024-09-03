package ${packageName}<#if moduleName??>.${moduleName}</#if>.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dm.common.base.response.PageInfo;
import com.dm.common.base.response.Param;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.manager.${tableInfo.tableClassName}Manager;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.rsp.${tableInfo.tableClassName}ResultDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}CreateDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}UpdateDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}QueryDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}DeleteDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.domain.${tableInfo.tableClassName}Entity;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.convert.${tableInfo.tableClassName}Convert;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.service.${tableInfo.tableClassName}Service;
import com.dm.common.base.error.CommonErrors;
import com.dm.common.mybatis.utils.Pages;
import com.dm.common.mybatis.utils.Queries;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ${tableInfo.tableRemarks?if_exists}服务层实现
 *
 * @author: ${author}
 * @date: ${date}
 */
@Validated
@Service("${tableInfo.tableClassName?uncap_first}Service")
@RequiredArgsConstructor
public class ${tableInfo.tableClassName}ServiceImpl implements ${tableInfo.tableClassName}Service {
    private final ${tableInfo.tableClassName}Convert ${tableInfo.tableClassName?uncap_first}Convert;
    private final ${tableInfo.tableClassName}Manager ${tableInfo.tableClassName?uncap_first}Manager;

    /** 分页查询 ${tableInfo.tableRemarks?if_exists}数据 */
    @Override
    public PageInfo<${tableInfo.tableClassName}ResultDTO> page(Param pageable, ${tableInfo.tableClassName}QueryDTO query) {
        IPage<${tableInfo.tableClassName}Entity> page = ${tableInfo.tableClassName?uncap_first}Manager.page(Pages.page(pageable), wrapper(query));
        return Pages.convert(page, ${tableInfo.tableClassName?uncap_first}Convert::convert);
    }

    /** 查询 ${tableInfo.tableRemarks?if_exists}数据 */
    @Override
    public List<${tableInfo.tableClassName}ResultDTO> find(${tableInfo.tableClassName}QueryDTO query) {
        return ${tableInfo.tableClassName?uncap_first}Manager.list(wrapper(query)).stream()
                .map(${tableInfo.tableClassName?uncap_first}Convert::convert).collect(Collectors.toList());
    }

    private LambdaQueryWrapper<${tableInfo.tableClassName}Entity> wrapper(${tableInfo.tableClassName}QueryDTO query) {
        LambdaQueryWrapper<${tableInfo.tableClassName}Entity> wrapper = Queries.lambda();
        Queries.accept(query.getId(), v -> wrapper.eq(${tableInfo.tableClassName}Entity::getId, v));
        return wrapper;
    }

    /** 根据id查询 ${tableInfo.tableRemarks?if_exists}数据 */
    @Override
    public ${tableInfo.tableClassName}ResultDTO findById(Long id) {
        CommonErrors.BAD_REQUEST.check(Objects.nonNull(id), "ID不能为空");
        return Optional.ofNullable(${tableInfo.tableClassName?uncap_first}Manager.findById(id)).map(${tableInfo.tableClassName?uncap_first}Convert::convert).orElse(null);
    }

    /** 新增 ${tableInfo.tableRemarks?if_exists}数据 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insert(@Valid ${tableInfo.tableClassName}CreateDTO dto) {
        ${tableInfo.tableClassName}Entity entity = ${tableInfo.tableClassName?uncap_first}Convert.create(dto);
        ${tableInfo.tableClassName?uncap_first}Manager.save(entity);
        return entity.getId();
    }

    /** 根据id修改 ${tableInfo.tableRemarks?if_exists}数据 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateById(@Valid ${tableInfo.tableClassName}UpdateDTO dto) {
        Optional.ofNullable(${tableInfo.tableClassName?uncap_first}Manager.getById(dto.getId()))
                .orElseThrow(() -> CommonErrors.BAD_REQUEST.asException("数据不存在"));
        ${tableInfo.tableClassName}Entity entity = ${tableInfo.tableClassName?uncap_first}Convert.update(dto);
        return ${tableInfo.tableClassName?uncap_first}Manager.updateById(entity);
    }

    /** 根据id删除 ${tableInfo.tableRemarks?if_exists}数据 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteById(Long id) {
        CommonErrors.BAD_REQUEST.check(Objects.nonNull(id), "ID不能为空");
        return ${tableInfo.tableClassName?uncap_first}Manager.deleteById(id);
    }

    /** 根据id删除 ${tableInfo.tableRemarks?if_exists}数据 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteById(${tableInfo.tableClassName}DeleteDTO dto) {
         return deleteById(dto.getId());
    }

}