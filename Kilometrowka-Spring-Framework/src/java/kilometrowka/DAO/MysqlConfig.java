package kilometrowka.DAO;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class MysqlConfig {

    private DriverManagerDataSource dataSource;

    public MysqlConfig() {
        this.dataSource = new DriverManagerDataSource();
        this.dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        this.dataSource.setUrl("jdbc:mysql://localhost:3306/kilometrowka?useUnicode=true&characterEncoding=utf8");
        this.dataSource.setUsername("kilometrowka");
        this.dataSource.setPassword("1234");
    }

    public DriverManagerDataSource getDataSource() {
        return dataSource;
    }

}
