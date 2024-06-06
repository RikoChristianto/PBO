package week9;

public class BangunDatar {
    private int panjang;
    private int lebar;
    private int diameter;
    private int sisi;
    private static final double pi = 3.14;

    public BangunDatar(int sisi) {
        this.sisi = sisi;
    }

    public BangunDatar(int panjang, int lebar) {
        this.panjang = panjang;
        this.lebar = lebar;
    }

    public int luas(int a) {
        return a * a;
    }

    public int luas(int a, int b) {
        return a * b;
    }

    public double luas(double a, double b) {
        return (a * b) * 0.5;
    }

    public double luas(double a) {
        return pi * a * a;
    }

    public int keliling(int a) {
        return 4 * a;
    }

    public int keliling(int a, int b) {
        return 2*(a+b);
    }

    public double keliling(double a, double b) {
        return a + b + Math.sqrt(a * a + b * b);
    }

    public double keliling(double a) {
        return pi * (a*2);
    }

    public int getPanjang() {
        return panjang;
    }

    public int getLebar() {
        return lebar;
    }

    public int getSisi() {
        return sisi;
    }

    public double getSisi1() {
        return sisi;
    }

    public double getPanjang1() {
        return panjang;
    }

    public double getLebar1() {
        return lebar;
    }


}
