package magnit.test.dao;

import magnit.test.model.DatabaseProperties;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;


public class DaoDataSource {

    public static DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DatabaseProperties.DATABASE_PROPERTIES.getDataBaseDriver());
        dataSource.setUrl(DatabaseProperties.DATABASE_PROPERTIES.getUrl());
        dataSource.setUsername(DatabaseProperties.DATABASE_PROPERTIES.getUserName());
        dataSource.setPassword(DatabaseProperties.DATABASE_PROPERTIES.getPassword());
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxOpenPreparedStatements(DatabaseProperties.POOL_SIZE);

        return dataSource;
    }
}
