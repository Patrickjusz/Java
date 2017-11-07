package kilometrowka.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kilometrowka.klasy.Pracownik;
import org.springframework.jdbc.core.JdbcTemplate;

public class PracownikDao {

    private JdbcTemplate jdbcTemplate;

    public PracownikDao() {
        MysqlConfig mysql = new MysqlConfig();
        jdbcTemplate = new JdbcTemplate(mysql.getDataSource());
    }

    public void zapiszPracownika(Pracownik pracownik, int id_uzytkownika) {
        String sql = "INSERT INTO pracownicy (imie, nazwisko, adres, miasto, kod_pocztowy, telefon, id_uzytkownika)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, pracownik.getImie(), pracownik.getNazwisko(), pracownik.getAdres(), pracownik.getMiasto(), pracownik.getKodPocztowy(), pracownik.getTelefon(), id_uzytkownika);
    }

    public void usunPracownika(int id, int id_uzytkownika) {
        String sql = "DELETE FROM pracownicy WHERE id_pracownika=? and id_uzytkownika=? limit 1";
        jdbcTemplate.update(sql, id, id_uzytkownika);
        sql = "DELETE FROM przejazdy WHERE id_pracownika=? and id_uzytkownika=? limit 1";
        jdbcTemplate.update(sql, id, id_uzytkownika);
    }

    public void aktualizujPracownika(int id, Pracownik pracownik, int id_uzytkownika) {
        String sql = "UPDATE PRACOWNICY SET imie = ?, nazwisko = ?, adres = ?, miasto = ?, kod_pocztowy = ?, telefon = ? WHERE id_pracownika = ? and id_uzytkownika=?";

        jdbcTemplate.update(sql, pracownik.getImie(), pracownik.getNazwisko(), pracownik.getAdres(), pracownik.getMiasto(), pracownik.getKodPocztowy(), pracownik.getTelefon(), id, id_uzytkownika);
    }

    public List<Pracownik> pobierzPracownikow(int id_uzytkownika) {
        String sql = "SELECT * FROM PRACOWNICY where id_uzytkownika=?";

        List<Pracownik> pracownicy = new ArrayList<Pracownik>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id_uzytkownika);

        for (Map row : rows) {
            Pracownik pracownik = new Pracownik();
            pracownik.setImie((String) (row.get("imie")));
            pracownik.setNazwisko((String) row.get("nazwisko"));
            pracownik.setAdres((String) row.get("adres"));
            pracownik.setMiasto((String) row.get("miasto"));
            pracownik.setKodPocztowy((String) row.get("kod_pocztowy"));
            pracownik.setTelefon((String) row.get("telefon"));
            pracownik.setId((int) row.get("id_pracownika"));
            pracownicy.add(pracownik);
            System.out.println(row.get("imie"));
        }

        return pracownicy;
    }

    public Pracownik pobierzPracownikaPoId(int id, int id_uzytkownika) {
        String sql = "SELECT * FROM PRACOWNICY where id_pracownika=? and id_uzytkownika=? limit 1";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id, id_uzytkownika);

        for (Map row : rows) {
            Pracownik pracownik = new Pracownik();
            pracownik.setImie((String) (row.get("imie")));
            pracownik.setNazwisko((String) row.get("nazwisko"));
            pracownik.setAdres((String) row.get("adres"));
            pracownik.setMiasto((String) row.get("miasto"));
            pracownik.setKodPocztowy((String) row.get("kod_pocztowy"));
            pracownik.setTelefon((String) row.get("telefon"));
            pracownik.setId(id);

            return pracownik;
        }

        return null;
    }

    public boolean sprawdzPracownikow(int id_uzytkownika) {
        //sprawdza czy istnieje pracownik uzytkownika
        String sql = "SELECT * FROM pracownicy where id_uzytkownika=? limit 1";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id_uzytkownika);
        for (Map row : rows) {
            return true;
        }
        return false;
    }

}
