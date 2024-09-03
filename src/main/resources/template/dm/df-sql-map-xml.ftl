<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${packageName}<#if moduleName??>.${moduleName}</#if>.mapper.${tableInfo.tableClassName}Mapper">
    <sql id="find_data_select">
        select * from ${tableInfo.tableName} a where 1=1
    </sql>
    <sql id="find_data_where">
        <if test="dto.id != null">
            and a.id = ${r'#'}{dto.id}
        </if>
    </sql>
    <select id="findPage" resultType="${packageName}<#if moduleName??>.${moduleName}</#if>.dto.rsp.${tableInfo.tableClassName}ResultDTO">
        <include refid="find_data_select"/>
        <include refid="find_data_where"/>
    </select>
    <select id="findList" resultType="${packageName}<#if moduleName??>.${moduleName}</#if>.dto.rsp.${tableInfo.tableClassName}ResultDTO">
        <include refid="find_data_select"/>
        <include refid="find_data_where"/>
    </select>
</mapper>
