package kilometrowka.klasy;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class Pojazd {

    private int id;
    private double stawka;
    
    @NotEmpty(message = "Prosze podać nazwę pojazdu.")
    @Size(min = 2, max = 15, message = "Nazwa musi się sładać od 2 do 15 znaków.")
    private String nazwa;
    
    @NotEmpty(message = "Prosze podać numer rejestracyjny pojazdu.")
    @Size(min = 2, max = 10, message = "Numer rejestracyjny musi się sładać od 2 do 10 znaków.")
    private String numerRejestracyjny;
    
    @NotNull
    @Size(min=1, max=1, message="Błąd!")
    private String pojemnoscSilnika;
    
    //opcjonalne
    private String opis;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pojazd() {
        //konstruktor domyslny
    }

    public Pojazd(String nazwa, String numerRejestracyjny, String pojemnoscSilnika, String opis) {
        this.nazwa = nazwa;
        this.numerRejestracyjny = numerRejestracyjny;
        this.pojemnoscSilnika = pojemnoscSilnika;
        this.opis = opis;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNumerRejestracyjny() {
        return numerRejestracyjny;
    }

    public void setNumerRejestracyjny(String numerRejestracyjny) {
        this.numerRejestracyjny = numerRejestracyjny;
    }

    public String getPojemnoscSilnika() {
        return pojemnoscSilnika;
    }

    public void setPojemnoscSilnika(String pojemnoscSilnika) {
        this.pojemnoscSilnika = pojemnoscSilnika;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getStawka() {
        return stawka;
    }

    public void setStawka(double stawka) {
        this.stawka = stawka;
    }

    
    
    

}
