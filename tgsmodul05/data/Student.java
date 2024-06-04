package org.modul_5.tugas.data;

import org.modul_5.tugas.books.Book;
import org.modul_5.tugas.exception.custom.InvalidMenuInput;
import org.modul_5.tugas.util.iMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student extends User implements iMenu {
    private String name;
    private String nim;
    private String faculty;
    private String programStudi;
    private static List<Book> borrowedBooks = new ArrayList<>();
    public Student(String name, String nim, String faculty, String programStudi){
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.programStudi = programStudi;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setNim(String nim){
        this.nim = nim;
    }
    public String getNim(){
        return nim;
    }
    public void setProgramStudi(String programStudi){
        this.programStudi = programStudi;
    }
    public String getProgramStudi(){
        return programStudi;
    }
    public void setFaculty(String faculty){
        this.faculty = faculty;
    }
    public String getFaculty(){
        return faculty;
    }
    public void setBorrowedBooks(List<Book> borrowedBooks) {
        Student.borrowedBooks = borrowedBooks;
    }
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }


    public void displayInfo(){
        System.out.println("\nNama: " + name + "\nNim: " + nim + "\nJurusan: " + programStudi + "\nFakultas: " + faculty);
    }
    public void showBorrowedBooks(){}
    public void displayBook(){
        super.displayBook();
    }
    public void logout(){}
    public void returnBook(){}

    String displayStudents() {
        return "\nNama: " + name + "\nNim: " + nim + "\nJurusan: " + programStudi + "\nFakultas: " + faculty;
    }

    @Override
    public void menu() {
        String pilihan;
        boolean ulang = true;
        Scanner objInput = new Scanner(System.in);

        while (ulang) {
            System.out.println("\nStudent menu: ");
            System.out.println("0. Tampil info student");
            System.out.println("1. Tampilkan Buku yang Dapat Dipinjam ");
            System.out.println("2. Buku terpinjam");
            System.out.println("3. Pinjam buku");
            System.out.println("4. Kembalikan buku");
            System.out.println("5. Pinjam buku atau Logout");

            System.out.print("Pilihan Anda: ");
            pilihan = objInput.nextLine();

            try {
                switch (pilihan) {
                    case "0":
                        System.out.println("Info student");
                        displayInfo();
                        break;
                    case "1":
                        System.out.println("Daftar Buku");
                        displayBook();
                        break;
                    case "2":
                        System.out.println("Buku terpinjam");
                        break;
                    case "3":
                        System.out.println("Pinjam buku");
                        break;
                    case "4":
                        System.out.println("Kembalikan buku");
                        break;
                    case "5":
                        System.out.println("Pinjam buku atau Logout");
                        ulang = false;
                        break;
                    default:
                        throw new InvalidMenuInput("inputan tidak valid");
                }
            } catch (InvalidMenuInput e) {
                String errorMessage = e.getMessage();
                System.out.println(errorMessage);
            }
        }
    }
}