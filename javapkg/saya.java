package javapkg;
import java.util.ArrayList;
import java.util.Scanner;
  
 
//============================
//  ABSTRACT CLASS ORANG
//============================

abstract class Orang {
    private String nama;
    
    public Orang (String nama) {
        this.nama = nama;
    }
    
    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong!!");
        }
        
        this.nama = nama;
    }
    public abstract void tampilkaninfo();
}


//=========================
//  ABSTRACT CLASS SANTRI
//=========================

abstract class Santri extends Orang {
    private String nis;
    
    public Santri (String nama, String nis) {
        super(nama);
        this.nis = nis;
    }
    
    public String getNis() {
        return nis;
    }
    
    public abstract String getJenisSantri();
    
    @Override
    public void tampilkaninfo() {
        System.out.println("NIS     : " + nis);
        System.out.println("NAMA    : " + getNama());
        System.out.println("JENIS   : " + getJenisSantri());
    }
}


//==============================
//  INHERITANCE SANTRI MUKMIM
//==============================


class santrimukmim extends Santri {
    private String kamar;
    
    public santrimukmim(String nama, String nis, String kamar) {
        super(nama, nis);
        this.kamar = kamar;
    }
    
    public String getKamar() {
        return kamar;
    }
    
    @Override
    public String getJenisSantri() {
        return "Mukmim";
    }
    
    @Override
    public void tampilkaninfo() {
    super.tampilkaninfo();
    System.out.println("KAMAR   : " + kamar);
    System.out.println("--------------------------");
    }
}


//==============================
//  INHERITANCE SANTRI KALONG
//==============================


class SantriKalong extends Santri {
    private String alamat;
    
    
    public SantriKalong(String nama, String nis, String alamat) {
        super(nama, nis);
        this.alamat = alamat;
    }
    
    public String getAlamat() {
        return alamat;
    }
    
    @Override
    public String getJenisSantri() {
        return "Kalong";
    }
    
    @Override
    public void tampilkaninfo() {
        super.tampilkaninfo();
        System.out.println("Alamat  : " + alamat);
        System.out.println("-------------------------");
    }
}


//==========================
//  INTERFACE PEMBAYARAN
//==========================
interface PembayaranService {
    void catatpembayaran(
         Santri santri,
         double nominal,
         String tanggal
    );
}

//========================
//  CLASS PEMBAYARAN
//========================
class Pembayaran {
    private String nama;
    private String nis;
    private double nominal;
    private String tanggal;
    
    public Pembayaran(
    String nama,
    String nis,
    double nominal,
    String tanggal
    ) {
        this.nama = nama;
        this.nis = nis;
        this.nominal = nominal;
        this.tanggal = tanggal;
    }
    
    
    
    public double getNominal() {
        return nominal;
    }
    
    public void tampilkaninfo() {
        System.out.println("Nis     : " + nis);
        System.out.println("Nama    : " + nama);
        System.out.println("Nominal : Rp" + nominal);
        System.out.println("Tanggal : " + tanggal);
    }
  }

//=============================
//  IMPLEMENSTASI INTERFACE
//=============================

class PembayaranServicempl implements PembayaranService {
    private ArrayList<Pembayaran> daftarPembayaran;
    
    public PembayaranServicempl() {
        daftarPembayaran = new ArrayList<>();
    }
    
    @Override
    public void catatpembayaran(
         Santri santri,
         double nominal,
         String tanggal
    ) {
        if (nominal <= 0) {
            throw new IllegalArgumentException (
            "Nominal pembayaran harus lebih dari nol!"
            );
        }
        
        Pembayaran pembayaran = new Pembayaran(santri.getNama(), santri.getNis(), nominal, tanggal);
        
        daftarPembayaran.add(pembayaran);
        
        System.out.println("Pembayaran berhasil dicatat!");
    }
    
    public void tampilkanriwayat() {
        if (daftarPembayaran.isEmpty()) {
            System.out.println("Belum melakukan transaksi pembayaran");
            return;
        }
        for (Pembayaran p : daftarPembayaran) {
            p.tampilkaninfo();
        }
    }
}

//==========================
//  CLASS PERIZINAN
//==========================

class Perizinan {
    private String nis;
    private String nama;
    private String keperluan;
    private String tanggal;
    
    public Perizinan(
    Santri santri,
    String keperluan,
    String tanggal
    ) {
        this.nis = santri.getNis();
        this.nama = santri.getNama();
        this.keperluan = keperluan;
        this.tanggal = tanggal;
    }
    
    public void tampilkaninfo() {
        System.out.println("Nis        : " + nis);
        System.out.println("Nama       : " + nama);
        System.out.println("Keperluan  : " + keperluan);
        System.out.println("Tanggal    : " + tanggal);
        System.out.println("------------------------------");
    }
}

//================================
//  CLASS MAIN
//================================

public class saya {
   static Scanner input = new Scanner(System.in);
    
   static ArrayList<Santri> daftarSantri = new ArrayList<>();
   static ArrayList<Perizinan> daftarizin = new ArrayList<>();
   
   static PembayaranServicempl layananpembayaran = new PembayaranServicempl();
   
        public static void main(String[] args) {
            
            int pilihan;
            do {
                   tampilkanmenu(); 
                   pilihan = bacaInt("Pilih Menu: ");
                   
                   try {
                       switch (pilihan) {
                           case 1:
                              tambahSantri();
                              break;
                              
                           case 2:
                              tampilkanSantri();
                              break;
                              
                           case 3:
                               cariSantri();
                               break;
                               
                           case 4:
                               catatPembayaran();
                               break;
                               
                           case 5:
                               catatIzin();
                               break;
                               
                           case 6:
                               layananpembayaran.tampilkanriwayat();
                               break;
                               
                           case 7 :
                               tampilkanRiwayatIzin();
                               break;
                               
                           case 0:
                               System.out.println("Program selesai!");
                               break;
                               
                           default :
                               System.out.println("Menu tidak tersedia!.");
                       }
                   } catch (IllegalArgumentException e) {
                       System.out.println("Kesalahan " + e.getMessage());
                   }
            } while (pilihan != 0);
            
            input.close();
      }
        
      //==========================
      //    MENU APP
      //==========================
        static void tampilkanmenu() {
           System.out.println("\n===================================");
           System.out.println(" ADMINISTRASI PONDOK PESANTREN ");
           System.out.println("  LEMBAGA TINGGI LUIHUR MALANG");
           System.out.println("=====================================");
           System.out.println("1. Tambah Santri");
           System.out.println("2. Tampilkan Santri");
           System.out.println("3. Cari Santri");
           System.out.println("4. Catat Pembayaran Syriah");
           System.out.println("5. Catat Perizinan");
           System.out.println("6. Riwayat Pembayaran");
           System.out.println("7. Riwayat Perizinan");
           System.out.println("0. Keluar");
           System.out.println("=====================================");
        }
        
    //=====================
    // MEMBACA INPUT ANGKA
    //=====================
    static int bacaInt(String pesan) {
        while (true) {
            System.out.print(pesan);
            
            try {
               int nilai =  Integer.parseInt(input.nextLine().trim());
               return nilai;
            } catch (NumberFormatException e) {
                System.out.println("Harus angka bulat!");
            }
        }
    }    
    
    static double bacaDouble(String pesan) {
        while (true) {
            System.out.print(pesan);
            
            try {
                double nilai = Double.parseDouble(input.nextLine().trim());
                
                if (!Double.isFinite(nilai)) {
                    System.out.println("Nominal tidak valid!");
                    continue;
                }
                
                return nilai;
            } catch (NumberFormatException e) {
                System.out.println("Masukkan nominal yang valid.");
            }
        }
    }

    // ------------------------
    //  TAMBAH SANTRI
    // ------------------------
    static void tambahSantri() {
        System.out.println("\n---Tambah Santri---");
        
        System.out.print("Nis: ");
        String nis = input.nextLine().trim();
        
        if (nis.isEmpty()) {
            throw new IllegalArgumentException("NIS tidak boleh kosong");
        }
        
        if (cariSantri(nis) != null) {
            throw new IllegalArgumentException("NIS sudah dimasukkan");
        }
        
        System.out.print("Nama: ");
        String nama = input.nextLine().trim();
        
        if (nama.isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong.");
        }

        System.out.println("Jenis santri:");
        System.out.println("1. Mukim");
        System.out.println("2. Kalong");

        int jenis = bacaInt("Pilih jenis: ");

        Santri santri;

        if (jenis == 1) {
            System.out.print("Nomor kamar: ");
            String kamar = input.nextLine().trim();

            santri = new santrimukmim(nama, nis, kamar);

        } else if (jenis == 2) {
            System.out.print("Alamat: ");
            String alamat = input.nextLine().trim();

            santri = new SantriKalong(nama, nis, alamat);

        } else {
            throw new IllegalArgumentException("Jenis tidak tersedia.");
        }

        daftarSantri.add(santri);
        System.out.println("Data santri berhasil ditambahkan.");
    }

    // ------------------------------------------
    // TAMPILKAN SANTRI
    // ------------------------------------------
    static void tampilkanSantri() {
        System.out.println("\n--- Daftar Santri ---");

        if (daftarSantri.isEmpty()) {
            System.out.println("Belum ada data santri.");
            return;
        }

        for (Santri santri : daftarSantri) {
            santri.tampilkaninfo();
        }
    }

    // ------------------------------------------
    // PENCARIAN SANTRI: OVERLOADING
    // ------------------------------------------
    static Santri cariSantri(String nis) {
        for (Santri santri : daftarSantri) {
            if (santri.getNis().equalsIgnoreCase(nis)) {
                return santri;
            }
        }

        return null;
    }

    static Santri cariSantri() {
        System.out.println("\n--- Cari Santri ---");

        System.out.print("Masukkan NIS: ");
        String nis = input.nextLine().trim();

        Santri santri = cariSantri(nis);

        if (santri == null) {
            System.out.println("Santri tidak ditemukan.");
        } else {
            santri.tampilkaninfo();
        }

        return santri;
    }

    // ------------------------------------------
    // CATAT PEMBAYARAN
    // ------------------------------------------
    static void catatPembayaran() {
        System.out.println("\n--- Pembayaran Syahriah ---");

        Santri santri = cariSantriUntukTransaksi();

        if (santri == null) {
            return;
        }

        double nominal = bacaDouble("Nominal pembayaran: Rp");
        
        System.out.print("Tanggal pembayaran (DD-MM-YYYY): ");
        String tanggal = input.nextLine().trim();

        if (tanggal.isEmpty()) {
            throw new IllegalArgumentException("Tanggal wajib diisi.");
        }

        layananpembayaran.catatpembayaran(
            santri,
            nominal,
            tanggal
        );
    }

    // ------------------------------------------
    // CATAT IZIN
    // ------------------------------------------
    static void catatIzin() {
        System.out.println("\n--- Catat Perizinan ---");

        Santri santri = cariSantriUntukTransaksi();

        if (santri == null) {
            return;
        }

        System.out.print("Keperluan izin: ");
        String keperluan = input.nextLine().trim();

        if (keperluan.isEmpty()) {
            throw new IllegalArgumentException(
                "Keperluan izin wajib diisi."
            );
        }

        System.out.print("Tanggal izin (DD-MM-YYYY): ");
        String tanggal = input.nextLine().trim();

        if (tanggal.isEmpty()) {
            throw new IllegalArgumentException("Tanggal wajib diisi.");
        }

        Perizinan izin = new Perizinan(
            santri,
            keperluan,
            tanggal
        );

        daftarizin.add(izin);

        System.out.println("Perizinan berhasil dicatat.");
    }

    // ------------------------------------------
    // PENCARIAN UNTUK TRANSAKSI
    // ------------------------------------------
    static Santri cariSantriUntukTransaksi() {
        System.out.print("Masukkan NIS santri: ");
        String nis = input.nextLine().trim();

        Santri santri = cariSantri(nis);

        if (santri == null) {
            System.out.println("Santri tidak ditemukan.");
        }

        return santri;
    }

    // ------------------------------------------
    // RIWAYAT IZIN
    // ------------------------------------------
    static void tampilkanRiwayatIzin() {
        System.out.println("\n--- Riwayat Perizinan ---");

        if (daftarizin.isEmpty()) {
            System.out.println("Belum ada data perizinan.");
            return;
        }

        for (Perizinan izin : daftarizin) {
            izin.tampilkaninfo();
        }
    }
}
    
    
