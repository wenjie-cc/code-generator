package ${packageName}<#if moduleName??>.${moduleName}</#if>.factory;

import ${packageName}<#if moduleName??>.${moduleName}</#if>.client.${tableInfo.tableClassName}Client;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.rsp.${tableInfo.tableClassName}ResultDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}QueryDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}CreateDTO;
import ${packageName}<#if moduleName??>.${moduleName}</#if>.dto.req.${tableInfo.tableClassName}UpdateDTO;
import com.dm.common.base.error.CommonErrors;
import com.dm.common.base.response.PageInfo;
import com.dm.common.base.response.R;
import org.springframework.stereotype.Component;
import org.springframework.cloud.openfeign.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * ${tableInfo.tableRemarks?if_exists}降级处理
 *
 * @author: ${author}
 * @date: ${date}
 */
@Slf4j
@Component
public class ${tableInfo.tableClassName}FallbackFactory implements FallbackFactory<${tableInfo.tableClassName}Client> {

    @Override
    public ${tableInfo.tableClassName}Client create(Throwable throwable) {
        log.error("日志服务调用失败:{}", throwable.getMessage());
        return new ${tableInfo.tableClassName}Client() {
            @Override
            public R<PageInfo<${tableInfo.tableClassName}ResultDTO>> page(${tableInfo.tableClassName}QueryDTO dto) {
                return R.error(CommonErrors.INTERNAL_SERVER_ERROR.getCode(), null);
            }

            @Override
            public R<${tableInfo.tableClassName}ResultDTO> findById(Long id) {
                return R.error(CommonErrors.INTERNAL_SERVER_ERROR.getCode(), null);
            }

        };
    }
}