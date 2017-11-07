/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kilometrowka.kontroler;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import kilometrowka.DAO.FirmaDao;
import kilometrowka.DAO.LogDao;
import kilometrowka.DAO.LogowanieDao;
import kilometrowka.DAO.PojazdDao;
import kilometrowka.DAO.PracownikDao;
import kilometrowka.DAO.PrzejazdDao;
import kilometrowka.DAO.TrasaDao;
import kilometrowka.klasy.Firma;
import kilometrowka.klasy.Logowanie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import kilometrowka.klasy.Pojazd;
import kilometrowka.klasy.Pracownik;
import kilometrowka.klasy.Przejazd;
import kilometrowka.klasy.Stawka;
import kilometrowka.klasy.Trasa;
import static org.jsoup.helper.StringUtil.isNumeric;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("session")
public class DomyslnyKontroler {

    private Logowanie log = new Logowanie();

    //logowanie
    @RequestMapping(value = "/logowanie", method = RequestMethod.GET)
    public String logowanie(ModelMap map, @RequestParam(value = "reg", required = false) String reg) {
        if (log.isZalogowany()) {
            return "redirect:firmy";
        }
        Logowanie dodajLogowanieForm = new Logowanie();
        map.addAttribute("dodajLogowanieForm", dodajLogowanieForm);

        if (reg != null && isNumeric(reg) && Integer.parseInt(reg) == 1) {
            map.addAttribute("reg", 1);
        }
        return "formularze/logowanie";
    }

    //test
    @RequestMapping(value = "/raport.txt",
            method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String pobierzRaport(HttpServletResponse response, @RequestParam(value = "idFirmy", required = true) String idFirmy, @RequestParam(value = "idPojazdu", required = true) String idPojazdu, @RequestParam(value = "miesiac", required = true) String miesiac, @RequestParam(value = "rok", required = true) String rok) {

        if (!log.isZalogowany()) {
            try {
                response.sendRedirect("logowanie");

            } catch (IOException ex) {
            }
            return "Błędne parametry!";
        }

        String odpowiedz = "";

        if (isNumeric(idPojazdu) && isNumeric(idFirmy) && isNumeric(miesiac) && isNumeric(rok)) {
            int id_pojazdu = Integer.parseInt(idPojazdu);
            int id_firmy = Integer.parseInt(idFirmy);

            FirmaDao daoFirma = new FirmaDao();
            Firma firma = daoFirma.pobierzFirmePoId(id_firmy, log.getId());
            if (firma == null) {
                try {
                    response.sendRedirect("logowanie");

                } catch (IOException ex) {
                }
                return "Błędne parametry!";
            }

            PojazdDao daoPojazd = new PojazdDao();
            Pojazd pojazd = daoPojazd.pobierzPojazdPoId(id_pojazdu, log.getId());
            if (pojazd == null) {
                try {
                    response.sendRedirect("logowanie");

                } catch (IOException ex) {
                }
                return "Błędne parametry!";
            }
            Double cena = Double.valueOf(pojazd.getStawka());
            Stawka stawka = new Stawka();
            odpowiedz += "Firma:" + "\n";
            odpowiedz += firma.getNazwaFirmy() + "\n";
            odpowiedz += firma.getNip() + "\n";
            odpowiedz += firma.getRegon() + "\n\n";

            odpowiedz += "Pojazd:" + "\n";
            odpowiedz += pojazd.getNazwa() + "\n";
            odpowiedz += pojazd.getNumerRejestracyjny() + "\n";
            odpowiedz += pojazd.getPojemnoscSilnika() + "\n\n";

            PrzejazdDao dao = new PrzejazdDao();
            List<Przejazd> przejazdy = dao.pobierzPrzejazdy(id_pojazdu, id_firmy, log.getId(), Integer.parseInt(miesiac), Integer.parseInt(rok));
            if (przejazdy == null) {
                try {
                    response.sendRedirect("logowanie");

                } catch (IOException ex) {
                }
                return "Błędne parametry!";
            }
            int i = 1;
            double sumaZl = 0;
            double sumaKm = 0;
            odpowiedz += "LP | Data | Trasa | Pracownik | Stawka | Przejechane km | Wartość | Cel wyjazdu\n"
                    + "--------------------------------------------------------------------------------\n";
            for (Przejazd przejazd : przejazdy) {
                odpowiedz += String.valueOf(i) + " | ";
                odpowiedz += przejazd.getData() + " | ";
                odpowiedz += przejazd.getNazwaTrasy() + " | ";
                odpowiedz += przejazd.getNazwaPracownika() + " | ";
                odpowiedz += cena + " | ";
                odpowiedz += przejazd.getOdlegloscTrasy() + "km | ";
                odpowiedz += Math.round(stawka.policzStawke(cena, Double.parseDouble(przejazd.getOdlegloscTrasy())) * 100.0) / 100.0 + "zł | ";
                odpowiedz += przejazd.getCelWyjazdu() + "\n";

                sumaZl += stawka.policzStawke(cena, Double.parseDouble(przejazd.getOdlegloscTrasy()));
                sumaKm += Double.parseDouble(przejazd.getOdlegloscTrasy());
                i++;
            }
            odpowiedz += "--------------------------------------------------------------------------------\n\n";
            odpowiedz += "Suma przejechanych kilometrów: " + sumaKm + "km\n";
            odpowiedz += "Wartość w złotówkach: " + Math.round(sumaZl * 100.0) / 100.0 + "zł";
        } else {
            try {
                response.sendRedirect("blad?blad=3");
            } catch (IOException ex) {
                Logger.getLogger(DomyslnyKontroler.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "Błędne parametry!";
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment");
        response.setHeader("filename", "raport.txt");

        return odpowiedz;
    }
    //

    @RequestMapping(value = "/rejestracja", method = RequestMethod.GET)
    public String rejestracja(ModelMap map) {
        if (log.isZalogowany()) {
            return "redirect:firmy";
        }

        Logowanie dodajLogowanieForm = new Logowanie();
        map.addAttribute("dodajLogowanieForm", dodajLogowanieForm);
        return "formularze/rejestracja";
    }

    @RequestMapping(value = "/rejestracja", method = RequestMethod.POST)
    public String processRegistration3(@Valid @ModelAttribute("dodajLogowanieForm") Logowanie logowanie, BindingResult result,
            ModelMap map, HttpServletRequest request) {
        if (log.isZalogowany()) {
            return "redirect:firmy";
        }
        LogDao daoLog = new LogDao();
        if (result.hasErrors()) {
            daoLog.dodajZdarzenie(3, request.getRemoteAddr(), request.getHeader("User-Agent"), "Nieudana próba rejestracji: " + logowanie.getLogin() + ", " + logowanie.getEmail());
            return "formularze/rejestracja";
        }
        LogowanieDao dao = new LogowanieDao();
        if (!dao.sprawdzDostepnoscLoginu(logowanie.getLogin())) {
            if (logowanie.getHaslo().equals(logowanie.getHaslo2())) {
                dao.zarejestrujUzytkownika(logowanie);
                daoLog.dodajZdarzenie(4, request.getRemoteAddr(), request.getHeader("User-Agent"), "Zarejestrował się: " + logowanie.getLogin() + ", " + logowanie.getEmail());
            } else {
                map.addAttribute("reg", 2);
                return "formularze/rejestracja";
            }
        } else {
            map.addAttribute("reg", 1);
            return "formularze/rejestracja";
        }

        return "redirect:logowanie?reg=1";

    }
    //

    @RequestMapping(value = "/logowanie", method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute("dodajLogowanieForm") Logowanie logowanie,
            ModelMap map, HttpServletRequest request) {
        if (log.isZalogowany()) {
            return "redirect:firmy";
        }
        LogowanieDao dao = new LogowanieDao();
        Logowanie zalogowano = dao.zaloguj(logowanie.getLogin(), logowanie.getHaslo());
        LogDao daoLog = new LogDao();
        if (zalogowano != null && zalogowano.isZalogowany()) {
            log.setId(zalogowano.getId());
            log.setLogin(zalogowano.getLogin());
            log.setZalogowany(true);
            map.addAttribute("sesja", log);

            daoLog.dodajZdarzenie(1, request.getRemoteAddr(), request.getHeader("User-Agent"), "Zalogował się: " + logowanie.getLogin());
            return "redirect:firmy";
        } //zalogowano!
        else {
            log.setId(-1);
            log.setLogin(null);
            log.setZalogowany(false);
            daoLog.dodajZdarzenie(2, request.getRemoteAddr(), request.getHeader("User-Agent"), "Nie udane logowanie: " + logowanie.getLogin());
            map.addAttribute("logowanie", 1);
            return "formularze/logowanie";
        }

    }

    @RequestMapping(value = "/wyloguj", method = RequestMethod.GET)
    public String wyloguj(ModelMap map) {
        log.setId(-1);
        log.setLogin(null);
        log.setZalogowany(false);
        return "redirect:logowanie";
    }
    //

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map) {
        if (log.isZalogowany()) {
            return "redirect:firmy";
        }
        return "index";
    }

    @RequestMapping(value = "/blad", method = RequestMethod.GET)
    public String blad(ModelMap map, @RequestParam(value = "blad", required = true) String blad) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }

        if (blad != null && !isNumeric(blad)) {
            return "redirect:blad?blad=3";
        }

        switch (Integer.parseInt(blad)) {
            case 1:
                map.addAttribute("trescBledu", "Nie posiadasz żadnych przejazdów do wyświetlenia!");
                break;
            case 2:
                map.addAttribute("trescBledu", "Aby dodać przejazd musisz mieć conajmiej jedeną firmę, pojazd, trasę i pracownika.");
                break;
            case 3:
                map.addAttribute("trescBledu", "Nieprawidłowe parametry zapytania.");
                break;
            default:
                map.addAttribute("trescBledu", "tresc");
        }

        return "blad";
    }

    @RequestMapping(value = "/stronaGlowna", method = RequestMethod.GET)
    public String stronaGlowna(ModelMap map) {
        if (log.isZalogowany()) {
            return "redirect:firmy";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/przejazdy", method = RequestMethod.GET)
    public String przejazdy(ModelMap map, @RequestParam(value = "nr", required = false) String nr) {

        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }

        if (nr == null) {
            return "redirect:przejazdy?nr=0";
        }

        if (nr != null && !isNumeric(nr)) {
            return "redirect:blad?blad=3";
        }

        PrzejazdDao dao = new PrzejazdDao();
        if (!dao.sprawdzWarunkiDodania(log.getId())) {
            return "redirect:blad?blad=2";
        }

        List<Przejazd> przejazd = dao.pobierzPrzejazdy(log.getId(), Integer.parseInt(nr) * 10);
        map.addAttribute("przejazdy", przejazd);

        int iloscPrzejazdowNastepnych = dao.pobierzIloscPrzejazdow(log.getId(), Integer.parseInt(nr) * 10 + 10);
        map.addAttribute("iloscNastepnych", iloscPrzejazdowNastepnych);
        if (Integer.parseInt(nr) > 0) {
            map.addAttribute("iloscPoprzednich", 1);
        } else {
            map.addAttribute("iloscPoprzednich", 0);
        }
        map.addAttribute("paginacja", 1);
        map.addAttribute("wprzod", Integer.parseInt(nr) + 1);
        map.addAttribute("wtyl", Integer.parseInt(nr) - 1);
        return "przejazdy";
    }

    @RequestMapping(value = "/trasy", method = RequestMethod.GET)
    public String trasy(ModelMap map) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        TrasaDao dao = new TrasaDao();
        List<Trasa> trasa = dao.pobierzTrasy(log.getId());
        map.addAttribute("trasy", trasa);
        return "trasy";
    }

    @RequestMapping(value = "/pojazdy", method = RequestMethod.GET)
    public String pojazdy(ModelMap map) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        PojazdDao dao = new PojazdDao();
        List<Pojazd> pojazd = dao.pobierzPojazdy(log.getId());
        map.addAttribute("pojazdy", pojazd);
        return "pojazdy";
    }

    @RequestMapping(value = "/firmy", method = RequestMethod.GET)
    public String firmy(ModelMap map) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        FirmaDao dao = new FirmaDao();
        List<Firma> firma = dao.pobierzFirmy(log.getId());
        map.addAttribute("firmy", firma);
        return "firmy";
    }

    @RequestMapping(value = "/pracownicy", method = RequestMethod.GET)
    public String pracownicy(ModelMap map) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        PracownikDao dao = new PracownikDao();
        List<Pracownik> pracownik = dao.pobierzPracownikow(log.getId());
        map.addAttribute("pracownicy", pracownik);
        return "pracownicy";
    }

    @RequestMapping(value = "/ewidencjaNr", method = RequestMethod.GET)
    public String ewidencjaNr(ModelMap map, @RequestParam(value = "idFirmy", required = true) String idFirmy, @RequestParam(value = "idPojazdu", required = true) String idPojazdu, @RequestParam(value = "miesiac", required = true) String miesiac, @RequestParam(value = "rok", required = true) String rok) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        Przejazd dodajPrzejazdForm = new Przejazd();
        map.addAttribute("dodajPrzejazdForm", dodajPrzejazdForm);

        if (isNumeric(idPojazdu) && isNumeric(idFirmy) && isNumeric(miesiac) && isNumeric(rok)) {
            int id_pojazdu = Integer.parseInt(idPojazdu);
            int id_firmy = Integer.parseInt(idFirmy);

            PrzejazdDao dao = new PrzejazdDao();
            List<Przejazd> przejazd = dao.pobierzPrzejazdy(id_pojazdu, id_firmy, log.getId(), Integer.parseInt(miesiac), Integer.parseInt(rok));
            map.addAttribute("przejazdy", przejazd);

            FirmaDao daoFirma = new FirmaDao();
            Firma firma = daoFirma.pobierzFirmePoId(id_firmy, log.getId());
            map.addAttribute("firma", firma);

            PojazdDao daoPojazd = new PojazdDao();
            Pojazd pojazd = daoPojazd.pobierzPojazdPoId(id_pojazdu, log.getId());
            map.addAttribute("pojazd", pojazd);

            map.addAttribute("miesiac", dao.zamienMiesiac(Integer.parseInt(miesiac)));
            map.addAttribute("miesiacLiczbowo", Integer.parseInt(miesiac));
            map.addAttribute("rok", rok);
        } else {
            return "redirect:blad?blad=3";
        }
        return "ewidencjaNr";
    }

    @RequestMapping(value = "/ewidencja", method = RequestMethod.GET)
    public String ewidencja(ModelMap map) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        PrzejazdDao iloscPrzejazdow = new PrzejazdDao();
        if (!iloscPrzejazdow.sprawdzPrzejazdy(log.getId())) {
            return "redirect:blad?blad=1";
        }

        Przejazd ewidencjaForm = new Przejazd();

        map.addAttribute("ewidencjaForm", ewidencjaForm);

        Map<String, String> listaFirm = new LinkedHashMap<String, String>();

        FirmaDao daoFirma = new FirmaDao();
        List<Firma> firma = daoFirma.pobierzFirmy(log.getId());

        for (Firma i : firma) {
            listaFirm.put(Integer.toString(i.getId()), i.getNazwaFirmy());
        }
        map.addAttribute("listaFirm", listaFirm);

        Map<String, String> listaPojazdow = new LinkedHashMap<String, String>();

        PojazdDao dao = new PojazdDao();
        List<Pojazd> pojazd = dao.pobierzPojazdy(log.getId());

        for (Pojazd i : pojazd) {
            listaPojazdow.put(Integer.toString(i.getId()), i.getNazwa());
        }
        map.addAttribute("listaPojazdow", listaPojazdow);
        map.addAttribute("czyPost", 0);
        return "ewidencja";
    }

    @RequestMapping(value = "/ewidencja", method = RequestMethod.POST)
    public String ewidencja(ModelMap map, @RequestParam(value = "idFirmy", required = true) String idFirmy) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }

        Przejazd ewidencjaForm = new Przejazd();
        map.addAttribute("ewidencjaForm", ewidencjaForm);

        Map<String, String> listaFirm = new LinkedHashMap<String, String>();

        FirmaDao daoFirma = new FirmaDao();
        List<Firma> firma = daoFirma.pobierzFirmy(log.getId());

        for (Firma i : firma) {
            listaFirm.put(Integer.toString(i.getId()), i.getNazwaFirmy());
        }
        map.addAttribute("listaFirm", listaFirm);

        Map<String, String> listaPojazdow = new LinkedHashMap<String, String>();

        PojazdDao dao = new PojazdDao();
        List<Pojazd> pojazd = dao.pobierzPojazdy(log.getId());

        for (Pojazd i : pojazd) {
            listaPojazdow.put(Integer.toString(i.getId()), i.getNazwa());
        }
        map.addAttribute("listaPojazdow", listaPojazdow);

        //  map.addAttribute("hej", idFirmy);
        PrzejazdDao dao1 = new PrzejazdDao();
        if (isNumeric(idFirmy)) {
            List<Przejazd> przejazd = dao1.pobierzPrzejazdy(-1, Integer.parseInt(idFirmy), log.getId(), -1, -1);
            map.addAttribute("przejazdy", przejazd);
        } else {
            return "redirect:blad?blad=3";
        }

        //PrzejazdDao dao2 = new PrzejazdDao();
        map.addAttribute("czyPost", 1);
        return "ewidencja";
    }

    //-- DODAWANIE DANYCH----------------------------------------------------------
// POJAZD
    @RequestMapping(value = "/dodajPojazd", method = RequestMethod.GET)
    public String dodajPojazd(ModelMap map) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        Pojazd dodajPojazdForm = new Pojazd();
        map.addAttribute("dodajPojazdForm", dodajPojazdForm);

        return "formularze/dodajPojazd";
    }

    @RequestMapping(value = "/dodajPojazd", method = RequestMethod.POST)
    public String processRegistration(@Valid
            @ModelAttribute("dodajPojazdForm") Pojazd pojazd,
            BindingResult result, ModelMap map) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        if (result.hasErrors()) {
            return "formularze/dodajPojazd";
        }
        /*
         1 Samochod os. poj. poniżej 900cm^3
         2 Samochod os. poj. powyżej 900cm^3
         3 Motocykl
         4 Motorower
         */
        int pojemnosc;
        pojemnosc = Integer.parseInt(pojazd.getPojemnoscSilnika());
        if (pojemnosc == 1 | pojemnosc == 2 | pojemnosc == 3 | pojemnosc == 4) {
            PojazdDao dao = new PojazdDao();
            dao.zapiszPojazd(pojazd, log.getId());
        } else {
            //ERRPR
        }

        switch (pojemnosc) {
            case 1:

                pojazd.setPojemnoscSilnika("poniżej 900cm^3");
                break;
            case 2:

                pojazd.setPojemnoscSilnika("powyżej 900cm^3");
                break;
            case 3:

                pojazd.setPojemnoscSilnika("Motocykl");
                break;
            case 4:

                pojazd.setPojemnoscSilnika("Motorower");
                break;
            default:
                pojazd.setPojemnoscSilnika("-");
                break;
        }

        return "formularze/stronyWynikow/pojazdWynik";
    }

    // FIRMY
    @RequestMapping(value = "/dodajFirme", method = RequestMethod.GET)
    public String dodajFirme(ModelMap map) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        Firma dodajFirmeForm = new Firma();
        map.addAttribute("dodajFirmeForm", dodajFirmeForm);

        return "formularze/dodajFirme";
    }

    @RequestMapping(value = "/dodajFirme", method = RequestMethod.POST)
    public String processRegistration(@Valid
            @ModelAttribute("dodajFirmeForm") Firma firma, BindingResult result,
            ModelMap map) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        if (result.hasErrors()) {
            return "formularze/dodajFirme";
        }

        FirmaDao dao = new FirmaDao();
        dao.zapiszFirme(firma, log.getId());

        return "formularze/stronyWynikow/firmaWynik";
    }

    //EDYTUJ FIRME
    @RequestMapping(value = "/edytujFirme", method = RequestMethod.GET)
    public String edytujFirme(ModelMap map, @RequestParam(value = "id", required = true) String id) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        FirmaDao dao = new FirmaDao();
        if (isNumeric(id)) {
            Firma dodajFirmeForm = dao.pobierzFirmePoId(Integer.parseInt(id), log.getId());

            map.addAttribute("edycja", 1);
            map.addAttribute("dodajFirmeForm", dodajFirmeForm);
        } else {
            return "redirect:blad?blad=3";
        }
        return "formularze/dodajFirme";
    }

    @RequestMapping(value = "/edytujFirme", method = RequestMethod.POST)
    public String processRegistration2(@Valid
            @ModelAttribute("dodajFirmeForm") Firma firma, BindingResult result,
            ModelMap map) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        if (result.hasErrors()) {
            map.addAttribute("edycja", 1);
            return "formularze/dodajFirme";
        }
        FirmaDao dao = new FirmaDao();
        dao.aktualizujFirme(firma.getId(), firma, log.getId());

        return "formularze/stronyWynikow/firmaWynik";
    }

    //
    //EDYTUJ TRASY
    @RequestMapping(value = "/edytujTrase", method = RequestMethod.GET)
    public String edytujTrasy(ModelMap map, @RequestParam(value = "id", required = true) String id) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        if (isNumeric(id)) {
            TrasaDao dao = new TrasaDao();
            Trasa dodajTraseForm = dao.pobierzTrasePoId(Integer.parseInt(id), log.getId());
            map.addAttribute("edycja", 1);
            map.addAttribute("dodajTraseForm", dodajTraseForm);
        } else {
            return "redirect:blad?blad=3";
        }
        return "formularze/dodajTrase";
    }

    @RequestMapping(value = "/edytujTrase", method = RequestMethod.POST)
    public String processRegistration2(@Valid
            @ModelAttribute("dodajTraseForm") Trasa trasa, BindingResult result,
            ModelMap map) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        if (result.hasErrors()) {
            map.addAttribute("edycja", 1);
            return "formularze/dodajTrase";
        }
        TrasaDao dao = new TrasaDao();
        dao.aktualizujTrase(trasa.getId(), trasa, log.getId());

        return "formularze/stronyWynikow/trasaWynik";
    }

    //
    //EDYTUJ POJAZD
    @RequestMapping(value = "/edytujPojazd", method = RequestMethod.GET)
    public String edytujPojazd(ModelMap map, @RequestParam(value = "id", required = true) String id) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        if (isNumeric(id)) {
            PojazdDao dao = new PojazdDao();
            Pojazd dodajPojazdForm = dao.pobierzPojazdPoId(Integer.parseInt(id), log.getId());
            map.addAttribute("edycja", 1);
            String pojemnosc = dodajPojazdForm.getPojemnoscSilnika();
            switch (pojemnosc) {
                case "poniżej 900cm^3":

                    pojemnosc = String.valueOf(1);
                    break;
                case "powyżej 900cm^3":

                    pojemnosc = String.valueOf(2);
                    break;
                case "Motocykl":

                    pojemnosc = String.valueOf(3);
                    break;
                case "Motorower":

                    pojemnosc = String.valueOf(4);
                    break;
                default:

                    pojemnosc = String.valueOf(0);
                    break;
            }

            dodajPojazdForm.setPojemnoscSilnika(pojemnosc);
            map.addAttribute("dodajPojazdForm", dodajPojazdForm);
        } else {
            return "redirect:blad?blad=3";
        }
        return "formularze/dodajPojazd";
    }

    @RequestMapping(value = "/edytujPojazd", method = RequestMethod.POST)
    public String processRegistration2(@Valid
            @ModelAttribute("dodajPojazdForm") Pojazd pojazd, BindingResult result,
            ModelMap map) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        if (result.hasErrors()) {
            map.addAttribute("edycja", 1);
            return "formularze/dodajPojazd";
        }
        PojazdDao dao = new PojazdDao();
        dao.aktualizujPojazd(pojazd.getId(), pojazd, log.getId());
        //

        String pojemnosc = pojazd.getPojemnoscSilnika();
        switch (pojemnosc) {
            case "1":

                pojazd.setPojemnoscSilnika("poniżej 900cm^3");

                break;
            case "2":
                pojazd.setPojemnoscSilnika("powyżej 900cm^3");

                break;
            case "3":
                pojazd.setPojemnoscSilnika("Motocykl");

                break;
            case "4":
                pojazd.setPojemnoscSilnika("Motorower");

                break;
            default:
                pojazd.setPojemnoscSilnika("-");
                break;
        }

        //
        return "formularze/stronyWynikow/pojazdWynik";
    }

    //
    //EDYTUJ PRACOWNIKA
    @RequestMapping(value = "/edytujPracownika", method = RequestMethod.GET)
    public String edytujPracownika(ModelMap map, @RequestParam(value = "id", required = true) String id) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        if (isNumeric(id)) {
            PracownikDao dao = new PracownikDao();
            Pracownik dodajPracownikaForm = dao.pobierzPracownikaPoId(Integer.parseInt(id), log.getId());
            map.addAttribute("edycja", 1);
            map.addAttribute("dodajPracownikaForm", dodajPracownikaForm);
        } else {
            return "redirect:blad?blad=3";
        }
        return "formularze/dodajPracownika";
    }

    @RequestMapping(value = "/edytujPracownika", method = RequestMethod.POST)
    public String processRegistration2(@Valid
            @ModelAttribute("dodajPracownikaForm") Pracownik pracownik, BindingResult result,
            ModelMap map) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        if (result.hasErrors()) {
            map.addAttribute("edycja", 1);
            return "formularze/dodajPracownika";
        }
        PracownikDao dao = new PracownikDao();
        dao.aktualizujPracownika(pracownik.getId(), pracownik, log.getId());

        return "formularze/stronyWynikow/pracownikWynik";
    }
    //

    //EDYTUJ PRZEJAZD
    @RequestMapping(value = "/edytujPrzejazd", method = RequestMethod.GET)
    public String edytujPrzejazd(ModelMap map, @RequestParam(value = "id", required = true) String id) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        if (isNumeric(id)) {
            PrzejazdDao dao = new PrzejazdDao();
            Przejazd dodajPrzejazdForm = dao.pobierzPrzejazdPoId(Integer.parseInt(id), log.getId());
            map.addAttribute("edycja", 1);
            map.addAttribute("dodajPrzejazdForm", dodajPrzejazdForm);
            ///----------
            Map<String, String> listaPojazdow = new LinkedHashMap<String, String>();

            PojazdDao dao4 = new PojazdDao();
            List<Pojazd> pojazd = dao4.pobierzPojazdy(log.getId());

            for (Pojazd i : pojazd) {
                listaPojazdow.put(Integer.toString(i.getId()), i.getNazwa());
            }
            map.addAttribute("listaPojazdow", listaPojazdow);
        //==========

            ///----------
            Map<String, String> listaTras = new LinkedHashMap<String, String>();

            TrasaDao daoTrasa = new TrasaDao();
            List<Trasa> trasa = daoTrasa.pobierzTrasy(log.getId());

            for (Trasa i : trasa) {
                listaTras.put(Integer.toString(i.getId()), i.getNazwa());
            }
            map.addAttribute("listaTras", listaTras);
        //==========

            ///----------
            Map<String, String> listaFirm = new LinkedHashMap<String, String>();

            FirmaDao daoFirma = new FirmaDao();
            List<Firma> firma = daoFirma.pobierzFirmy(log.getId());

            for (Firma i : firma) {
                listaFirm.put(Integer.toString(i.getId()), i.getNazwaFirmy());
            }
            map.addAttribute("listaFirm", listaFirm);
        //==========

            ///----------
            Map<String, String> listaPracownikow = new LinkedHashMap<String, String>();

            PracownikDao daoPracownik = new PracownikDao();
            List<Pracownik> pracownik = daoPracownik.pobierzPracownikow(log.getId());

            for (Pracownik i : pracownik) {
                listaPracownikow.put(Integer.toString(i.getId()), i.getImie() + ' ' + i.getNazwisko());
            }
            map.addAttribute("listaPracownikow", listaPracownikow);
            //==========
        } else {
            return "redirect:blad?blad=3";
        }
        return "formularze/dodajPrzejazd";
    }

    @RequestMapping(value = "/edytujPrzejazd", method = RequestMethod.POST)
    public String processRegistration2(@Valid
            @ModelAttribute("dodajPrzejazdForm") Przejazd przejazd, BindingResult result,
            ModelMap map) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        if (result.hasErrors()) {
            map.addAttribute("edycja", 1);
            return "formularze/dodajPrzejazd";
        }
        PrzejazdDao dao = new PrzejazdDao();
        dao.aktualizujPrzejazd(Integer.parseInt(przejazd.getId()), przejazd, log.getId());

        PojazdDao pojazdDao = new PojazdDao();
        TrasaDao trasaDao = new TrasaDao();
        FirmaDao firmaDao = new FirmaDao();
        PracownikDao pracownikDao = new PracownikDao();

        przejazd.setIdPojazdu(pojazdDao.pobierzPojazdPoId(Integer.parseInt(przejazd.getIdPojazdu()), log.getId()).getNazwa());
        przejazd.setIdTrasy(trasaDao.pobierzTrasePoId(Integer.parseInt(przejazd.getIdTrasy()), log.getId()).getNazwa());
        przejazd.setIdFirmy(firmaDao.pobierzFirmePoId(Integer.parseInt(przejazd.getIdFirmy()), log.getId()).getNazwaFirmy());
        przejazd.setIdPracownika(pracownikDao.pobierzPracownikaPoId(Integer.parseInt(przejazd.getIdPracownika()), log.getId()).getImie()
                + " " + pracownikDao.pobierzPracownikaPoId(Integer.parseInt(przejazd.getIdPracownika()), log.getId()).getNazwisko());

        return "formularze/stronyWynikow/przejazdWynik";
    }
    //

    //Pracownicy
    @RequestMapping(value = "/dodajPracownika", method = RequestMethod.GET)
    public String dodajPracownika(ModelMap map) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }

        Pracownik dodajPracownikaForm = new Pracownik();
        map.addAttribute("dodajPracownikaForm", dodajPracownikaForm);
        return "formularze/dodajPracownika";
    }

    @RequestMapping(value = "/dodajPracownika", method = RequestMethod.POST)
    public String processRegistration(@Valid
            @ModelAttribute("dodajPracownikaForm") Pracownik pracownik,
            BindingResult result, ModelMap map) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        if (result.hasErrors()) {
            return "formularze/dodajPracownika";
        }
        PracownikDao dao = new PracownikDao();
        dao.zapiszPracownika(pracownik, log.getId());

        return "formularze/stronyWynikow/pracownikWynik";
    }

    //Trasy
    @RequestMapping(value = "/dodajTrase", method = RequestMethod.GET)
    public String dodajTrase(ModelMap map) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        Trasa dodajTraseForm = new Trasa();
        map.addAttribute("dodajTraseForm", dodajTraseForm);

        return "formularze/dodajTrase";
    }

    @RequestMapping(value = "/dodajTrase", method = RequestMethod.POST)
    public String processRegistration(@Valid
            @ModelAttribute("dodajTraseForm") Trasa trasa,
            BindingResult result, ModelMap map) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        if (result.hasErrors()) {
            return "formularze/dodajTrase";
        }
        //
        TrasaDao dao = new TrasaDao();
        dao.zapiszTrase(trasa, log.getId());
        return "formularze/stronyWynikow/trasaWynik";
    }

    //Przejazdy
    @RequestMapping(value = "/dodajPrzejazd", method = RequestMethod.GET)
    public String dodajPrzejazd(ModelMap map) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        Przejazd dodajPrzejazdForm = new Przejazd();
        map.addAttribute("dodajPrzejazdForm", dodajPrzejazdForm);

        ///----------
        Map<String, String> listaPojazdow = new LinkedHashMap<String, String>();

        PojazdDao dao = new PojazdDao();
        List<Pojazd> pojazd = dao.pobierzPojazdy(log.getId());

        for (Pojazd i : pojazd) {
            listaPojazdow.put(Integer.toString(i.getId()), i.getNazwa());
        }
        map.addAttribute("listaPojazdow", listaPojazdow);
        //==========

        ///----------
        Map<String, String> listaTras = new LinkedHashMap<String, String>();

        TrasaDao daoTrasa = new TrasaDao();
        List<Trasa> trasa = daoTrasa.pobierzTrasy(log.getId());

        for (Trasa i : trasa) {
            listaTras.put(Integer.toString(i.getId()), i.getNazwa());
        }
        map.addAttribute("listaTras", listaTras);
        //==========

        ///----------
        Map<String, String> listaFirm = new LinkedHashMap<String, String>();

        FirmaDao daoFirma = new FirmaDao();
        List<Firma> firma = daoFirma.pobierzFirmy(log.getId());

        for (Firma i : firma) {
            listaFirm.put(Integer.toString(i.getId()), i.getNazwaFirmy());
        }
        map.addAttribute("listaFirm", listaFirm);
        //==========

        ///----------
        Map<String, String> listaPracownikow = new LinkedHashMap<String, String>();

        PracownikDao daoPracownik = new PracownikDao();
        List<Pracownik> pracownik = daoPracownik.pobierzPracownikow(log.getId());

        for (Pracownik i : pracownik) {
            listaPracownikow.put(Integer.toString(i.getId()), i.getImie() + ' ' + i.getNazwisko());
        }
        map.addAttribute("listaPracownikow", listaPracownikow);
        //==========

        return "formularze/dodajPrzejazd";
    }

    @RequestMapping(value = "/dodajPrzejazd", method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute("dodajPrzejazdForm") Przejazd przejazd,
            ModelMap map) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }

        //
        PrzejazdDao dao = new PrzejazdDao();
        dao.zapiszPrzejazd(przejazd, log.getId());

        PojazdDao pojazdDao = new PojazdDao();
        TrasaDao trasaDao = new TrasaDao();
        FirmaDao firmaDao = new FirmaDao();
        PracownikDao pracownikDao = new PracownikDao();

        przejazd.setIdPojazdu(pojazdDao.pobierzPojazdPoId(Integer.parseInt(przejazd.getIdPojazdu()), log.getId()).getNazwa());
        przejazd.setIdTrasy(trasaDao.pobierzTrasePoId(Integer.parseInt(przejazd.getIdTrasy()), log.getId()).getNazwa());
        przejazd.setIdFirmy(firmaDao.pobierzFirmePoId(Integer.parseInt(przejazd.getIdFirmy()), log.getId()).getNazwaFirmy());
        przejazd.setIdPracownika(pracownikDao.pobierzPracownikaPoId(Integer.parseInt(przejazd.getIdPracownika()), log.getId()).getImie()
                + " " + pracownikDao.pobierzPracownikaPoId(Integer.parseInt(przejazd.getIdPracownika()), log.getId()).getNazwisko());

        return "formularze/stronyWynikow/przejazdWynik";
    }

    @RequestMapping(value = "/usun", method = RequestMethod.GET)
    public String usun(ModelMap map, @RequestParam(value = "id", required = true) String id, @RequestParam(value = "akceptacja", required = false) String akceptacja, @RequestParam(value = "typ", required = true) String typ) {
        if (!log.isZalogowany()) {
            return "redirect:logowanie";
        } else {
            map.addAttribute("sesja", log);
        }
        FirmaDao dao0 = new FirmaDao();
        PojazdDao dao1 = new PojazdDao();
        TrasaDao dao2 = new TrasaDao();
        PracownikDao dao3 = new PracownikDao();
        PrzejazdDao dao4 = new PrzejazdDao();
        //??
        if (id != null && isNumeric(id)) {
            if (akceptacja != null && isNumeric(akceptacja)) {

                if (1 == Integer.parseInt(akceptacja)) {
                    /* typ=1 pojazdy, typ=2 trasy, typ=3 firmy, typ4 pracownicy*/

                    if (typ != null && isNumeric(typ)) {
                        switch (Integer.parseInt(typ)) {
                            case 0:
                                //firma

                                dao0.usunFirme(Integer.parseInt(id), log.getId());
                                dao4.usunWszystkiePrzejazdy(3, log.getId());
                                return "redirect:firmy";

                            case 1:
                                dao1.usunPojazd(Integer.parseInt(id), log.getId());
                                dao4.usunWszystkiePrzejazdy(1, log.getId());
                                return "redirect:pojazdy";

                            case 2:
                                dao2.usunTrase(Integer.parseInt(id), log.getId());
                                dao4.usunWszystkiePrzejazdy(2, log.getId());
                                return "redirect:trasy";

                            case 3:
                                dao3.usunPracownika(Integer.parseInt(id), log.getId());
                                dao4.usunWszystkiePrzejazdy(4, log.getId());
                                return "redirect:pracownicy";

                            case 4:
                                dao4.usunPrzejazd(Integer.parseInt(id), log.getId());
                                return "redirect:przejazdy";

                            default:
                                System.out.println("nieprzewidziana sytuacja");
                        }
                    }

                }
                //??
            } else {
                map.addAttribute("id", id);
                //
                if (typ != null && isNumeric(typ)) {
                    switch (Integer.parseInt(typ)) {
                        case 0:
                            //firma
                            Firma firma = dao0.pobierzFirmePoId(Integer.parseInt(id), log.getId());
                            map.addAttribute("nazwa", " firme " + firma.getNazwaFirmy());
                            map.addAttribute("id", id);
                            map.addAttribute("typ", 0);
                            break;

                        case 1:
                            Pojazd pojazd = dao1.pobierzPojazdPoId(Integer.parseInt(id), log.getId());
                            map.addAttribute("nazwa", " pojazd " + pojazd.getNazwa());
                            map.addAttribute("id", id);
                            map.addAttribute("typ", 1);
                            break;
                        case 2:
                            Trasa trasa = dao2.pobierzTrasePoId(Integer.parseInt(id), log.getId());
                            map.addAttribute("nazwa", " trase " + trasa.getNazwa());
                            map.addAttribute("id", id);
                            map.addAttribute("typ", 2);
                            break;
                        case 3:
                            Pracownik pracownik = dao3.pobierzPracownikaPoId(Integer.parseInt(id), log.getId());
                            map.addAttribute("nazwa", " pracownika " + pracownik.getImie() + " " + pracownik.getNazwisko());
                            map.addAttribute("id", id);
                            map.addAttribute("typ", 3);
                            break;

                        case 4:
                            Przejazd przejazd = dao4.pobierzPrzejazdPoId(Integer.parseInt(id), log.getId());
                            map.addAttribute("nazwa", " przejazd pojazdem " + dao1.pobierzPojazdPoId(Integer.parseInt(przejazd.getIdPojazdu()), log.getId()).getNazwa() + " z dnia " + przejazd.getData());
                            map.addAttribute("id", id);
                            map.addAttribute("typ", 4);
                            break;

                        default:
                            return "redirect:firmy";
                    }
                    return "formularze/usun";
                }

                //
            }
        } else {
            return "redirect:blad?blad=3";
        }
        return "redirect:index";

    }

    @RequestMapping(value = "/przekieruj", method = RequestMethod.GET)
    public String przekieruj(ModelMap map, @RequestParam(value = "typ", required = false) String typ) {
        if (typ != null && isNumeric(typ)) {
            switch (Integer.parseInt(typ)) {
                case 0:
                    //firma
                    return "redirect:firmy";

                case 1:
                    return "redirect:pojazdy";

                case 2:
                    return "redirect:trasy";

                case 3:
                    return "redirect:pracownicy";

                case 4:
                    return "redirect:przejazdy";
            }

        }
        return "redirect:firmy";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info(ModelMap map) {
        Stawka stawka = new Stawka();
        map.addAttribute("stawka", stawka);
        return "info";
    }
    //END-----------------------------------------------------------------------

}
