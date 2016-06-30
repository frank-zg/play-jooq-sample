package mysql.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by zg on 2016/6/28.
 */
public class JdbcPool {

    private HikariDataSource ds;

    public JdbcPool() {
        HikariConfig config = new HikariConfig();
        config.setMinimumIdle(1);
        config.setMaximumPoolSize(10);
        config.setConnectionTestQuery("select 1");
        config.setJdbcUrl("jdbc:mysql://localhost/test");
        config.setUsername("root");
        config.setPassword("zhaogang");
        ds = new HikariDataSource(config);
    }

    public Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JdbcPool getPoolSingleton() {
        return JdbcPoolSingleton.pool;
    }

    protected static class JdbcPoolSingleton {
        private static final JdbcPool pool = new JdbcPool();
    }

}
