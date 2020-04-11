package com.momolela;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JDBCTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Test
    public void testJDBC() throws SQLException {
        log.info("spring boot 默认使用的数据源是：" + dataSource.getClass());
        Connection connection = dataSource.getConnection();
        log.info("数据源获取到的连接：" + connection);
        connection.close();

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from dim0000056");
        log.info("dim0000056 data==="+maps.get(0));
    }
}
