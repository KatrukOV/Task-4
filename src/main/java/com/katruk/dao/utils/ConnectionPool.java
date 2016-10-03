package com.katruk.dao.utils;

import com.katruk.dao.exceptions.DaoException;
import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

	private static ConnectionPool pool;
	private DataSource dataSource;
	private static final Logger logger = Logger.getLogger(ConnectionPool.class);

	private ConnectionPool() {
		Config config = Config.getInstance();
		PoolProperties poolProperties = new PoolProperties();
		poolProperties.setUrl(config.getValue(Config.URL)); //DB jdbc connection url
		poolProperties.setDriverClassName(config.getValue(Config.DRIVER)); //db jdbc driver type
		poolProperties.setMaxIdle(10); //max amount of thread that are allowed to idle at the same time
		poolProperties.setMaxWait(100); //max wait amount until request timeout exception
		poolProperties.setMaxActive(10); //max amount of active threads in the pool
		dataSource = new DataSource();
		dataSource.setPoolProperties(poolProperties);
	}

	public Connection getConnection() throws DaoException {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			logger.error("Can't get connection", e);
			throw new DaoException("Can't get connection", e);
		}
	}

	public static ConnectionPool getInstance() {
		if (pool == null) {
			synchronized (ConnectionPool.class) {
				if (pool == null) {
					pool = new ConnectionPool();
				}
			}
		}
		return pool;
	}
    public void close(Connection connection) throws DaoException {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Can't close connection", e);
                throw new DaoException("Can't close connection", e);
            }
    }
}