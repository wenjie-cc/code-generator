package com.dm.generator.generate;

/**
 * 类型
 *
 * @author: wenjie
 */
public enum GenerateType {

    /**
     * client
     */
    DF_CLIENT("api/${moduleName}/client", "df-client.ftl", "${tableClassName}Client.java"),
    /**
     * client
     */
    DF_CLIENT_FACTORY("api/${moduleName}/factory", "df-client-factory.ftl", "${tableClassName}FallbackFactory.java"),
    /**
     * mapper
     */
    DF_MAPPER("app/${moduleName}/mapper", "df-mapper.ftl", "${tableClassName}Mapper.java"),
    /**
     * transfer
     */
    DF_CONVERT("app/${moduleName}/convert", "df-convert.ftl", "${tableClassName}Convert.java"),
    /**
     * controller
     */
    DF_CONTROLLER("app/${moduleName}/controller", "df-controller.ftl", "${tableClassName}Controller.java"),
    /**
     * admin-controller
     */
    DF_ADMIN_CONTROLLER("app/${moduleName}/controller/admin", "df-controller-admin.ftl", "${tableClassName}AdminController.java"),
    /**
     * inner-controller
     */
    DF_INNER_CONTROLLER("app/${moduleName}/controller/inner", "df-controller-inner.ftl", "${tableClassName}InnerController.java"),
    /**
     * web-controller
     */
    DF_WEB_CONTROLLER("app/${moduleName}/controller/web", "df-controller-web.ftl", "${tableClassName}WebController.java"),
    /**
     * app-controller
     */
    DF_APP_CONTROLLER("app/${moduleName}/controller/app", "df-controller-app.ftl", "${tableClassName}AppController.java"),
    /**
     * query-dto
     */
    DF_QUERY_DTO("api/${moduleName}/dto/req", "df-req-query-dto.ftl", "${tableClassName}QueryDTO.java"),
    /**
     * request-dto
     */
    DF_REQ_CREATE_DTO("api/${moduleName}/dto/req", "df-req-create-dto.ftl", "${tableClassName}CreateDTO.java"),
    /**
     * request-update-dto
     */
    DF_REQ_UPDATE_DTO("api/${moduleName}/dto/req", "df-req-update-dto.ftl", "${tableClassName}UpdateDTO.java"),
    /**
     * request-delete-dto
     */
    DF_REQ_DELETE_DTO("api/${moduleName}/dto/req", "df-req-delete-dto.ftl", "${tableClassName}DeleteDTO.java"),
    /**
     * response-dto
     */
    DF_RESPONSE_DTO("api/${moduleName}/dto/rsp", "df-rsp-dto.ftl", "${tableClassName}ResultDTO.java"),
    /**
     * manager
     */
    DF_I_MANAGER("app/${moduleName}/manager", "df-i-manager.ftl", "${tableClassName}Manager.java"),
    /**
     * manager-impl
     */
    DF_I_MANAGER_IMPL("app/${moduleName}/manager/impl", "df-i-manager-impl.ftl", "${tableClassName}ManagerImpl.java"),
    /**
     * service
     */
    DF_I_SERVICE("app/${moduleName}/service", "df-i-service.ftl", "${tableClassName}Service.java"),
    /**
     * service-impl
     */
    DF_I_SERVICE_IMPL("app/${moduleName}/service/impl", "df-i-service-impl.ftl", "${tableClassName}ServiceImpl.java"),
    /**
     * entity
     */
    DF_ENTITY("app/${moduleName}/domain", "df-entity.ftl", "${tableClassName}Entity.java"),
    /**
     * sql-mapper-xml
     */
    DF_SQL_MAP_XML("mybatis/mapper/${appName}/${moduleName}", "df-sql-map-xml.ftl", "${tableClassName}Mapper.xml");

    private String modelType;

    private String tempFile;

    private String suffix;

    GenerateType(String modelType, String tempFile, String suffix) {
        this.modelType = modelType;
        this.tempFile = tempFile;
        this.suffix = suffix;
    }

    public String getModelType() {
        return modelType;
    }

    public String getTempFile() {
        return this.tempFile;
    }

    public String getSuffix() {
        return this.suffix;
    }

}
