package Database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @program: 2020.12.12
 * @description: ${description}
 * @author: spdz
 * @create: 2020-12-10 21:15
 **/

//懒汉模式单例
public class DBUtil2 {

    private static volatile DataSource dataSource = null;

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            synchronized(DBUtil2.class) {
                if (dataSource == null) {
                    MysqlDataSource mysqlDataSource = new MysqlDataSource();
                    mysqlDataSource.setServerName("127.0.0.1");
                    mysqlDataSource.setPort(3306);
                    mysqlDataSource.setUser("root");
                    mysqlDataSource.setPassword("spdz");
                    mysqlDataSource.setUseSSL(false);
                    mysqlDataSource.setCharacterEncoding("utf8");
                    dataSource = new MysqlDataSource();
                }
            }
        }
        return dataSource.getConnection();
    }

}
