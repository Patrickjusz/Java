package kilometrowka.klasy;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class Trasa {

    private int id;

    @NotEmpty(message = "Prosze podać nazwę trasy.")
    @Size(min = 2, max = 15, message = "Nazwa musi się sładać od 2 do 15 znaków.")
    private String nazwa;

    @NotEmpty(message = "Prosze podać źródło wyjazdu.")
    @Size(min = 2, max = 64, message = "Źródło wyjazdu musi się sładać od 2 do 64 znaków.")
    private String skad;

    @NotEmpty(message = "Prosze podać miejsce docelowe.")
    @Size(min = 2, max = 64, message = "Miejsce docelowe wyjazdu musi się sładać od 2 do 64 znaków.")
    private String dokad;

    @NotEmpty(message = "Prosze podać odległość.")
    @Size(min = 1, max = 9, message = "Odległość musi się składać z liczby do 9 znaków")
    private String odleglosc;

    //opcjonalny
    private String opis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getSkad() {
        return skad;
    }

    public void setSkad(String skad) {
        this.skad = skad;
    }

    public String getDokad() {
        return dokad;
    }

    public void setDokad(String dokad) {
        this.dokad = dokad;
    }

    public String getOdleglosc() {
        return odleglosc;
    }

    public void setOdleglosc(String odleglosc) {
        this.odleglosc = odleglosc;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

}
