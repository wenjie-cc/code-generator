package com.dm.generator.handle;

import com.dm.generator.Config;
import com.dm.generator.handle.impl.DM8Handle;
import com.dm.generator.handle.impl.MySql8Handle;
import com.dm.generator.handle.impl.PgHandle;
import com.dm.generator.model.TableInfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * @author: wenjie
 */
public interface TableHandle {

    TableInfo getTableInfo(String owner, String tableName, Connection conn, String prefix) throws SQLException;

    /**
     * 获取表信息
     */
    default List<String> getAllTableInfo(Connection conn) throws SQLException {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + "还未实现getAllTableInfo方法");
    }

    class TableHandleFactory {

        public TableHandle create(Config.Database database) {
            String drive = database.getDriver();
            if (Objects.equals(database.getIsMysql8(), true)) {
                return new MySql8Handle();
            } else if (Objects.equals(drive, "org.postgresql.Driver")) {
                return new PgHandle();
            } else {
                return new DM8Handle();
            }
        }
    }

}
