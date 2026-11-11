package javapkg;
import java.util.Scanner;
import java.util.ArrayList;

class Mahasiswa {
    private String nama;
    private int nim;
    private double ipk;
            
    Mahasiswa (String nama, int nim, double ipk) {
        this.nama = nama;
        this.nim = nim;
        this.ipk = ipk;
    }
    public boolean isLulus() {
        return ipk >= 2.5;
        
    }
    
    public String predikat() {
        if (ipk >= 3.5) return "Cumlaude";
        if (ipk >= 3.0) return "Sangat Memuaskan";
        if (ipk >= 2.5) return "Memuaskan";
        return "Perlu perbaikan";
    }
    
    public void tampilkanInfo() {
        System.out.println("----------------------");
        System.out.println("Nama        : " + nama);
        System.out.println("Nim         : " + nim);
        System.out.println("Nilai       : " + ipk);
        System.out.println("Predikat    : " + predikat());
        System.out.println("Lulus       : " + isLulus());
        System.out.println("----------------------");
    }
}

public class NilaiMhs {
    public static void main(String[] args) {
        ArrayList<Mahasiswa> daftar = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        
        System.out.print("Masukkan jumlah mahasiswa: ");
        int nomor = input.nextInt();
        input.nextLine();
        
        for (int i = 0; i < nomor; i++) {
            System.out.println("\nData mahasiswa ke-[" + (i + 1) + "]---");
            
        System.out.print("\nMasukkan nama mahasiswa: ");
        String nama = input.nextLine();
        
        System.out.print("Masukkan NIM: ");
        int nim = input.nextInt();
        
        System.out.print("Masukkan IPK: ");
        double ipk = input.nextDouble();
        input.nextLine();
        
        daftar.add(new Mahasiswa(nama, nim, ipk));
        }
        
      System.out.println("\n===== Daftar Mahasiswa =====");
      for (Mahasiswa mhs : daftar) {
          mhs.tampilkanInfo();
      }
      input.close();
    }
}