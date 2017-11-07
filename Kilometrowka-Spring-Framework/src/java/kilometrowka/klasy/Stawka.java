package kilometrowka.klasy;

public class Stawka {

    private double stawkaDo900;
    private double stawkaPowyzej900;
    private double stawkaMotorower;
    private double stawkaMotocykl;

    public Stawka() {
        this.stawkaDo900 = 0.5214;
        this.stawkaPowyzej900 = 0.8358;
        this.stawkaMotocykl = 0.2302;
        this.stawkaMotorower = 0.1382;
    }

    public double getStawkaDo900() {
        return stawkaDo900;
    }

    public double getStawkaPowyzej900() {
        return stawkaPowyzej900;
    }

    public double getStawkaMotorower() {
        return stawkaMotorower;
    }

    public double getStawkaMotocykl() {
        return stawkaMotocykl;
    }

    public double policzStawke(double a, double b) {
        return a * b;
    }
}
