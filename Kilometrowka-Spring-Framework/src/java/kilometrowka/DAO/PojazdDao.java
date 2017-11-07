package kilometrowka.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kilometrowka.klasy.Pojazd;
import kilometrowka.klasy.Stawka;
import org.springframework.jdbc.core.JdbcTemplate;

public class PojazdDao {

    private JdbcTemplate jdbcTemplate;

    public PojazdDao() {
        MysqlConfig mysql = new MysqlConfig();
        jdbcTemplate = new JdbcTemplate(mysql.getDataSource());
    }

    public void zapiszPojazd(Pojazd pojazd, int id_uzytkownika) {
        String sql = "INSERT INTO pojazdy (nazwa_pojazdu, numer_rejestracyjny, pojemnosc_silnika, opis, id_uzytkownika)"
                + " VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, pojazd.getNazwa().trim(),
                pojazd.getNumerRejestracyjny().trim().replace(" ", ""), Integer.parseInt(pojazd.getPojemnoscSilnika()), pojazd.getOpis().trim(), id_uzytkownika);
    }

    public void usunPojazd(int id, int id_uzytkownika) {
        String sql = "DELETE FROM pojazdy WHERE id_pojazdu=? and id_uzytkownika=? limit 1";
        jdbcTemplate.update(sql, id, id_uzytkownika);
        sql = "DELETE FROM przejazdy WHERE id_pojazdu=? and id_uzytkownika=? limit 1";
        jdbcTemplate.update(sql, id, id_uzytkownika);
    }

    public void aktualizujPojazd(int id, Pojazd pojazd, int id_uzytkownika) {
        String sql = "UPDATE POJAZDY SET nazwa_pojazdu = ?, numer_rejestracyjny = ?, pojemnosc_silnika = ?, opis = ? WHERE id_pojazdu = ? and id_uzytkownika=?";

        jdbcTemplate.update(sql, pojazd.getNazwa().trim(),
                pojazd.getNumerRejestracyjny().trim().replace(" ", ""), Integer.parseInt(pojazd.getPojemnoscSilnika()), pojazd.getOpis().trim(), id, id_uzytkownika);

    }

    public List<Pojazd> pobierzPojazdy(int id_uzytkownika) {
        String sql = "SELECT * FROM POJAZDY where id_uzytkownika=?";

        List<Pojazd> pojazdy = new ArrayList<Pojazd>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id_uzytkownika);

        for (Map row : rows) {
            Pojazd pojazd = new Pojazd();
            pojazd.setNazwa((String) (row.get("nazwa_pojazdu")));
            pojazd.setNumerRejestracyjny((String) row.get("numer_rejestracyjny"));
            int pojemnosc;
            pojemnosc = Integer.parseInt(row.get("pojemnosc_silnika").toString());
            String pojemnoscTresc;
            Stawka stawka = new Stawka();

            switch (pojemnosc) {
                case 1:
                    pojazd.setStawka(stawka.getStawkaDo900());
                    pojemnoscTresc = "poniżej 900cm^3";
                    break;
                case 2:
                    pojazd.setStawka(stawka.getStawkaPowyzej900());
                    pojemnoscTresc = "powyżej 900cm^3";
                    break;
                case 3:
                    pojazd.setStawka(stawka.getStawkaMotocykl());
                    pojemnoscTresc = "Motocykl";
                    break;
                case 4:
                    pojazd.setStawka(stawka.getStawkaMotorower());
                    pojemnoscTresc = "Motorower";
                    break;
                default:
                    pojazd.setStawka(3);
                    pojemnoscTresc = "Błąd";
                    break;
            }
            pojazd.setPojemnoscSilnika(pojemnoscTresc);
            pojazd.setOpis((String) row.get("opis"));
            pojazd.setId((int) row.get("id_pojazdu"));
            pojazdy.add(pojazd);
        }

        return pojazdy;
    }

    public Pojazd pobierzPojazdPoId(int id, int id_uzytkownika) {
        String sql = "SELECT * FROM POJAZDY where id_pojazdu=? and id_uzytkownika=? limit 1";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id, id_uzytkownika);

        for (Map row : rows) {
            Pojazd pojazd = new Pojazd();
            pojazd.setNazwa((String) (row.get("nazwa_pojazdu")));
            pojazd.setNumerRejestracyjny((String) row.get("numer_rejestracyjny"));
            pojazd.setId(id);
            int pojemnosc = (int) row.get("pojemnosc_silnika");
            String pojemnoscTresc;
            Stawka stawka = new Stawka();

            switch (pojemnosc) {
                case 1:
                    pojazd.setStawka(stawka.getStawkaDo900());
                    pojemnoscTresc = "poniżej 900cm^3";
                    break;
                case 2:
                    pojazd.setStawka(stawka.getStawkaPowyzej900());
                    pojemnoscTresc = "powyżej 900cm^3";
                    break;
                case 3:
                    pojazd.setStawka(stawka.getStawkaMotocykl());
                    pojemnoscTresc = "Motocykl";
                    break;
                case 4:
                    pojazd.setStawka(stawka.getStawkaMotorower());
                    pojemnoscTresc = "Motorower";
                    break;
                default:
                    pojazd.setStawka(3);
                    pojemnoscTresc = "Błąd";
                    break;
            }
            pojazd.setPojemnoscSilnika(pojemnoscTresc);
            pojazd.setOpis((String) row.get("opis"));
            return pojazd;
        }

        return null;
    }

    public boolean sprawdzPojazdy(int id_uzytkownika) {
        //sprawdza czy istnie pojazd uzytkownika
        String sql = "SELECT * FROM pojazdy where id_uzytkownika=? limit 1";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id_uzytkownika);
        for (Map row : rows) {
            return true;
        }
        return false;
    }

}
