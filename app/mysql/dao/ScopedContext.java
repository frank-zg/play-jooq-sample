package mysql.dao;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

import java.sql.Connection;

/**
 * Created by zg on 2016/6/28.
 */
public class ScopedContext implements AutoCloseable {
    private Connection connection = null;
    private DSLContext dslContext = null;

    public ScopedContext() {
    }

    public DSLContext getDSLContext() {
        if (dslContext == null) {
            Configuration defaultConfiguration = new DefaultConfiguration().set(getConnection())
                    .set(SQLDialect.MYSQL);
            dslContext = DSL.using(defaultConfiguration);
        }
        return dslContext;
    }

    private Connection getConnection() {
        if (connection == null) {
            connection = createPoolConnection();
        }
        return connection;
    }

    private Connection createPoolConnection() {
        return JdbcPool.getPoolSingleton().getConnection();
    }

    @Override
    public void close() throws Exception {
        connection.close();
        connection = null;
    }
}
