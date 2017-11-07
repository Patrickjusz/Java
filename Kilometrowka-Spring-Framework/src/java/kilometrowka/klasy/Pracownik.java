package kilometrowka.klasy;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class Pracownik {

    private int id;

    @NotEmpty(message = "Prosze podać imię pracownika.")
    @Size(min = 2, max = 22, message = "Imię musi się sładać od 2 do 22 znaków.")
    private String imie;

    @NotEmpty(message = "Prosze podać nazwisko pracownika.")
    @Size(min = 2, max = 32, message = "Nazwisko musi się sładać od 2 do 32 znaków.")
    private String nazwisko;

    @NotEmpty(message = "Prosze podać adres pracownika.")
    @Size(min = 2, max = 128, message = "Adres musi się sładać od 2 do 128 znaków.")
    private String adres;

    @NotEmpty(message = "Prosze podać miasto zamieszkania pracownika.")
    @Size(min = 2, max = 64, message = "Nazwa miasta musi się sładać od 2 do 64 znaków.")
    private String miasto;

    @NotEmpty(message = "Prosze podać kod pocztowy pracownika.")
    @Size(min = 6, max = 6, message = "Kod pocztowy musi składać się z 6 znaków (XX-XXX).")
    private String kodPocztowy;

    private String telefon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

}
