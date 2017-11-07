package kilometrowka.DAO;

import java.util.List;
import java.util.Map;
import kilometrowka.klasy.Logowanie;
import org.springframework.jdbc.core.JdbcTemplate;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class LogowanieDao {

    private JdbcTemplate jdbcTemplate;

    public LogowanieDao() {
        MysqlConfig mysql = new MysqlConfig();
        jdbcTemplate = new JdbcTemplate(mysql.getDataSource());
    }

    public String pobierzSolDynamiczna(String login) {
        String sql = "SELECT sol FROM uzytkownicy where login = '" + login + "' limit 1";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            return String.valueOf(row.get("sol"));
        }
        return "error";
    }

    public Boolean sprawdzDostepnoscLoginu(String login) {
        String sql = "SELECT login FROM uzytkownicy where login = '" + login + "' limit 1";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            return true;
        }
        return false;
    }

    public String hashujMD5(String tekst, String solDynamiczna) {
        //generuje skrot MD5 z solą statyczną i dynamiczną z DB
        String solStatyczna = "aC7677@58!26Z";
        tekst += solStatyczna;
        tekst = solDynamiczna + tekst;

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md5.digest(tekst.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashMD5 = number.toString(16);
            while (hashMD5.length() < 32) {
                hashMD5 = "0" + hashMD5;
            }
            return hashMD5;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public Logowanie zaloguj(String login, String haslo) {
        String sql = "SELECT * FROM uzytkownicy where login = '" + login + "' and haslo = '" + hashujMD5(haslo, pobierzSolDynamiczna(login)) + "' limit 1";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Logowanie logowanie = new Logowanie();
            logowanie.setLogin((String) row.get("login"));
            logowanie.setId((int) row.get("id_uzytkownika"));
            logowanie.setZalogowany(true);
            return logowanie;
        }
        return null;
    }

    public void zarejestrujUzytkownika(Logowanie logowanie) {
        String sql = "INSERT INTO uzytkownicy (login, haslo, email, sol)"
                + " VALUES (?, ?, ?, ?)";

        Random generator = new Random();
        int solDynamiczna = generator.nextInt();
        jdbcTemplate.update(sql, logowanie.getLogin(), hashujMD5(logowanie.getHaslo(), String.valueOf(solDynamiczna)), logowanie.getEmail(), String.valueOf(solDynamiczna));
    }

    public boolean wyloguj() {

        return false;
    }

}
