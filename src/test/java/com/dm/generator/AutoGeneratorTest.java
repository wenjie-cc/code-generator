package com.dm.generator;


import com.dm.generator.utils.Utils;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wenjie
 * @date 2023/1/11
 */
public class AutoGeneratorTest {

    public static void main(String[] args) {
        bizConfig();
    }

    public static void chat() {
        File dir = new File("./out");
        if (dir.exists()) {
            dir.delete();
        }
        String prefix = "ca_";
        String appName = "chat";
        String feignServerName = "XQ_CHAT";
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);
            configData.setModuleName("friend");
            tableNames.add("ca_chat_friend");
            tableNames.add("ca_chat_friend_apply");
            tableNames.add("ca_chat_friend_apply_message");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("group");
            tableNames.add("ca_chat_group");
            tableNames.add("ca_chat_group_user");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("feedback");
            tableNames.add("ca_chat_feedback");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("chat");
            tableNames.add("ca_chat_notice");
            tableNames.add("ca_chat_question");
            tableNames.add("ca_chat_remind");
            tableNames.add("ca_chat_vote");

            generator(configData, prefix, appName, feignServerName);
        }
    }

    public static void bizConfig() {
        File dir = new File("./out");
        if (dir.exists()) {
            dir.delete();
        }
        String prefix = "cmn_";
        String appName = "bizconfig";
        String feignServerName = "XQ_BIZ_CONFIG";
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);
            configData.setModuleName("version");
            tableNames.add("cmn_app_version");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("calendar");
            tableNames.add("cmn_calendar");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("message");
            tableNames.add("cmn_course_message");
            tableNames.add("cmn_user_schedule");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("config");
            tableNames.add("cmn_district");
            tableNames.add("cmn_ems");
            tableNames.add("cmn_system_config");
            tableNames.add("cmn_university");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("comment");
            tableNames.add("cmn_comment");
            tableNames.add("cmn_comment_give");

            generator(configData, prefix, appName, feignServerName);
        }
    }

    public static void user() {
        File dir = new File("./out");
        if (dir.exists()) {
            dir.delete();
        }
        String prefix = "ue_";
        String appName = "user";
        String feignServerName = "XQ_USER";
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("user");
            tableNames.add("ue_user");
            tableNames.add("ue_user_config");
            tableNames.add("ue_user_login_log");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("third");
            tableNames.add("ue_user_third_bind");
            tableNames.add("ue_user_token");

            generator(configData, prefix, appName, feignServerName);
        }
    }

    public static void course() {
        File dir = new File("./out");
        if (dir.exists()) {
            dir.delete();
        }
        String prefix = "co_";
        String appName = "course";
        String feignServerName = "XQ_COURSE";
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("attendance");
            tableNames.add("co_course_attendance");
            tableNames.add("co_course_attendance_log");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("course");
            tableNames.add("co_course");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("homework");
            tableNames.add("co_course_homework_info");
            tableNames.add("co_course_homework_record");
            tableNames.add("co_course_homework_submit");
            tableNames.add("co_course_homework_timer");
            tableNames.add("co_course_homework_config");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("content");
            tableNames.add("co_course_content");
            tableNames.add("co_course_content_read");
            tableNames.add("co_course_chapter");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("discuss");
            tableNames.add("co_course_discuss_config");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("score");
            tableNames.add("co_course_total_score");
            tableNames.add("co_course_content_total_score");
            tableNames.add("co_course_content_student_total_score");
            tableNames.add("co_course_total_score_config");
            tableNames.add("co_course_total_score_type_config");
            tableNames.add("co_course_total_score_type");
            tableNames.add("co_course_type_total_score");
            tableNames.add("co_course_student_total_score");
            tableNames.add("co_course_offline_score");
            tableNames.add("co_course_offline_grade_config");
            tableNames.add("co_course_offline_student_score");

            generator(configData, prefix, appName, feignServerName);
        }

        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("data");
            tableNames.add("co_course_resource_config");
            tableNames.add("co_course_resource_comment");
            tableNames.add("co_course_resource_comment_give");
            tableNames.add("co_course_resource_doubt");
            tableNames.add("co_course_resource_part");
            tableNames.add("co_course_resource_file");

            generator(configData, prefix, appName, feignServerName);
        }

        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("meeting");
            tableNames.add("co_course_meeting_config");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("interaction");
            tableNames.add("co_course_interaction_record");
            tableNames.add("co_course_interaction_submit");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("notice");
            tableNames.add("co_course_notice_config");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("exam");
            tableNames.add("co_course_error_set");
            tableNames.add("co_course_question_library");
            tableNames.add("co_course_exam_config");
            tableNames.add("co_course_exam_answer");
            tableNames.add("co_course_exam_dynamic_img");
            tableNames.add("co_course_exam_dynamic_teacher");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            prefix = "cl_";
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("clazz");
            tableNames.add("cl_class");
            tableNames.add("cl_class_student");
            tableNames.add("cl_class_group");
            tableNames.add("cl_class_group_student");
            tableNames.add("cl_class_menu");
            tableNames.add("cl_class_menu_init");
            tableNames.add("cl_class_join_audit");
            tableNames.add("cl_class_operation_log");

            generator(configData, prefix, appName, feignServerName);
        }
    }

    public static void cloudNote() {
        File dir = new File("./out");
        if (dir.exists()) {
            dir.delete();
        }
        String prefix = "cn_";
        String appName = "cloudnote";
        String feignServerName = "XQ_CLOUD_NOTE";
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);
            configData.setModuleName("content");
            tableNames.add("cn_cloud_note_file");
            tableNames.add("cn_cloud_note_content");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("folder");
            tableNames.add("cn_cloud_note_folder");
            tableNames.add("cn_cloud_note_manager");

            generator(configData, prefix, appName, feignServerName);
        }
    }

    public static void favorite() {
        File dir = new File("./out");
        if (dir.exists()) {
            dir.delete();
        }
        String prefix = "fa_";
        String appName = "favorite";
        String feignServerName = "XQ_FAVORITE";
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);
            configData.setModuleName("content");
            tableNames.add("fa_favorite_content");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("config");
            tableNames.add("fa_favorite_tag");
            tableNames.add("fa_favorite_type");

            generator(configData, prefix, appName, feignServerName);
        }
    }

    public static void live() {
        File dir = new File("./out");
        if (dir.exists()) {
            dir.delete();
        }
        String prefix = "lv_";
        String appName = "live";
        String feignServerName = "XQ_LIVE";
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("live");
            tableNames.add("lv_live");
            tableNames.add("lv_live_doubt");
            tableNames.add("lv_live_operate_log");
            tableNames.add("lv_live_student");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("act");
            tableNames.add("lv_live_act");
            tableNames.add("lv_live_act_student");

            generator(configData, prefix, appName, feignServerName);
        }
    }

    public static void prepare() {
        File dir = new File("./out");
        if (dir.exists()) {
            dir.delete();
        }
        String prefix = "pl_";
        String appName = "prepare";
        String feignServerName = "XQ_PREPARE";
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("prepare");
            tableNames.add("pl_prepare");
            tableNames.add("pl_prepare_exam_paper");
            tableNames.add("pl_prepare_file");
            tableNames.add("pl_prepare_question_library");
            tableNames.add("pl_prepare_exam_library");

            generator(configData, prefix, appName, feignServerName);
        }
        {
            ConfigData configData = new ConfigData();
            List<String> tableNames = new ArrayList<>();
            configData.setTableNames(tableNames);

            configData.setModuleName("member");
            tableNames.add("pl_prepare_member");
            tableNames.add("pl_prepare_member_role");
            tableNames.add("pl_prepare_operate_log");

            generator(configData, prefix, appName, feignServerName);
        }
    }

    public static void generator(ConfigData configData, String prefix, String appName, String feignServerName) {
        Config config = new Config();
        // 设置 需要生成的表名
        List<String> tableNames = configData.getTableNames();

        String moduleName = configData.getModuleName();
        String basePackage = "com.dm";
        String packageName = basePackage + "." + appName;
        Config.Table table = new Config.Table();
        // 设置 表名前缀
        table.setPrefix(prefix);
        table.setTables(tableNames);
        config.setTable(table);

        Config.Database database = new Config.Database();
        // 设置 数据库设置
        database.setDriver("com.mysql.cj.jdbc.Driver");
        database.setUrl(
                "jdbc:mysql://127.0.0.1:3306/supervision?useUnicode=true&characterEncoding=utf8&nullCatalogMeansCurrent=true&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8");
        database.setUserName("root");
        database.setPassWord("root");
        config.setDatabase(database);

        // 设置 基础设置
        config.setPackageName(packageName);
        config.setModuleName(moduleName);
        config.setAppName(appName);
        config.setFeignServerName(feignServerName);
        config.setAuthor("system");
        config.setDate(Utils.toDay());
        config.setOutPath("./out");
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.generate(config);
    }

    @Data
    public static class ConfigData {
        private List<String> tableNames;
        private String moduleName;
    }
}