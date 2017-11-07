package kilometrowka.klasy;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class Logowanie {

    @NotEmpty(message = "Prosze podać login.")
    @Size(min = 5, max = 32, message = "Login musi się sładać od 5 do 32 znaków.")
    private String login;

    @NotEmpty(message = "Prosze podać hasło.")
    @Size(min = 8, max = 32, message = "Hasło musi się składać od 8 do 32 znaków.")
    private String haslo;

    @NotEmpty(message = "Prosze podać ponownie identyczne hasło.")
    @Size(min = 8, max = 32, message = "Hasło musi się składać od 8 do 32 znaków i być identyczne z podanym powyżej.")
    private String haslo2;

    @NotEmpty(message = "Prosze podać e-mail.")
    @Email(message = "Ten e-mail jest niepoprawny!")
    private String email;
    private int id;
    private boolean zalogowany;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isZalogowany() {
        return zalogowany;
    }

    public void setZalogowany(boolean zalogowany) {
        this.zalogowany = zalogowany;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHaslo2() {
        return haslo2;
    }

    public void setHaslo2(String haslo2) {
        this.haslo2 = haslo2;
    }

}
