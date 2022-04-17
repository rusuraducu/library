package Biblioteca.Connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLException;

public class ConnectionPool {
        private static HikariDataSource ds;
        static{

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/library?useSSL=false");
            config.setUsername("root");
            config.setPassword("password");
            config.setDriverClassName("com.mysql.jdbc.Driver");
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            ds = new HikariDataSource(config);
        }

        public static java.sql.Connection getConnection()  {
            try {
                return ds.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

}
