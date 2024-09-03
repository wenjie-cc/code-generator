package ${packageName}<#if moduleName??>.${moduleName}</#if>.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dm.common.base.response.PageInfo;
import com.dm.common.base.response.Param;
import com.dm.common.mybatis.data.service.impl.BaseServiceImpl;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.domain.${tableInfo.tableClassName}Entity;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.mapper.${tableInfo.tableClassName}Mapper;
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
public class ${tableInfo.tableClassName}ServiceImpl extends BaseServiceImpl<${tableInfo.tableClassName}Mapper, ${tableInfo.tableClassName}Entity> implements ${tableInfo.tableClassName}Service {
    private final ${tableInfo.tableClassName}Convert ${tableInfo.tableClassName?uncap_first}Convert;

    /** 分页查询 ${tableInfo.tableRemarks?if_exists}数据 */
    @Override
    public PageInfo<${tableInfo.tableClassName}ResultDTO> page(Param pageable, ${tableInfo.tableClassName}QueryDTO query) {
        return this.baseMapper.selectDtoPageInfo(Pages.page(pageable), wrapper(query), ${tableInfo.tableClassName?uncap_first}Convert::convert);
    }

    /** 查询 ${tableInfo.tableRemarks?if_exists}数据 */
    @Override
    public List<${tableInfo.tableClassName}ResultDTO> find(${tableInfo.tableClassName}QueryDTO query) {
        return this.baseMapper.selectDtoList(wrapper(query), ${tableInfo.tableClassName?uncap_first}Convert::convert);
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
        return this.baseMapper.selectDtoById(id, ${tableInfo.tableClassName?uncap_first}Convert::convert);
    }

    /** 新增 ${tableInfo.tableRemarks?if_exists}数据 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insert(@Valid ${tableInfo.tableClassName}CreateDTO dto) {
        ${tableInfo.tableClassName}Entity entity = ${tableInfo.tableClassName?uncap_first}Convert.create(dto);
        this.save(entity);
        return entity.getId();
    }

    /** 根据id修改 ${tableInfo.tableRemarks?if_exists}数据 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateById(@Valid ${tableInfo.tableClassName}UpdateDTO dto) {
        ${tableInfo.tableClassName}Entity old = Optional.ofNullable(this.getById(dto.getId()))
                .orElseThrow(() -> CommonErrors.BAD_REQUEST.asException("数据不存在"));
        ${tableInfo.tableClassName}Entity entity = ${tableInfo.tableClassName?uncap_first}Convert.update(dto);
        return this.updateById(entity);
    }

    /** 根据id删除 ${tableInfo.tableRemarks?if_exists}数据 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteById(Long id) {
        CommonErrors.BAD_REQUEST.check(Objects.nonNull(id), "ID不能为空");
        return this.removeById(id);
    }

    /** 根据id删除 ${tableInfo.tableRemarks?if_exists}数据 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteById(${tableInfo.tableClassName}DeleteDTO dto) {
         CommonErrors.BAD_REQUEST.check(Objects.nonNull(dto.getId()), "ID不能为空");
         return this.deleteById(dto.getId());
    }

}