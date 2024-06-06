package week9;

public class BangunRuang extends BangunDatar{
    private int tinggi;
    private static final double pi = 3.14;

    public BangunRuang(int sisi) {
        super(sisi);
    }
    public BangunRuang(int panjang, int lebar, int tinggi) {
        super(panjang,lebar);
        this.tinggi = tinggi;
    }

    public int getTinggi() {
        return tinggi;
    }

    public int volume(int a) {
        return getSisi()*getSisi()*getSisi();
    }

    public int volume(int a, int b, int c) {
        return getPanjang()*getLebar()*getTinggi();
    }

    public double volume(double a, double b, double c) {
        return ((getPanjang1()*getLebar1())*0.5)*getTinggi();
    }

    public double volume(double a) {
        return (pi * getSisi1() * getSisi1() * getSisi1())*4/3;
    }
}
