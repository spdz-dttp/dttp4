package com.spdz;

/**
 * @program: 2021.1.26
 * @description: ${description}
 * @author: spdz
 * @create: 2021-01-28 16:32
 **/

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 懒汉单例 - Double Check
 * URL 的理解
 */
 
public class DBUtil {
    private static volatile DataSource dataSource = null;

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            synchronized (DBUtil.class) {
                if (dataSource == null) {
                    dataSource = initDataSource();
                }
            }
        }
        return dataSource.getConnection();
    }

    private static DataSource initDataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL("jdbc:mysql://127.0.0.1:3306/dttp_reward?useSSL=false&characterEncoding=utf8");
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("spdz");

        return mysqlDataSource;
    }

}
