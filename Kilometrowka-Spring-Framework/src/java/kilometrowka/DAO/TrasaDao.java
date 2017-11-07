package kilometrowka.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kilometrowka.klasy.Trasa;
import org.springframework.jdbc.core.JdbcTemplate;

public class TrasaDao {

    private JdbcTemplate jdbcTemplate;

    public TrasaDao() {
        MysqlConfig mysql = new MysqlConfig();
        jdbcTemplate = new JdbcTemplate(mysql.getDataSource());
    }

    public void zapiszTrase(Trasa trasa, int id_uzytkownika) {
        String sql = "INSERT INTO trasy (nazwa_trasy, miejscowosc_zrodlowa, miejscowosc_docelowa, odleglosc, opis, id_uzytkownika)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, trasa.getNazwa(), trasa.getSkad(),
                trasa.getDokad(), trasa.getOdleglosc(), trasa.getOpis(), id_uzytkownika);
    }

    public void usunTrase(int id, int id_uzytkownika) {
        String sql = "DELETE FROM trasy WHERE id_trasy=? and id_uzytkownika=? limit 1";
        jdbcTemplate.update(sql, id, id_uzytkownika);
        sql = "DELETE FROM przejazdy WHERE id_trasy=? and id_uzytkownika=? limit 1";
        jdbcTemplate.update(sql, id, id_uzytkownika);
    }

    public void aktualizujTrase(int id, Trasa trasa, int id_uzytkownika) {
        //String sql = "UPDATE FIRMY SET nazwa_firmy = ?, adres = ?, miasto = ?, kod_pocztowy = ?, nip = ?, regon = ? WHERE id_firmy = ?";
        String sql = "UPDATE TRASY SET nazwa_trasy = ?, miejscowosc_zrodlowa = ?, miejscowosc_docelowa = ?, odleglosc = ?, opis = ? WHERE id_trasy = ? and id_uzytkownika=?";
        jdbcTemplate.update(sql, trasa.getNazwa(), trasa.getSkad(),
                trasa.getDokad(), trasa.getOdleglosc(), trasa.getOpis(), id, id_uzytkownika);
    }

    public List<Trasa> pobierzTrasy(int id_uzytkownika) {
        String sql = "SELECT * FROM TRASY where id_uzytkownika=?";

        List<Trasa> trasy = new ArrayList<Trasa>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id_uzytkownika);

        for (Map row : rows) {
            Trasa trasa = new Trasa();
            trasa.setNazwa((String) (row.get("nazwa_trasy")));
            trasa.setSkad((String) row.get("miejscowosc_zrodlowa"));
            trasa.setDokad((String) row.get("miejscowosc_docelowa"));
            trasa.setOdleglosc((String) row.get("odleglosc").toString());
            trasa.setOpis((String) row.get("opis"));
            trasa.setId((int) row.get("id_trasy"));
            trasy.add(trasa);
        }

        return trasy;
    }

    public Trasa pobierzTrasePoId(int id, int id_uzytkownika) {
        String sql = "SELECT * FROM TRASY where id_trasy=? and id_uzytkownika=? limit 1";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id, id_uzytkownika);

        for (Map row : rows) {
            Trasa trasa = new Trasa();
            trasa.setNazwa((String) (row.get("nazwa_trasy")));
            trasa.setSkad((String) row.get("miejscowosc_zrodlowa"));
            trasa.setDokad((String) row.get("miejscowosc_docelowa"));
            trasa.setOdleglosc((String) row.get("odleglosc").toString());
            trasa.setOpis((String) row.get("opis"));
            trasa.setNazwa((String) (row.get("nazwa_trasy")));
            trasa.setId(id);
            return trasa;
        }

        return null;
    }

    public boolean sprawdzTrasy(int id_uzytkownika) {
        //sprawdza czy istnieje pracownik uzytkownika
        String sql = "SELECT * FROM trasy where id_uzytkownika=? limit 1";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id_uzytkownika);
        for (Map row : rows) {
            return true;
        }
        return false;
    }
}
