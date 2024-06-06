package week9;

public class Hitung {
    public static void main(String[] args) {
        BangunDatar bd1 = new BangunDatar(5);
        BangunDatar bd = new BangunDatar(5, 4);

        BangunRuang br1 = new BangunRuang(5, 4, 8);
        BangunRuang br2 = new BangunRuang(5);

        System.out.println("Luas Persegi         : " + bd1.luas(bd1.getSisi()));
        System.out.println("Luas Persegi Panjang : " + bd.luas(bd.getPanjang(), bd.getLebar()));
        System.out.println("Luas Segitiga        : " + bd.luas(bd.getPanjang1(), bd.getLebar1()));
        System.out.println("Luas Lingkaran       : " + bd1.luas(bd1.getSisi1()));

        System.out.println();

        System.out.println("keliling Persegi         : " + bd1.keliling(bd1.getSisi()));
        System.out.println("Keliling Persegi Panjang : " + bd.keliling(bd.getPanjang(), bd.getLebar()));
        System.out.println("keliling Segitiga        : " + bd.keliling(bd.getPanjang1(), bd.getLebar1()));
        System.out.println("keliling Lingkaran       : " + bd1.keliling(bd1.getSisi1()));

        System.out.println();

        System.out.println("Volume Kubus  : " + br2.volume(br2.getSisi()));
        System.out.println("Volume Balok  : " + br1.volume(br1.getPanjang(), br1.getLebar(), br1.getTinggi()));
        System.out.println("Volume Prisma : " + br1.volume(br1.getPanjang1(), br1.getLebar1(), br1.getTinggi()));
        System.out.println("Volume Bola   : " + br2.volume(br2.getSisi1()));
    }
}
