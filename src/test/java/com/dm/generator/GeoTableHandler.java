package com.dm.generator;

import com.dm.generator.utils.MetaDataUtils;
import lombok.Data;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wenjie
 * @date 2020/6/22
 */
public class GeoTableHandler {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        GeoTableHandler ll = new GeoTableHandler();
        ll.process();
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Config.Database database = new Config.Database();
        // 设置 数据库设置
        database.setDriver("com.mysql.cj.jdbc.Driver");
        database.setUrl(
                "jdbc:mysql://127.0.0.1:3306/supervision?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
        database.setUserName("root");
        database.setPassWord("root");
        return MetaDataUtils.getConnection(database);
    }

    private QueryRunner qr = new QueryRunner();
    private final static String QUERY_SQL = "select * from `fam-comm-lxh-2`.geo a where a.statis_id is null";
    private final static String UPDATE_SQL = "update `fam-comm-lxh-2`.geo set statis_id=?, statis_pid=?, statis_name=?, statis_ext_name=? where id=?";

    private void process() throws IOException, SQLException, ClassNotFoundException {
        init();
        // 获取csv中的数据
        csv("/Users/wenjie/Downloads/test-1");
        // 获取原表数据
        Connection conn = getConnection();
        List<UpdateData> query = qr.query(conn, QUERY_SQL, new ResultSetHandler<List<UpdateData>>() {

            @Override
            public List<UpdateData> handle(ResultSet rs) throws SQLException {
                List<UpdateData> list = new LinkedList<>();
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String name = rs.getString("name");
                    Integer level = rs.getInt("level");
                    String path = rs.getString("path");
                    if (path == null || Objects.equals(path, "null") || StringUtils.isEmpty(path)) {
                        continue;
                    }
                    if (name.equals("市辖区")) {
                        path = path.replace("/市辖区", "");
                    }
                    if (csvMap.get(path) != null) {
                        AddrData addrData = csvMap.get(path);
                        UpdateData d = new UpdateData();
                        d.setId(id);
                        d.setAddrData(addrData);
                        list.add(d);
//                        // 处理
//                        System.out.println(String.format("id = %d, level=%d, name = %s, path = %s, %s, %s, %s %s",
//                                id, level, name, path, addrData.getId(), addrData.getDeep(), addrData.getName(), addrData.getExtName()));

                    } else {
                        System.out.println(String.format("=======> id = %d, level=%d, name = %s, path = %s", id, level, name, path));
                    }
                }
                return list;
            }
        });
        conn.close();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        for (UpdateData updateData : query) {
            updateData(updateData.getAddrData(), updateData.getId());
            System.out.println("id = " + updateData.getId() + ",    updateData = " + updateData.toString());
        }
    }

    private void updateData(AddrData addrData, Long id) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        qr.update(conn, UPDATE_SQL, addrData.getId(), addrData.getPid(), addrData.getName(), addrData.getExtName(), id);
        conn.close();
    }

    private List<AddrData> csvList = new LinkedList<>();
    private Map<String, AddrData> csvMap = new HashMap<>();
    private Map<Long, AddrData> pMap = new HashMap<>();

    private void csv(String file) throws IOException {
        Files.lines(Paths.get(file)).forEach(v -> {
            if (StringUtils.isEmpty(v)) {
                return;
            }
            AddrData addrData = null;
            try {
                addrData = AddrData.of(v);
            } catch (Exception e) {
                System.out.println(v);
                throw e;
            }
            if (addrData.getDeep() >= 3) {
                return;
            }
            addrData.setPath(getPath(addrData));
            csvList.add(addrData);
            pMap.put(addrData.getId(), addrData);
            csvMap.put(addrData.getPath(), addrData);
        });
    }

    @Data
    public static class GeoData {
        private Long id;
        private String name;
        private Integer level;
        private String path;
    }

    @Data
    public static class UpdateData {
        private Long id;
        private AddrData addrData;
    }

    @Data
    public static class AddrData {
        private Long id;
        private Long pid;
        private Integer deep;
        private String name;
        private String extName;
        private String path;

        public static AddrData of(String s) {
            AddrData addrData = new AddrData();
            String[] split = s.split(",");
            addrData.setId(Long.parseLong(split[0]));
            addrData.setPid(Long.parseLong(split[1]));
            addrData.setDeep(Integer.parseInt(split[2]));
            addrData.setName(split[3]);
            addrData.setExtName(split[7]);
            return addrData;
        }
    }

    private Map<String, String> tMap = new HashMap<>();

    private String getPath(AddrData addrData) {
        if (addrData.getDeep() == 0) {
            if (tMap.get(addrData.getExtName()) != null) {
                return tMap.get(addrData.getExtName());
            }
            return addrData.getExtName();
        } else if (addrData.getDeep() == 1) {
            if (Objects.equals(addrData.getExtName(), "市辖区")) {
                if (Objects.equals(addrData.getName(), "天津")) {
                    return "天津市/天津市";
                }
                AddrData p = pMap.get(addrData.getPid());
                return p.getName() + "/" + p.getExtName();
            }
            if (Objects.equals(addrData.getName(), "重庆城区")) {
                return "重庆市/重庆市";
            }
            AddrData p = pMap.get(addrData.getPid());
            return p.getExtName() + "/" + addrData.getExtName();
        } else if (addrData.getDeep() == 2) {
            AddrData c = pMap.get(addrData.getPid());
            String path = getPath(c);
            return path + "/" + addrData.getExtName();
        }
        throw new RuntimeException("找不到对应的 deep" + addrData.getDeep());
    }

    private Map<String, String> cMap = new HashMap<>();
    private Map<String, String> dMap = new HashMap<>();
    private void init() {
        tMap.put("北京市", "北京");
        tMap.put("上海市", "上海");
        tMap.put("重庆市", "重庆市");
        tMap.put("香港特别行政区", "香港");
        tMap.put("台湾省", "台湾");


        cMap.put("重庆城区", "香港");

        dMap.put("密云区", "密云县");
        dMap.put("延庆区", "延庆县");
        dMap.put("崇明区", "崇明县");
//        dMap.put("库尔勒市", "库尔勒经济技术开发区");



    }
}
