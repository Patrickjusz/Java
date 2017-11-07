package kilometrowka.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kilometrowka.klasy.Data;
import kilometrowka.klasy.Firma;
import kilometrowka.klasy.Pojazd;
import kilometrowka.klasy.Pracownik;
import kilometrowka.klasy.Przejazd;
import kilometrowka.klasy.Stawka;
import kilometrowka.klasy.Trasa;
import org.springframework.jdbc.core.JdbcTemplate;

public class PrzejazdDao {

    private JdbcTemplate jdbcTemplate;

    public PrzejazdDao() {
        MysqlConfig mysql = new MysqlConfig();
        jdbcTemplate = new JdbcTemplate(mysql.getDataSource());
    }

    public List<Przejazd> pobierzPrzejazdy(int idPojazdu, int idFirmy, int id_uzytkownika, int miesiac, int rok) {
        //funkcja do wyświetlenia ewidencji
        String sql;
        if (idPojazdu == -1) {
            sql = "SELECT * FROM przejazdy where id_uzytkownika=" + id_uzytkownika + " and id_firmy=" + idFirmy + " group by id_pojazdu";
        } else {
            sql = "SELECT *, MONTH(data) as miesiac, YEAR(data) as rok FROM PRZEJAZDY having id_pojazdu=" + idPojazdu + " and id_firmy=" + idFirmy + " and id_uzytkownika=" + id_uzytkownika + " and rok=" + rok + " and miesiac=" + miesiac + " order by data";
        }
        List<Przejazd> przejazdy = new ArrayList<Przejazd>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Przejazd przejazd = new Przejazd();

            //
            PojazdDao dao = new PojazdDao();
            Pojazd pojazd = new Pojazd();
            int id;
            id = (int) row.get("id_pojazdu");
            pojazd = dao.pobierzPojazdPoId(id, id_uzytkownika);
            przejazd.setIdPojazdu(Integer.toString(pojazd.getId()));
            przejazd.setNazwaPojazdu(pojazd.getNazwa());

            //
            List<Data> daty = this.pobierzDaty(id, id_uzytkownika);
            przejazd.setMiesiacIRok(daty);
            //

            //
            FirmaDao daoFirma = new FirmaDao();
            Firma firma = new Firma();
            int id1;
            id1 = (int) row.get("id_firmy");
            firma = daoFirma.pobierzFirmePoId(id1, id_uzytkownika);
            przejazd.setIdFirmy(Integer.toString(firma.getId()));
            przejazd.setNazwaFirmy(firma.getNazwaFirmy());
            //

            //
            TrasaDao daoTrasa = new TrasaDao();
            Trasa trasa = new Trasa();
            int id2;
            id2 = (int) row.get("id_trasy");
            trasa = daoTrasa.pobierzTrasePoId(id2, id_uzytkownika);
            przejazd.setIdTrasy(Integer.toString(trasa.getId()));
            przejazd.setNazwaTrasy(trasa.getSkad() + "-" + trasa.getDokad());

            //--------------------------
            if (idPojazdu == -1) {
                przejazd.setOdlegloscTrasy(String.valueOf(dlugoscPrzejazdow(id_uzytkownika, id, idFirmy)));
                przejazd.setCena(String.valueOf(sumaPrzejazdow(id_uzytkownika, id, idFirmy)));
            } else {
                przejazd.setOdlegloscTrasy(trasa.getOdleglosc());
            }
            //

            //
            PracownikDao daoPracownik = new PracownikDao();
            Pracownik pracownik = new Pracownik();
            int id3;
            id3 = (int) row.get("id_pracownika");
            pracownik = daoPracownik.pobierzPracownikaPoId(id3, id_uzytkownika);
            przejazd.setIdPracownika(Integer.toString(pracownik.getId()));
            przejazd.setNazwaPracownika(pracownik.getImie() + ' ' + pracownik.getNazwisko());
            //

            przejazd.setCelWyjazdu((String) row.get("cel_wyjazdu"));

            przejazd.setAdnotacje((String) row.get("adnotacje").toString());
            przejazd.setData((String) row.get("data").toString());
            przejazd.setId((String) row.get("id_przejazdu").toString());
            przejazdy.add(przejazd);
        }
        if (przejazdy.isEmpty()) {
            return null;
        }
        return przejazdy;

    }

    public List<Przejazd> pobierzPrzejazdy(int id_uzytkownika, int limit) {

        String sql = "SELECT * FROM PRZEJAZDY where id_uzytkownika=? order by data limit ?,10";

        List<Przejazd> przejazdy = new ArrayList<Przejazd>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id_uzytkownika, limit);
        if (rows.isEmpty()) {
            return null;
        }
        for (Map row : rows) {
            Przejazd przejazd = new Przejazd();

            //
            PojazdDao dao = new PojazdDao();
            Pojazd pojazd = new Pojazd();
            int id;
            id = (int) row.get("id_pojazdu");
            pojazd = dao.pobierzPojazdPoId(id, id_uzytkownika);
            przejazd.setIdPojazdu(Integer.toString(pojazd.getId()));
            przejazd.setNazwaPojazdu(pojazd.getNazwa());
            //

            //
            FirmaDao daoFirma = new FirmaDao();
            Firma firma = new Firma();
            int id1;
            id1 = (int) row.get("id_firmy");
            firma = daoFirma.pobierzFirmePoId(id1, id_uzytkownika);
            przejazd.setIdFirmy(Integer.toString(firma.getId()));
            przejazd.setNazwaFirmy(firma.getNazwaFirmy());
            //

            //
            TrasaDao daoTrasa = new TrasaDao();
            Trasa trasa = new Trasa();
            int id2;
            id2 = (int) row.get("id_trasy");
            trasa = daoTrasa.pobierzTrasePoId(id2, id_uzytkownika);
            przejazd.setIdTrasy(Integer.toString(trasa.getId()));
            przejazd.setNazwaTrasy(trasa.getNazwa());
            //

            //
            PracownikDao daoPracownik = new PracownikDao();
            Pracownik pracownik = new Pracownik();
            int id3;
            id3 = (int) row.get("id_pracownika");
            pracownik = daoPracownik.pobierzPracownikaPoId(id3, id_uzytkownika);
            przejazd.setIdPracownika(Integer.toString(pracownik.getId()));
            przejazd.setNazwaPracownika(pracownik.getImie() + ' ' + pracownik.getNazwisko());
            //

            przejazd.setCelWyjazdu((String) row.get("cel_wyjazdu"));

            przejazd.setAdnotacje((String) row.get("adnotacje").toString());
            przejazd.setData((String) row.get("data").toString());
            przejazd.setId((String) row.get("id_przejazdu").toString());
            przejazdy.add(przejazd);
        }

        if (przejazdy.isEmpty()) {
            return null;
        }
        return przejazdy;
    }

    public Przejazd pobierzPrzejazdPoId(int idParm, int id_uzytkownika) {
        String sql = "SELECT * FROM PRZEJAZDY where id_przejazdu=? and id_uzytkownika=? order by data limit 1";

        //List<Przejazd> przejazdy = new ArrayList<Przejazd>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, idParm, id_uzytkownika);

        for (Map row : rows) {
            Przejazd przejazd = new Przejazd();

            //
            PojazdDao dao = new PojazdDao();
            Pojazd pojazd = new Pojazd();
            int id;
            id = (int) row.get("id_pojazdu");
            pojazd = dao.pobierzPojazdPoId(id, id_uzytkownika);
            przejazd.setIdPojazdu(Integer.toString(pojazd.getId()));
            przejazd.setNazwaPojazdu(pojazd.getNazwa());
            //

            //
            FirmaDao daoFirma = new FirmaDao();
            Firma firma = new Firma();
            int id1;
            id1 = (int) row.get("id_firmy");
            firma = daoFirma.pobierzFirmePoId(id1, id_uzytkownika);
            przejazd.setIdFirmy(Integer.toString(firma.getId()));
            przejazd.setNazwaFirmy(firma.getNazwaFirmy());
            //

            //
            TrasaDao daoTrasa = new TrasaDao();
            Trasa trasa = new Trasa();
            int id2;
            id2 = (int) row.get("id_trasy");
            trasa = daoTrasa.pobierzTrasePoId(id2, id_uzytkownika);
            przejazd.setIdTrasy(Integer.toString(trasa.getId()));
            przejazd.setNazwaTrasy(trasa.getNazwa());
            //

            //
            PracownikDao daoPracownik = new PracownikDao();
            Pracownik pracownik = new Pracownik();
            int id3;
            id3 = (int) row.get("id_pracownika");
            pracownik = daoPracownik.pobierzPracownikaPoId(id3, id_uzytkownika);
            przejazd.setIdPracownika(Integer.toString(pracownik.getId()));
            przejazd.setNazwaPracownika(pracownik.getImie() + ' ' + pracownik.getNazwisko());
            //

            przejazd.setCelWyjazdu((String) row.get("cel_wyjazdu"));

            przejazd.setAdnotacje((String) row.get("adnotacje").toString());
            przejazd.setData((String) row.get("data").toString());
            przejazd.setId(String.valueOf(idParm));

            return przejazd;
        }

        return null;
    }

    public void zapiszPrzejazd(Przejazd przejazd, int id_uzytkownika) {
        String sql = "INSERT INTO przejazdy (id_pojazdu, id_trasy, id_firmy, id_pracownika, cel_wyjazdu, adnotacje, data, id_uzytkownika)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, przejazd.getIdPojazdu(), przejazd.getIdTrasy(), przejazd.getIdFirmy(), przejazd.getIdPracownika(), przejazd.getCelWyjazdu(), przejazd.getAdnotacje(), przejazd.getData(), id_uzytkownika);
    }

    public void aktualizujPrzejazd(int id, Przejazd przejazd, int id_uzytkownika) {
        String sql = "UPDATE PRZEJAZDY SET id_pojazdu = ?, id_trasy = ?, id_firmy = ?, id_pracownika = ?, cel_wyjazdu = ?, adnotacje = ?, data = ? WHERE id_przejazdu = ? and id_uzytkownika=?";

        jdbcTemplate.update(sql, przejazd.getIdPojazdu(), przejazd.getIdTrasy(), przejazd.getIdFirmy(), przejazd.getIdPracownika(), przejazd.getCelWyjazdu(), przejazd.getAdnotacje(), przejazd.getData(), id, id_uzytkownika);
    }

    public double pobierzOdleglosc(int idPojazdu, int idFirmy, int id_uzytkownika) {
        //przejazd+=stawka*dlugosc trasy
        String sql = "SELECT * FROM PRZEJAZDY where id_pojazdu=" + idPojazdu + " and id_uzytkownika=" + id_uzytkownika + " and id_firmy=" + idFirmy;;

        List<Przejazd> przejazdy = new ArrayList<Przejazd>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        Pojazd pojazd = new Pojazd();
        PojazdDao daoPojazd = new PojazdDao();
        pojazd = daoPojazd.pobierzPojazdPoId(idPojazdu, id_uzytkownika);
        int pojemnosc = 1;
        Stawka stawka = new Stawka();
        double koszt = 0.0;
        switch (pojemnosc) {
            case 1:
                koszt = stawka.getStawkaDo900();

                break;
            case 2:
                koszt = stawka.getStawkaPowyzej900();

                break;
            case 3:
                koszt = stawka.getStawkaMotocykl();

                break;
            case 4:
                koszt = stawka.getStawkaMotorower();

                break;
            default:
                koszt = 0.0;
                break;
        }

        double suma = 0;
        for (Map row : rows) {

            TrasaDao daoTrasa = new TrasaDao();
            Trasa trasa = new Trasa();
            trasa = daoTrasa.pobierzTrasePoId((int) row.get("id_trasy"), id_uzytkownika);

            suma += koszt * Integer.parseInt(trasa.getOdleglosc());
        }
        return suma;
    }

    public void usunPrzejazd(int id, int id_uzytkownika) {
        String sql = "DELETE FROM przejazdy WHERE id_przejazdu=? and id_uzytkownika=? limit 1";
        jdbcTemplate.update(sql, id, id_uzytkownika);
    }

    public void usunWszystkiePrzejazdy(int typ, int id_uzytkownika) {
        /* typ=1 pojazdy, typ=2 trasy, typ=3 firmy, typ4 pracownicy*/
        String sql;
        switch (typ) {
            case 1:
                sql = "DELETE FROM przejazdy where id_pojazdu=? and id_uzytkownika=?";
                jdbcTemplate.update(sql, typ, id_uzytkownika);
                break;
            case 2:
                sql = "DELETE FROM przejazdy where id_trasy=? and id_uzytkownika=?";
                jdbcTemplate.update(sql, typ, id_uzytkownika);
                break;
            case 3:
                sql = "DELETE FROM przejazdy where id_firmy=? and id_uzytkownika=?";
                jdbcTemplate.update(sql, typ, id_uzytkownika);
                break;
            case 4:
                sql = "DELETE FROM przejazdy where id_pracownika=? and id_uzytkownika=?";
                jdbcTemplate.update(sql, typ, id_uzytkownika);
                break;
        }
    }

    public boolean sprawdzPrzejazdy(int id_uzytkownika) {
        //sprawdza czy mozna wyświetlić ewidencje
        String sql = "SELECT * FROM PRZEJAZDY where id_uzytkownika=? limit 1";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id_uzytkownika);
        for (Map row : rows) {
            return true;
        }
        return false;
    }

    public boolean sprawdzWarunkiDodania(int id_uzytkownika) {
        FirmaDao firma = new FirmaDao();
        PojazdDao pojazd = new PojazdDao();
        PracownikDao pracownik = new PracownikDao();
        TrasaDao trasa = new TrasaDao();
        if (firma.sprawdzFirmy(id_uzytkownika) && pojazd.sprawdzPojazdy(id_uzytkownika) && pracownik.sprawdzPracownikow(id_uzytkownika) && trasa.sprawdzTrasy(id_uzytkownika)) {
            //istnieje firma, pojazd, pracownik i trasa wiec mozna dodac przejazd
            return true;
        }
        return false;
    }

    public List<Data> pobierzDaty(int idPojazdu, int id_uzytkownika) {
        String sql = "SELECT MONTH(data) as miesiac, YEAR(data) as rok from przejazdy where id_pojazdu=? and id_uzytkownika=? group by miesiac, rok";
        List<Data> daty = new ArrayList<Data>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, idPojazdu, id_uzytkownika);
        for (Map row : rows) {
            Data data = new Data();
            data.setRok((int) row.get("rok"));
            data.setMiesiac((int) row.get("miesiac"));
            daty.add(data);
        }

        return daty;
    }

    public String zamienMiesiac(int miesiac) {
        switch (miesiac) {
            case 1:
                return "styczeń";

            case 2:
                return "luty";
            case 3:
                return "marzec";

            case 4:
                return "kwiecień";
            case 5:
                return "maj";
            case 6:
                return "czerwiec";
            case 7:
                return "lipiec";
            case 8:
                return "sierpień";
            case 9:
                return "wrzesień";
            case 10:
                return "październik";
            case 11:
                return "listopad";
            case 12:
                return "grudzień";
        }
        return "-";
    }

    public int pobierzIloscPrzejazdow(int id_uzytkownika, int limit) {

        String sql = "SELECT * FROM PRZEJAZDY where id_uzytkownika=? order by data limit ?,10";
        int ilosc = 0;
        List<Przejazd> przejazdy = new ArrayList<Przejazd>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id_uzytkownika, limit);

        for (Map row : rows) {
            ilosc++;
        }

        return ilosc;
    }

    public int dlugoscPrzejazdow(int id_uzytkownika, int id_pojazdu, int id_firmy) {

        String sql = "SELECT id_trasy FROM przejazdy WHERE id_uzytkownika=? and id_pojazdu=? and id_firmy=?";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id_uzytkownika, id_pojazdu, id_firmy);
        int sumaKm = 0;
        for (Map row : rows) {
            int id1 = (int) row.get("id_trasy");
            TrasaDao dao = new TrasaDao();
            Trasa trasa = new Trasa();
            trasa = dao.pobierzTrasePoId(id1, id_uzytkownika);
            sumaKm += Integer.parseInt(trasa.getOdleglosc());
        }
        return sumaKm;
    }

    public double sumaPrzejazdow(int id_uzytkownika, int id_pojazdu, int id_firmy) {

        String sql = "SELECT id_trasy, id_pojazdu FROM przejazdy WHERE id_uzytkownika=? and id_pojazdu=? and id_firmy=?";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id_uzytkownika, id_pojazdu, id_firmy);
        double sumaKm = 0;
        for (Map row : rows) {
            int id1 = (int) row.get("id_trasy");
            int id2 = (int) row.get("id_pojazdu");

            TrasaDao dao = new TrasaDao();
            Trasa trasa = new Trasa();
            trasa = dao.pobierzTrasePoId(id1, id_uzytkownika);

            PojazdDao dao2 = new PojazdDao();
            Pojazd pojazd = new Pojazd();
            pojazd = dao2.pobierzPojazdPoId(id_pojazdu, id_uzytkownika);
            Stawka stawka = new Stawka();
            sumaKm += stawka.policzStawke(pojazd.getStawka(), Double.parseDouble(trasa.getOdleglosc()));
        }

        return Math.round(sumaKm * 100.0) / 100.0;
    }

}
