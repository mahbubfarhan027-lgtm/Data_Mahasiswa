package javapkg;
import java.util.Scanner;
import java.util.ArrayList;

public class NilaiMh {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Mahasiswa> daftar = new ArrayList<>();
        daftar.add(new Mahasiswa("farhan", 12, 2.0));
        daftar.add(new Mahasiswa("fajar", 183, 3.1));
        daftar.add(new Mahasiswa("fajrul", 6667, 2.9));
        System.out.print("Masukkan nama: ");
        String nama = input.nextLine();
        System.out.print("Masukkan nim: ");
        int nim = input.nextInt();
        System.out.print("Masukkan ipk: ");
        double ipk = input.nextDouble();
        daftar.add(new Mahasiswa(nama, nim, ipk));
        
        System.out.println("========= Daftar Mahasiswa =========");
        for (Mahasiswa mhs : daftar) {
            mhs.tampilkanInfo();
        }
    }
}