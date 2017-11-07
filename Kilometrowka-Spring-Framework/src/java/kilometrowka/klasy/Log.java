package kilometrowka.klasy;

public class Log {

    private int id_logu;
    private String data;
    private int akcja;
    private String ip;
    private String useragent;
    private String tresc;

    public int getId_logu() {
        return id_logu;
    }

    public void setId_logu(int id_logu) {
        this.id_logu = id_logu;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getAkcja() {
        return akcja;
    }

    public void setAkcja(int akcja) {
        this.akcja = akcja;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

}
