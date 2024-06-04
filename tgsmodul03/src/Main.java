package org.java;

import java.util.Scanner;
import java.util.ArrayList;

class Buku {
    private String id;
    private String judul;
    private String pengarang;
    private String kategori;
    private int stok;
    private int durasiPinjam;

    //konstruktor buku
    public Buku(String id, String judul, String pengarang, String kategori, int stok) {
        this.id = id;
        this.judul = judul;
        this.pengarang = pengarang;
        this.kategori = kategori;
        this.stok = stok;
    }

    // Getter dan setter semua atribut

    public String getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public String getKategori() {
        return kategori;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getDurasiPinjam() {
        return durasiPinjam;
    }

    public void setDurasiPinjam(int durasiPinjam) {
        this.durasiPinjam = durasiPinjam;
    }
}

class Pengguna {
    protected static ArrayList<Buku> daftarBuku = new ArrayList<>();

    public void tampilkanBuku() {
        System.out.println("Daftar Buku:");
        System.out.println("================================================================");
        System.out.println("|| No. || Id Buku || Judul Buku || Pengarang || Kategori || Stok ||");
        int indeks = 1;
        for (Buku buku : daftarBuku) {
            System.out.println("|| " + indeks + "  || " + buku.getId() + " || " + buku.getJudul() + " || " + buku.getPengarang() + " || " + buku.getKategori() + "  || " + buku.getStok() + " ||");
            indeks++;
        }
        System.out.println("================================================================");
    }

    public boolean isAdmin(String namaPenggunaAdmin, String kataSandiAdmin) {
        return false;
    }
}

class Mahasiswa extends Pengguna {
    private String nama;
    private String nim;
    private String fakultas;
    private String programStudi;
    private ArrayList<Buku> bukuYangDipinjam;

    public Mahasiswa(String nama, String nim, String fakultas, String programStudi) {
        this.nama = nama;
        this.nim = nim;
        this.fakultas = fakultas;
        this.programStudi = programStudi;
        this.bukuYangDipinjam = new ArrayList<>();
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public String getFakultas() {
        return fakultas;
    }

    public String getProgramStudi() {
        return programStudi;
    }

    public ArrayList<Buku> getBukuYangDipinjam() {
        return bukuYangDipinjam;
    }

    public void tampilkanInfo() {
        System.out.println("Informasi Mahasiswa:");
        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + nim);
        System.out.println("Fakultas: " + fakultas);
        System.out.println("Program Studi: " + programStudi);
    }

    public void tampilkanBukuYangDipinjam() {
        if (bukuYangDipinjam.isEmpty()) {
            System.out.println("Tidak ada buku yang sedang dipinjam.");
        } else {
            System.out.println("Buku yang Dipinjam:");
            for (Buku buku : bukuYangDipinjam) {
                System.out.println("Judul: " + buku.getJudul());
            }
        }
    }

    public void pinjamBuku(Buku buku, int durasiPinjam) {
        bukuYangDipinjam.add(buku);
        buku.setStok(buku.getStok() - 1);
        buku.setDurasiPinjam(durasiPinjam);
        System.out.println("Buku '" + buku.getJudul() + "' berhasil dipinjam selama " + durasiPinjam + " hari.");
    }

    public void kembalikanBuku(Buku buku) {
        bukuYangDipinjam.remove(buku);
        buku.setStok(buku.getStok() + 1);
        System.out.println("Buku '" + buku.getJudul() + "' berhasil dikembalikan.");
    }
}

class Admin extends Pengguna {
    private String namaPenggunaAdmin = "admin";
    private String kataSandiAdmin = "adm";
    private ArrayList<Mahasiswa> daftarMahasiswa;

    public Admin() {
        this.daftarMahasiswa = new ArrayList<>();
    }

    public boolean isAdmin(String namaPengguna, String kataSandi) {
        return namaPengguna.equals(namaPenggunaAdmin) && kataSandi.equals(kataSandiAdmin);
    }

    public void tambahMahasiswa(Scanner scanner) {
        System.out.println("Masukkan detail mahasiswa:");
        System.out.print("Masukkan nama mahasiswa: ");
        String nama = scanner.nextLine();
        String nim;
        do {
            System.out.print("Masukkan NIM mahasiswa (15 digit): ");
            nim = scanner.nextLine();
            if (nim.length() != 15) {
                System.out.println("NIM harus terdiri dari 15 digit.");
            }
        } while (nim.length() != 15);
        System.out.print("Masukkan fakultas mahasiswa: ");
        String fakultas = scanner.nextLine();
        System.out.print("Masukkan program studi mahasiswa: ");
        String programStudi = scanner.nextLine();
        Mahasiswa mahasiswaBaru = new Mahasiswa(nama, nim, fakultas, programStudi);
        daftarMahasiswa.add(mahasiswaBaru);
        Main.userMahasiswa.add(mahasiswaBaru); // Tambahkan mahasiswa ke daftar userMahasiswa
        System.out.println("Mahasiswa berhasil terdaftar.");
    }

    public void tampilkanMahasiswa() {
        System.out.println("Daftar Mahasiswa Terdaftar:");
        for (Mahasiswa mahasiswa : daftarMahasiswa) {
            mahasiswa.tampilkanInfo();
        }
    }

    public void masukkanBuku(Scanner scanner) {
        System.out.println("Pilih kategori buku:");
        System.out.println("1. Buku Cerita");
        System.out.println("2. Buku Sejarah");
        System.out.println("3. Buku Teks");
        System.out.print("Masukkan pilihan (1-3): ");
        String pilihanKategori = scanner.nextLine();

        String kategori;
        switch (pilihanKategori) {
            case "1":
                kategori = "Buku Cerita";
                break;
            case "2":
                kategori = "Buku Sejarah";
                break;
            case "3":
                kategori = "Buku Teks";
                break;
            default:
                System.out.println("Pilihan tidak valid. Default ke Buku Cerita.");
                kategori = "Buku Cerita";
                break;
        }
        System.out.println("Masukkan detail buku:");
        System.out.print("Masukkan ID buku: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan judul buku: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan pengarang buku: ");
        String pengarang = scanner.nextLine();
        System.out.print("Masukkan stok buku: ");
        int stok = Integer.parseInt(scanner.nextLine());

        Buku bukuBaru = new Buku(id, judul, pengarang, kategori, stok);
        daftarBuku.add(bukuBaru);
        System.out.println("Buku berhasil ditambahkan ke perpustakaan.");
    }
}

class BukuSejarah extends Buku {
    public BukuSejarah(String id, String judul, String pengarang, int stok) {
        super(id, judul, pengarang, "Buku Sejarah", stok);
    }
}

class BukuTeks extends Buku {
    public BukuTeks(String id, String judul, String pengarang, int stok) {
        super(id, judul, pengarang, "Buku Teks", stok);
    }
}

class BukuCerita extends Buku {
    public BukuCerita(String id, String judul, String pengarang, int stok) {
        super(id, judul, pengarang, "Buku Cerita", stok);
    }
}

public class Main {
    static ArrayList<Buku> daftarBuku = new ArrayList<>();
    static ArrayList<Mahasiswa> userMahasiswa = new ArrayList<>();

    public static void main(String[] args) {
        inisialisasiData();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== Sistem Perpustakaan =====");
            System.out.println("1. Login sebagai Mahasiswa");
            System.out.println("2. Login sebagai Admin");
            System.out.println("3. Keluar");
            System.out.print("Masukkan pilihan: ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    // Login sebagai mahasiswa
                    System.out.print("Masukkan NIM Anda: ");
                    String nim = scanner.nextLine();
                    Mahasiswa mahasiswa = cariMahasiswaDenganNIM(nim);
                    if (mahasiswa != null) {
                        menuMahasiswa(mahasiswa, scanner);
                    } else {
                        System.out.println("Mahasiswa tidak ditemukan.");
                    }
                    break;
                case "2":
                    // Login sebagai admin
                    System.out.print("Masukkan nama pengguna admin: ");
                    String namaPenggunaAdmin = scanner.nextLine();
                    System.out.print("Masukkan kata sandi admin: ");
                    String kataSandiAdmin = scanner.nextLine();
                    Admin admin = new Admin();
                    if (admin.isAdmin(namaPenggunaAdmin, kataSandiAdmin)) {
                        menuAdmin(admin, scanner);
                    } else {
                        System.out.println("Nama pengguna atau kata sandi tidak valid.");
                    }
                    break;
                case "3":
                    System.out.println("Keluar...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public static void inisialisasiData() {
        // Tambahkan beberapa buku awal ke perpustakaan
        Buku buku1 = new BukuCerita("C001", "Alice in Wonderland", "Lewis Carroll", 5);
        Buku buku2 = new BukuSejarah("S001", "The Guns of August", "Barbara W. Tuchman", 3);
        Buku buku3 = new BukuTeks("T001", "Introduction to Algorithms", "Thomas H. Cormen", 2);

        daftarBuku.add(buku1);
        daftarBuku.add(buku2);
        daftarBuku.add(buku3);
    }

    public static void menuMahasiswa(Mahasiswa mahasiswa, Scanner scanner) {
        while (true) {
            System.out.println("===== Menu Mahasiswa =====");
            System.out.println("1. Tampilkan Buku yang Tersedia");
            System.out.println("2. Pinjam Buku");
            System.out.println("3. Kembalikan Buku");
            System.out.println("4. Lihat Buku yang Dipinjam");
            System.out.println("5. Logout");
            System.out.print("Masukkan pilihan: ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    mahasiswa.tampilkanBuku();
                    break;
                case "2":
                    System.out.print("Masukkan nomor buku yang ingin Anda pinjam: ");
                    int indeksBuku = Integer.parseInt(scanner.nextLine()) - 1;
                    if (indeksBuku >= 0 && indeksBuku < daftarBuku.size()) {
                        Buku bukuDipilih = daftarBuku.get(indeksBuku);
                        System.out.print("Masukkan durasi pinjam (dalam hari): ");
                        int durasiPinjam = Integer.parseInt(scanner.nextLine());
                        mahasiswa.pinjamBuku(bukuDipilih, durasiPinjam);
                    } else {
                        System.out.println("Nomor buku tidak valid.");
                    }
                    break;
                case "3":
                    if (!mahasiswa.getBukuYangDipinjam().isEmpty()) {
                        System.out.println("Pilih buku yang akan dikembalikan:");
                        mahasiswa.tampilkanBukuYangDipinjam();
                        System.out.print("Masukkan judul buku: ");
                        String judulBuku = scanner.nextLine();
                        Buku bukuKembali = null;
                        for (Buku buku : mahasiswa.getBukuYangDipinjam()) {
                            if (buku.getJudul().equalsIgnoreCase(judulBuku)) {
                                bukuKembali = buku;
                                break;
                            }
                        }
                        if (bukuKembali != null) {
                            mahasiswa.kembalikanBuku(bukuKembali);
                        } else {
                            System.out.println("Buku tidak ditemukan.");
                        }
                    } else {
                        System.out.println("Tidak ada buku yang sedang dipinjam.");
                    }
                    break;
                case "4":
                    mahasiswa.tampilkanBukuYangDipinjam();
                    break;
                case "5":
                    System.out.println("Logout...");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public static void menuAdmin(Admin admin, Scanner scanner) {
        while (true) {
            System.out.println("===== Menu Admin =====");
            System.out.println("1. Tampilkan Buku yang Tersedia");
            System.out.println("2. Tambahkan Buku");
            System.out.println("3. Tampilkan Mahasiswa Terdaftar");
            System.out.println("4. Tambahkan Mahasiswa");
            System.out.println("5. Logout");
            System.out.print("Masukkan pilihan: ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    admin.tampilkanBuku();
                    break;
                case "2":
                    admin.masukkanBuku(scanner);
                    break;
                case "3":
                    admin.tampilkanMahasiswa();
                    break;
                case "4":
                    admin.tambahMahasiswa(scanner);
                    break;
                case "5":
                    System.out.println("Logout...");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public static Mahasiswa cariMahasiswaDenganNIM(String nim) {
        for (Mahasiswa mahasiswa : userMahasiswa) {
            if (mahasiswa.getNim().equals(nim)) {
                return mahasiswa;
            }
        }
        return null;
    }
}

