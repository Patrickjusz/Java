package kilometrowka.klasy;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class Firma {

    private int id;

    @NotEmpty(message = "Prosze podać nazwę firmy.")
    @Size(min = 2, max = 128, message = "Nazwa musi się sładać od 2 do 128 znaków.")
    private String nazwaFirmy;

    @NotEmpty(message = "Prosze podać adres firmy.")
    @Size(min = 2, max = 128, message = "Adres musi się sładać od 2 do 128 znaków.")
    private String adres;

    @NotEmpty(message = "Prosze podać miasto firmy.")
    @Size(min = 2, max = 64, message = "Nazwa miasta musi się sładać od 2 do 64 znaków.")
    private String miasto;

    @NotEmpty(message = "Prosze podać kod pocztowy do firmy.")
    @Size(min = 6, max = 6, message = "Kod pocztowy musi składać się z 6 znaków (XX-XXX).")
    private String kodPocztowy;

    //DO MODYFIKACJI!!!!!!!!!!!!!!!
    @NotEmpty(message = "Prosze podać NIP.")
    @Size(min = 1, max = 32, message = "NIP musi składać się od 1 do 32 znaków.")
    private String nip;

    @NotEmpty(message = "Prosze podać REGON firmy.")
    @Size(min = 9, max = 14, message = "REGON musi składać się od 9 do 14 znaków.")
    private String regon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwaFirmy() {
        return nazwaFirmy;
    }

    public void setNazwaFirmy(String nazwaFirmy) {
        this.nazwaFirmy = nazwaFirmy;
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

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

}
