package com.multitenancy.teste.dao;

import com.multitenancy.teste.model.BaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Tobias Andre [tobiasae@gmail.com] on 21/07/2017.
 */
@Profile("jdbc")
public class BaseConfigDAO extends JdbcDaoSupport {

    private static final String BASECONFIG_SQL = "SELECT * FROM BaseConfig";

    @Autowired
    public BaseConfigDAO(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public List<BaseConfig> BaseConfigs() {
        return getJdbcTemplate().query(BASECONFIG_SQL, new BaseConfigRowMapper());
    }

    public List<BaseConfig> findAll() {
        return getJdbcTemplate().query(BASECONFIG_SQL, new BaseConfigRowMapper());
    }

    public BaseConfig findByEmail(String dsEmail){
        return getJdbcTemplate().queryForObject("select * from baseconfig where ds_email like '%"+dsEmail+"%'",new BaseConfigRowMapper());
    }

    private static class BaseConfigRowMapper implements RowMapper<BaseConfig> {

        /*
         * (non-Javadoc)
         * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
         */
        public BaseConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
            BaseConfig baseConfig = new BaseConfig();
            baseConfig.setIdConfig(rs.getInt("id_config"));
            baseConfig.setDsEmail(rs.getString("ds_email"));
            baseConfig.setNmBase(rs.getString("nm_base"));
            return baseConfig;
        }
    }
}
