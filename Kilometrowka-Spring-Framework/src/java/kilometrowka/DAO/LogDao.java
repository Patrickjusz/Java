package kilometrowka.DAO;

import org.springframework.jdbc.core.JdbcTemplate;

public class LogDao {

    private JdbcTemplate jdbcTemplate;

    public LogDao() {
        MysqlConfig mysql = new MysqlConfig();
        jdbcTemplate = new JdbcTemplate(mysql.getDataSource());
    }

    public void dodajZdarzenie(int akcja, String ip, String useragent, String tresc) {
        String sql = "INSERT INTO logi (data, akcja, ip, useragent, tresc)"
                + " VALUES (NOW(), ?, ?, ?, ?)";
        jdbcTemplate.update(sql, akcja, ip, useragent, tresc);

    }

}
