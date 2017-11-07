package kilometrowka.klasy;

import java.util.List;

public class Przejazd {

    private String idPojazdu;
    private String idTrasy;
    private String idFirmy;
    private String idPracownika;

    private String celWyjazdu;

    private String adnotacje;
    private String data;
    private String odlegloscTrasy;
    private String nazwaPojazdu;
    private String nazwaFirmy;
    private String nazwaPracownika;
    private String nazwaTrasy;
    private List<Data> miesiacIRok;
    private String id;
    private String cena;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPojazdu() {
        return idPojazdu;
    }

    public void setIdPojazdu(String idPojazdu) {
        this.idPojazdu = idPojazdu;
    }

    public String getIdTrasy() {
        return idTrasy;
    }

    public void setIdTrasy(String idTrasy) {
        this.idTrasy = idTrasy;
    }

    public String getIdFirmy() {
        return idFirmy;
    }

    public void setIdFirmy(String idFirmy) {
        this.idFirmy = idFirmy;
    }

    public String getIdPracownika() {
        return idPracownika;
    }

    public void setIdPracownika(String idPracownika) {
        this.idPracownika = idPracownika;
    }

    public String getCelWyjazdu() {
        return celWyjazdu;
    }

    public void setCelWyjazdu(String celWyjazdu) {
        this.celWyjazdu = celWyjazdu;
    }

    public String getAdnotacje() {
        return adnotacje;
    }

    public void setAdnotacje(String adnotacje) {
        this.adnotacje = adnotacje;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOdlegloscTrasy() {
        return odlegloscTrasy;
    }

    public void setOdlegloscTrasy(String odlegloscTrasy) {
        this.odlegloscTrasy = odlegloscTrasy;
    }

    public String getNazwaPojazdu() {
        return nazwaPojazdu;
    }

    public void setNazwaPojazdu(String nazwaPojazdu) {
        this.nazwaPojazdu = nazwaPojazdu;
    }

    public String getNazwaFirmy() {
        return nazwaFirmy;
    }

    public void setNazwaFirmy(String nazwaFirmy) {
        this.nazwaFirmy = nazwaFirmy;
    }

    public String getNazwaPracownika() {
        return nazwaPracownika;
    }

    public void setNazwaPracownika(String nazwaPracownika) {
        this.nazwaPracownika = nazwaPracownika;
    }

    public String getNazwaTrasy() {
        return nazwaTrasy;
    }

    public void setNazwaTrasy(String nazwaTrasy) {
        this.nazwaTrasy = nazwaTrasy;
    }

    public List<Data> getMiesiacIRok() {
        return miesiacIRok;
    }

    public void setMiesiacIRok(List<Data> miesiacIRok) {
        this.miesiacIRok = miesiacIRok;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

}
