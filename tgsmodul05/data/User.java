package org.modul_5.tugas.data;

import org.modul_5.tugas.books.Book;
import org.modul_5.tugas.books.HistoryBook;
import org.modul_5.tugas.books.StoryBook;
import org.modul_5.tugas.books.TextBook;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
    protected static ArrayList<Book> bookList = new ArrayList<>();

    public void displayBook(){
        System.out.println("Daftar Buku:");
        System.out.println("================================================================");
        System.out.println("|| No. || Id Buku || Judul Buku || Pengarang || Kategori || Stok ||");
        int indeks = 0;
        for (Book buku : bookList) {
            System.out.println("|| " + indeks + "  || " + buku.getBookId() + " || " + buku.getTitle() + " || " + buku.getAuthor() + " || " + buku.getCategory() + "  || " + buku.getStock() + " ||");
            indeks++;
        }
        System.out.println("================================================================");
    }

    public void inputBook(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Masukkan detail buku:");
        System.out.print("Masukkan ID buku: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan judul buku: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan pengarang buku: ");
        String pengarang = scanner.nextLine();
        System.out.print("Masukkan stok buku: ");
        int stok = Integer.parseInt(scanner.nextLine());

        System.out.println("Pilih kategori buku:");
        System.out.println("1. Buku Cerita");
        System.out.println("2. Buku Sejarah");
        System.out.println("3. Buku Teks");
        System.out.print("Masukkan pilihan (1-3): ");
        String pilihanKategori = scanner.nextLine();
        Book newBook = null;

        String kategori; //IMPLEMENTASI POLYMORPHISM
        switch (pilihanKategori) {
            case "1":
                kategori = "Buku Cerita";
                newBook = new StoryBook(id, judul, pengarang, kategori, stok);
                break;
            case "2":
                kategori = "Buku Sejarah";
                newBook = new HistoryBook(id, judul, pengarang, kategori, stok);
                break;
            case "3":
                kategori = "Buku Teks";
                newBook = new TextBook(id, judul, pengarang, kategori, stok);
                break;
            default:
                System.out.println("Pilihan tidak valid. Default ke Buku Cerita.");
                kategori = "Buku Cerita";
                newBook = new StoryBook(id, judul, pengarang, kategori, stok);
                break;
        }

        bookList.add(newBook);
        System.out.println("Buku berhasil ditambahkan ke perpustakaan.");
    }
}