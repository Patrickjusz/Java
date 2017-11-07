package kilometrowka.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kilometrowka.klasy.Firma;
import org.springframework.jdbc.core.JdbcTemplate;

public class FirmaDao {

    private JdbcTemplate jdbcTemplate;

    public FirmaDao() {
        MysqlConfig mysql = new MysqlConfig();
        jdbcTemplate = new JdbcTemplate(mysql.getDataSource());
    }

    public void zapiszFirme(Firma firma, int id_uzytkownika) {
        String sql = "INSERT INTO firmy (nazwa_firmy, adres, miasto, kod_pocztowy, nip, regon, id_uzytkownika)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, firma.getNazwaFirmy(), firma.getAdres(), firma.getMiasto(),
                firma.getKodPocztowy(), firma.getNip(), firma.getRegon(), id_uzytkownika);
    }

    public void aktualizujFirme(int id, Firma firma, int id_uzytkownika) {
        String sql = "UPDATE FIRMY SET nazwa_firmy = ?, adres = ?, miasto = ?, kod_pocztowy = ?, nip = ?, regon = ? WHERE id_firmy = ? and id_uzytkownika = ?";
        jdbcTemplate.update(sql, firma.getNazwaFirmy(), firma.getAdres(), firma.getMiasto(),
                firma.getKodPocztowy(), firma.getNip(), firma.getRegon(), id, id_uzytkownika);
    }

    public void usunFirme(int id, int id_uzytkownika) {
        String sql = "DELETE FROM firmy WHERE id_firmy=? and id_uzytkownika=? limit 1";
        jdbcTemplate.update(sql, id, id_uzytkownika);
        sql = "DELETE FROM przejazdy WHERE id_firmy=? and id_uzytkownika=? limit 1";
        jdbcTemplate.update(sql, id, id_uzytkownika);
    }

    public List<Firma> pobierzFirmy(int id_uzytkownika) {
        String sql = "SELECT * FROM FIRMY where id_uzytkownika=?";

        List<Firma> firmy = new ArrayList<Firma>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id_uzytkownika);

        for (Map row : rows) {
            Firma firma = new Firma();
            firma.setNazwaFirmy((String) (row.get("nazwa_firmy")));
            firma.setAdres((String) row.get("adres"));
            firma.setMiasto((String) row.get("miasto"));
            firma.setKodPocztowy((String) row.get("kod_pocztowy"));
            firma.setNip((String) row.get("nip"));
            firma.setRegon((String) row.get("regon"));
            firma.setId((int) row.get("id_firmy"));
            firmy.add(firma);
        }

        return firmy;
    }

    public Firma pobierzFirmePoId(int id, int id_uzytkownika) {
        String sql = "SELECT * FROM FIRMY where id_firmy=? and id_uzytkownika=? limit 1";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id, id_uzytkownika);

        for (Map row : rows) {
            Firma firma = new Firma();
            firma.setNazwaFirmy((String) (row.get("nazwa_firmy")));
            firma.setAdres((String) row.get("adres"));
            firma.setMiasto((String) row.get("miasto"));
            firma.setKodPocztowy((String) row.get("kod_pocztowy"));
            firma.setNip((String) row.get("nip"));
            firma.setRegon((String) row.get("regon"));
            firma.setId(id);
            return firma;
        }

        return null;
    }

    public boolean sprawdzFirmy(int id_uzytkownika) {
        //sprawdza czy istnie firma uzytkownika
        String sql = "SELECT * FROM firmy where id_uzytkownika=? limit 1";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id_uzytkownika);
        for (Map row : rows) {
            return true;
        }
        return false;
    }

}
