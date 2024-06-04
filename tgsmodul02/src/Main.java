import java.util.ArrayList;
import java.util.Scanner;
// Kelas untuk buku
class Buku {
    private String id;
    private String judul;
    private String penulis;
    private String kategori;
    private int stok;

    public Buku(String id, String judul, String penulis, String kategori, int stok) {
        this.id = id;
        this.judul = judul;
        this.penulis = penulis;
        this.kategori = kategori;
        this.stok = stok;
    }
    public String getId() {
        return id;
    }
    public String getJudul() {
        return judul;
    }
    public String getPenulis() {
        return penulis;
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
}
// Kelas untuk mahasiswa
class Mahasiswa {
    private String nama;
    private String fakultas;
    private String nim;
    private String program;
    private ArrayList<Buku> bukuYangDipinjam = new ArrayList<>();

    public Mahasiswa(String nama, String fakultas, String nim, String program) {
        this.nama = nama;
        this.fakultas = fakultas;
        this.nim = nim;
        this.program = program;
    }

    public String getNama() {
        return nama;
    }

    public String getFakultas() {
        return fakultas;
    }

    public String getNim() {
        return nim;
    }

    public String getProgram() {
        return program;
    }

    public void lihatBukuYangDipinjam() {
        if (bukuYangDipinjam.isEmpty()) {
            System.out.println("Anda belum meminjam buku apapun.");
            return;
        }
        // Fungsi untuk melihat buku yang dipinjam oleh mahasiswa
        System.out.println("\n===== Buku Terpinjam =====");
        System.out.printf("%-10s %-20s %-20s %-10s\n", "ID", "Judul", "Penulis", "Stok");
        for (Buku buku : bukuYangDipinjam) {
            System.out.printf("%-10s %-20s %-20s %-10d\n", buku.getId(), buku.getJudul(), buku.getPenulis(), buku.getStok());
        }
    }

    public void pinjamBuku(ArrayList<Buku> bukus, Scanner scanner) {
        System.out.print("Masukkan ID buku yang ingin dipinjam: ");
        String id = scanner.next();

        if (id.isEmpty()) {
            System.out.println("ID buku tidak boleh kosong. Kembali ke menu mahasiswa...");
            return;
        }

        Buku buku = null;
        for (Buku b : bukus) {
            if (b.getId().equals(id)) {
                buku = b;
                break;
            }
        }

        if (buku == null) {
            System.out.println("Buku tidak ditemukan. Kembali ke menu mahasiswa...");
            return;
        }

        if (buku.getStok() > 0) {
            bukuYangDipinjam.add(buku);
            buku.setStok(buku.getStok() - 1);
            System.out.println("Buku berhasil dipinjam!");
        } else {
            System.out.println("Maaf, stok buku habis.");
        }
    }
}

public class Main {
    // Daftar mahasiswa dan buku
    private static ArrayList<Mahasiswa> mahasiswas = new ArrayList<>();
    private static ArrayList<Buku> bukus = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Menambahkan buku ke dalam daftar buku
        bukus.add(new Buku("388c-e681-9152", "a", "hoh", "Pendidikan", 3));
        bukus.add(new Buku("ed90-be30-5cdb", "b", "comel", "Horror", 2));
        bukus.add(new Buku("d95e-0c4a-9523", "c", "l", "SciFi", 3));

        while (true) {
            System.out.println("===== Sistem Perpustakaan =====");
            System.out.println("1. Masuk sebagai Mahasiswa");
            System.out.println("2. Masuk sebagai Admin");
            System.out.println("3. Keluar");
            System.out.print("Pilih opsi (1-3): ");
            int userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    masukSebagaiMahasiswa();
                    break;
                case 2:
                    masukSebagaiAdmin();
                    break;
                case 3:
                    System.out.println("Terima kasih. Keluar dari program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Input tidak valid. Silakan coba lagi.");
            }
        }
    }

    // Fungsi untuk masuk sebagai mahasiswa
    public static void masukSebagaiMahasiswa() {
        System.out.println("===== Menu Mahasiswa ====");
        System.out.print("Masukkan NIM Anda (masukkan 99 untuk kembali): ");
        String nim = scanner.next();

        if (nim.equals("99")) {
            System.out.println("Kembali ke menu utama...");
            return;
        }

        // Mencari mahasiswa berdasarkan NIM
        Mahasiswa mahasiswa = cariMahasiswaDenganNIM(nim);

        if (mahasiswa == null) {
            System.out.println("Mahasiswa tidak ditemukan. Kembali ke menu utama...");
            return;
        }

        // menu untuk pinjam buku
        while (true) {
            System.out.println("\n===== Menu Mahasiswa ====");
            System.out.println("1. Buku Terpinjam");
            System.out.println("2. Pinjam Buku");
            System.out.println("3. Keluar");
            System.out.print("Pilih opsi (1-3): ");
            int userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    mahasiswa.lihatBukuYangDipinjam();
                    break;
                case 2:
                    mahasiswa.pinjamBuku(bukus, scanner);
                    break;
                case 3:
                    System.out.println("Keluar dari akun mahasiswa...");
                    return;
                default:
                    System.out.println("Input tidak valid. Silakan coba lagi.");
            }
        }
    }

    // Fungsi untuk masuk sebagai admin
    public static Mahasiswa cariMahasiswaDenganNIM(String nim) {
        for (Mahasiswa m : mahasiswas) {
            if (m.getNim().equals(nim)) {
                return m;
            }
        }
        return null;
    }

    // Menu admin
    public static void masukSebagaiAdmin() {
        System.out.println("===== Menu Admin =====");
        System.out.println("1. Tambah Mahasiswa");
        System.out.println("2. Tampilkan Mahasiswa Terdaftar");
        System.out.println("3. Keluar");
        System.out.print("Pilih opsi (1-3): ");
        int userInput = scanner.nextInt();

        switch (userInput) {
            case 1:
                tambahMahasiswa();
                break;
            case 2:
                tampilkanMahasiswaTerdaftar();
                break;
            case 3:
                System.out.println("Keluar dari akun admin...");
                break;
            default:
                System.out.println("Input tidak valid. Silakan coba lagi.");
        }
    }

    // Fungsi untuk menambahkan mahasiswa baru
    public static void tambahMahasiswa() {
        System.out.print("Masukkan nama mahasiswa: ");
        String nama = scanner.next();
        System.out.print("Masukkan fakultas mahasiswa: ");
        String fakultas = scanner.next();
        System.out.print("Masukkan NIM mahasiswa: ");
        String nim = scanner.next();
        System.out.print("Masukkan program studi mahasiswa: ");
        String program = scanner.next();

        mahasiswas.add(new Mahasiswa(nama, fakultas, nim, program));
        System.out.println("Mahasiswa berhasil ditambahkan!");
    }

    // Fungsi untuk menampilkan daftar mahasiswa terdaftar
    public static void tampilkanMahasiswaTerdaftar() {
        System.out.println("\n===== Mahasiswa Terdaftar =====");
        System.out.printf("%-20s %-20s %-15s %-20s\n", "Nama", "Fakultas", "NIM", "Program Studi");
        for (Mahasiswa mahasiswa : mahasiswas) {
            System.out.printf("%-20s %-20s %-15s %-20s\n", mahasiswa.getNama(), mahasiswa.getFakultas(), mahasiswa.getNim(), mahasiswa.getProgram());
        }
    }
}
