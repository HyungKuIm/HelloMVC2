package com.oraclejava.base;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class HikariCPDataSource {

    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
        config.setUsername("sora2");
        config.setPassword("1234");

        // HikariCP 설정
        config.setMaximumPoolSize(10);  // 최대 커넥션 개수
        config.setMinimumIdle(2);       // 최소 유휴 커넥션 개수
        config.setIdleTimeout(30000);   // 유휴 커넥션 타임아웃 (30초)
        config.setMaxLifetime(1800000); // 커넥션 최대 수명 (30분)
        config.setConnectionTimeout(2000); // 커넥션 타임아웃 (2초)

        dataSource = new HikariDataSource(config);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
